package rs.raf.gui.components.panels;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Locale;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import rs.raf.gui.MainFrameInstaFram;
import rs.raf.model.InstaFramPredefinedParameter;
import rs.raf.utils.Localisation;

@SuppressWarnings("serial")
public class PredefinedParameterView extends JPanel {
	private InstaFramPredefinedParameter parameter;

	public PredefinedParameterView(InstaFramPredefinedParameter parameter) {
		this.parameter = parameter;

		initialize();
	}

	private void initialize() {
		JLabel labelName = new JLabel(parameter.getName());
		JTextArea textAreaDescription = new JTextArea(parameter.getDescription());

		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		textAreaDescription.setLineWrap(true);
		textAreaDescription.setEditable(false);

		JScrollPane scrollPane = new JScrollPane(textAreaDescription);

		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setMaximumSize(new Dimension(400, 100));

		this.add(labelName);

		add(Box.createVerticalStrut(10));

		this.add(scrollPane);

		if (parameter.isCompanyName()) {
			enterCompanyName();
			return;
		}

		if (parameter.isSystem()) {
			chooseSystem();
			return;
		}

		if (parameter.isLogo()) {
			addLogo();
			return;
		}

		if (parameter.isLangauge()) {
			addLangauge();
			return;
		}

		if (parameter.isVersion()) {
			selectVerison();
			return;
		}

		if (parameter.isSoftware()) {
			addSoftware();
			return;
		}

		if (parameter.isLookAndFeel()) {
			setLookAndFeel();
			return;
		}
		
		if (parameter.isEntryPoint()) {
			addEntryPoint();
			return;
		}
	}

	private void enterCompanyName() {
		JLabel labelCompanyName = new JLabel(Localisation.getLocalisedString("mPredefinedParameterCompanyName"));

		this.add(Box.createHorizontalGlue());

		JTextField textFieldCompanyName = new JTextField(parameter.getValue());
		textFieldCompanyName.setPreferredSize(new Dimension(150, 20));

		this.add(labelCompanyName);
		this.add(textFieldCompanyName);

		textFieldCompanyName.addCaretListener(new CaretListener() {
			@Override
			public void caretUpdate(CaretEvent event) {
				parameter.setValue(textFieldCompanyName.getText());
				MainFrameInstaFram.getInstance().setSaved(false);
			}
		});
	}

	private void chooseSystem() {
		JLabel labelChooseSistem = new JLabel(Localisation.getLocalisedString("mPredefinedParameterSystem"));

		add(Box.createHorizontalGlue());

		JComboBox<String> checkBoxChooseSistem = new JComboBox<String>();

		checkBoxChooseSistem.addItem("Windows");
		checkBoxChooseSistem.addItem("Linux");
		checkBoxChooseSistem.addItem("Mac OS");
		checkBoxChooseSistem.setPreferredSize(new Dimension(150, 20));

		String parameterValue = parameter.getValue();
		if (parameterValue.length() > 0) {
			if (parameterValue.equalsIgnoreCase("Windows"))
				checkBoxChooseSistem.setSelectedIndex(0);

			if (parameterValue.equalsIgnoreCase("Linux"))
				checkBoxChooseSistem.setSelectedIndex(1);

			if (parameterValue.equalsIgnoreCase("Mac OS"))
				checkBoxChooseSistem.setSelectedIndex(2);
		}

		this.add(labelChooseSistem);
		this.add(checkBoxChooseSistem);
		checkBoxChooseSistem.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				parameter.setValue(checkBoxChooseSistem.getSelectedItem().toString());
				MainFrameInstaFram.getInstance().setSaved(false);
			}
		});
	}

	private void addLogo() {
		JLabel labelLogo = new JLabel(Localisation.getLocalisedString("mPredefinedParameterLogo"));

		add(Box.createHorizontalGlue());

		JTextField textFieldChooseLogoField = new JTextField(parameter.getValue());
		textFieldChooseLogoField.setEnabled(false);
		add(Box.createHorizontalGlue());

		JButton buttonChooseLogo = new JButton(Localisation.getLocalisedString("mChooseLogoButton"));
		JFileChooser fileChooser = new JFileChooser();

		textFieldChooseLogoField.setPreferredSize(new Dimension(150, 20));
		buttonChooseLogo.setPreferredSize(new Dimension(80, 20));

		buttonChooseLogo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					textFieldChooseLogoField.setText(fileChooser.getSelectedFile().toString());
					parameter.setValue(textFieldChooseLogoField.getText());
					MainFrameInstaFram.getInstance().setSaved(false);
				} else {
					textFieldChooseLogoField.setText("");
					parameter.setValue("");
					MainFrameInstaFram.getInstance().setSaved(false);
				}
			}
		});

		this.add(labelLogo);
		this.add(textFieldChooseLogoField);
		this.add(buttonChooseLogo);
	}

	private void addLangauge() {
		JLabel labelChooseLangauge = new JLabel(Localisation.getLocalisedString("mPredefinedParameterLanguage"));

		add(Box.createHorizontalGlue());

		JComboBox<String> cxeckBoxChooseLanguage = new JComboBox<>();

		cxeckBoxChooseLanguage.setPreferredSize(new Dimension(150, 20));

		cxeckBoxChooseLanguage.addItem("English (United States)");
		cxeckBoxChooseLanguage.addItem("Serbian (Latin)");

		String parameterValue = parameter.getValue();
		if (parameterValue.length() > 0) {
			if (parameterValue.equalsIgnoreCase("English (United States)"))
				cxeckBoxChooseLanguage.setSelectedIndex(0);

			if (parameterValue.equalsIgnoreCase("Serbian (Latin)"))
				cxeckBoxChooseLanguage.setSelectedIndex(1);
		}

		this.add(labelChooseLangauge);
		this.add(cxeckBoxChooseLanguage);

		cxeckBoxChooseLanguage.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (cxeckBoxChooseLanguage.getSelectedItem().toString().equals("English (United States)")) {
					Locale.setDefault(new Locale("en", "US"));
					MainFrameInstaFram.getInstance().changeLanguage();
					parameter.setValue(cxeckBoxChooseLanguage.getSelectedItem().toString());
				} else if (cxeckBoxChooseLanguage.getSelectedItem().toString().equals("Serbian (Latin)")) {
					Locale.setDefault(new Locale("sr", "RS"));
					MainFrameInstaFram.getInstance().changeLanguage();
					parameter.setValue(cxeckBoxChooseLanguage.getSelectedItem().toString());
				}

				MainFrameInstaFram.getInstance().setSaved(false);
			}
		});
	}

	private void selectVerison() {
		JLabel labelVersion = new JLabel(Localisation.getLocalisedString("mPredefinedParameterVersion"));

		add(Box.createHorizontalGlue());

		JTextField textFieldfVersion = new JTextField(parameter.getValue());
		textFieldfVersion.setPreferredSize(new Dimension(150, 20));

		this.add(labelVersion);
		this.add(textFieldfVersion);

		textFieldfVersion.addCaretListener(new CaretListener() {
			@Override
			public void caretUpdate(CaretEvent event) {
				parameter.setValue(textFieldfVersion.getText());
				MainFrameInstaFram.getInstance().setSaved(false);
			}
		});
	}

	private void addSoftware() {
		JLabel labelSoftware = new JLabel(Localisation.getLocalisedString("mPredefinedParameterSoftware"));

		add(Box.createHorizontalGlue());

		JTextField textFieldSoftwareField = new JTextField(parameter.getValue());

		add(Box.createHorizontalGlue());

		JButton buttonChooseSoftware = new JButton(Localisation.getLocalisedString("mButtonChooseSoftware"));
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		buttonChooseSoftware.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					textFieldSoftwareField.setText(fileChooser.getSelectedFile().toString());
					parameter.setValue(textFieldSoftwareField.getText());
					MainFrameInstaFram.getInstance().setSaved(false);
					// MainFrameInstaFram.getInstance().setDestination(textFieldSoftwareField.getText());
				} else {
					textFieldSoftwareField.setText(System.getenv("APPDATA"));
					parameter.setValue(textFieldSoftwareField.getText());
					MainFrameInstaFram.getInstance().setSaved(false);
					// MainFrameInstaFram.getInstance().setDestination(textFieldSoftwareField.getText());
				}
			}
		});

		this.add(labelSoftware);
		this.add(textFieldSoftwareField);
		this.add(buttonChooseSoftware);
	}
	
	private void addEntryPoint() {
		JLabel labelEntryPoint = new JLabel(Localisation.getLocalisedString("mPredefinedParameterEntryPoint"));

		add(Box.createHorizontalGlue());

		JTextField textFieldEntryPoint = new JTextField(parameter.getValue());

		add(Box.createHorizontalGlue());

		JButton buttonChooseEntryPoint = new JButton(Localisation.getLocalisedString("mButtonChooseFile"));
		JFileChooser fileChooser = new JFileChooser();

		buttonChooseEntryPoint.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					textFieldEntryPoint.setText(fileChooser.getSelectedFile().toString());
					parameter.setValue(textFieldEntryPoint.getText());
					MainFrameInstaFram.getInstance().setSaved(false);
				} else {
					textFieldEntryPoint.setText("");
					parameter.setValue(textFieldEntryPoint.getText());
					MainFrameInstaFram.getInstance().setSaved(false);
				}
			}
		});

		this.add(labelEntryPoint);
		this.add(textFieldEntryPoint);
		this.add(buttonChooseEntryPoint);
	}

	private void setLookAndFeel() {
		JLabel labelChooseLook = new JLabel(Localisation.getLocalisedString("mPredefinedParameterLookAndFeel"));

		add(Box.createHorizontalGlue());

		JComboBox<String> checkBoxLookAndFeel = new JComboBox<String>();

		checkBoxLookAndFeel.addItem("Metal");
		checkBoxLookAndFeel.addItem("Nimbus");
		checkBoxLookAndFeel.addItem("Motif");

		String parameterValue = parameter.getValue();
		if (parameterValue.length() > 0) {
			if (parameterValue.equalsIgnoreCase("Metal"))
				checkBoxLookAndFeel.setSelectedIndex(0);

			if (parameterValue.equalsIgnoreCase("Nimbus"))
				checkBoxLookAndFeel.setSelectedIndex(1);

			if (parameterValue.equalsIgnoreCase("Motif"))
				checkBoxLookAndFeel.setSelectedIndex(2);
		}

		checkBoxLookAndFeel.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				String selected = String.valueOf(checkBoxLookAndFeel.getSelectedItem());

				parameter.setValue(selected);

				try {
					MainFrameInstaFram.getInstance().setLookAndFeel(getLookAndFeelClassName(selected));
					MainFrameInstaFram.getInstance().getTree()
							.expandPath(MainFrameInstaFram.getInstance().getTree().getSelectionPath());
					MainFrameInstaFram.getInstance().setSaved(false);
				} catch (Exception esv) {
					esv.printStackTrace();
				}
			}
		});

		this.add(labelChooseLook);
		this.add(checkBoxLookAndFeel);
	}

	public static String getLookAndFeelClassName(String snippetName) {
		LookAndFeelInfo[] lookandFeelInfos = UIManager.getInstalledLookAndFeels();

		for (LookAndFeelInfo info : lookandFeelInfos) {
			if (info.getName().contains(snippetName)) {
				return info.getClassName();
			}
		}
		return null;
	}
}
