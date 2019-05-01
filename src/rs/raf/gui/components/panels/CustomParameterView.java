package rs.raf.gui.components.panels;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.StringTokenizer;

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
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import rs.raf.gui.MainFrameInstaFram;
import rs.raf.model.InstaFramCustomParameter;
import rs.raf.utils.Localisation;

@SuppressWarnings("serial")
public class CustomParameterView extends JPanel {
	private InstaFramCustomParameter parameter;

	public CustomParameterView(InstaFramCustomParameter parameter) {
		this.parameter = parameter;

		initialise();
	}

	private void initialise() {
		JLabel labelName = new JLabel(parameter.getName());
		JTextArea textAreaDescription = new JTextArea(parameter.getDescription());

		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		textAreaDescription.setLineWrap(true);
		textAreaDescription.setEnabled(false);

		JScrollPane scrollPane = new JScrollPane(textAreaDescription);

		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setMaximumSize(new Dimension(400, 100));

		this.add(labelName);

		add(Box.createVerticalStrut(10));

		this.add(scrollPane);

		if (parameter.isUserInput()) {
			userInput();
			return;
		}

		if (parameter.isHasFile()) {
			addFile();
			return;
		}

		if (parameter.isComboBox()) {
			addComboBox();
			return;
		}
	}

	private void userInput() {
		JTextArea textAreaUserInput = new JTextArea(parameter.getValue());
		textAreaUserInput.setMaximumSize(new Dimension(400, 50));

		add(Box.createVerticalStrut(10));

		textAreaUserInput.setLineWrap(true);
		textAreaUserInput.setEditable(true);

		JScrollPane scrollPane = new JScrollPane(textAreaUserInput);

		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setMaximumSize(new Dimension(400, 200));

		this.add(textAreaUserInput);

		textAreaUserInput.addCaretListener(new CaretListener() {
			@Override
			public void caretUpdate(CaretEvent e) {
				parameter.setValue(textAreaUserInput.getText());
				MainFrameInstaFram.getInstance().setSaved(false);
			}
		});
	}

	private void addFile() {
		JLabel labelSoftware = new JLabel(Localisation.getLocalisedString("mBrowseFile"));

		add(Box.createHorizontalGlue());

		JTextField textFiledFileLocation = new JTextField(parameter.getValue());

		add(Box.createHorizontalGlue());

		JButton buttonChooseSoftware = new JButton(Localisation.getLocalisedString("mBrowse"));
		JFileChooser fileChooser = new JFileChooser();

		buttonChooseSoftware.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					textFiledFileLocation.setText(fileChooser.getSelectedFile().toString());
					parameter.setValue(textFiledFileLocation.getText());
					MainFrameInstaFram.getInstance().setSaved(false);
				} else {
					textFiledFileLocation.setText("");
					parameter.setValue(textFiledFileLocation.getText());
					MainFrameInstaFram.getInstance().setSaved(false);
				}
			}
		});

		this.add(labelSoftware);
		this.add(textFiledFileLocation);
		this.add(buttonChooseSoftware);
	}

	private void addComboBox() {
		JComboBox<String> comboBox = new JComboBox<>();
		comboBox.setMaximumSize(new Dimension(400, 100));
		add(Box.createVerticalStrut(10));

		StringTokenizer stringTokenizer = new StringTokenizer(parameter.getComboBoxOptions(), "\n");

		while (stringTokenizer.hasMoreTokens()) {
			comboBox.addItem(stringTokenizer.nextToken());
		}

		this.add(comboBox);

		comboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				parameter.setValue(comboBox.getSelectedItem().toString());
			}
		});
	}
}
