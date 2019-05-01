package rs.raf.gui.dialogs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import rs.raf.actions.CustomParameterCancelAction;
import rs.raf.actions.CustomParameterOkAction;
import rs.raf.utils.Localisation;
import rs.raf.utils.ScreenUtils;

@SuppressWarnings("serial")
public class CustomParameterDialog extends JFrame {
	private JTextField textFieldName;
	private JTextArea textAreaDescription;

	private JButton buttonBrowse;
	private JRadioButton radioButtonAddFile;

	private JRadioButton radioButtonUserInput;
	private JRadioButton radioButtonComboBox;

	private JTextArea textAreaComboBox;
	private JTextField textFieldLocation;

	private JPanel panelCenterParameters;
	private JFileChooser fileChooser;

	public CustomParameterDialog() {
		setBounds(0, 0, ScreenUtils.getScreenWidth() * 2 / 5, ScreenUtils.getScreenHeight() * 2 / 5);
		setLocationRelativeTo(null);
		setVisible(true);
		setTitle(Localisation.getLocalisedString("mNewCustomParameter"));

		initialiseComponents();
		initialiseComponentStates();
		initialiseListeners();

		addCOmponents();
		add(panelCenterParameters, BorderLayout.CENTER);

		JButton buttonOk = new JButton(Localisation.getLocalisedString("mOk"));
		JButton buttonCancel = new JButton(Localisation.getLocalisedString("mCancel"));

		buttonOk.addActionListener(new CustomParameterOkAction(this));
		buttonCancel.addActionListener(new CustomParameterCancelAction(this));

		JPanel optionsPanel = new JPanel();
		optionsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10));
		optionsPanel.add(buttonOk);
		optionsPanel.add(buttonCancel);

		add(optionsPanel, BorderLayout.SOUTH);
	}

	private void initialiseComponents() {
		textFieldName = new JTextField();
		textAreaDescription = new JTextArea();

		buttonBrowse = new JButton(Localisation.getLocalisedString("mBrowse"));
		radioButtonAddFile = new JRadioButton(Localisation.getLocalisedString("mBrowseFile"));

		radioButtonUserInput = new JRadioButton(Localisation.getLocalisedString("mUserInput"));
		radioButtonComboBox = new JRadioButton(Localisation.getLocalisedString("mComboBox"));

		textAreaComboBox = new JTextArea();
		textFieldLocation = new JTextField();

		fileChooser = new JFileChooser();

		panelCenterParameters = new JPanel();
	}

	private void initialiseComponentStates() {
		textFieldLocation.setEditable(false);
		buttonBrowse.setEnabled(false);
		textAreaComboBox.setEditable(false);
		textAreaComboBox.setText(Localisation.getLocalisedString("mComboBoxOptionsTextAreaContent"));
	}

	private void initialiseListeners() {
		// Browse Files
		buttonBrowse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
					textFieldLocation.setText(fileChooser.getSelectedFile().toString());
				else
					textFieldLocation.setText("");
			}
		});

		// Browse Type
		radioButtonAddFile.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent event) {
				buttonBrowse.setEnabled(radioButtonAddFile.isSelected());
			}
		});
		
		// Combo Box
		radioButtonComboBox.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent event) {
				textAreaComboBox.setEnabled(radioButtonComboBox.isSelected());
				textAreaComboBox.setEditable(radioButtonComboBox.isSelected());
			}
		});
	}

	private void addCOmponents() {
		// Radio buttons
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(radioButtonAddFile);
		buttonGroup.add(radioButtonComboBox);
		buttonGroup.add(radioButtonUserInput);

		// Other
		textFieldName.setPreferredSize(new Dimension(400, 20));
		textAreaDescription.setPreferredSize(new Dimension(400, 100));
		textFieldLocation.setPreferredSize(new Dimension(400, 20));
		textAreaComboBox.setPreferredSize(new Dimension(400, 100));
		panelCenterParameters.setLayout(new GridBagLayout());

		// Position
		GridBagConstraints gridBagConstraint = new GridBagConstraints();
		gridBagConstraint.gridx = 0;
		gridBagConstraint.gridy = 0;
		panelCenterParameters.add(new JLabel(Localisation.getLocalisedString("mParameterName")), gridBagConstraint);

		gridBagConstraint.gridx = 1;
		gridBagConstraint.gridy = 0;
		panelCenterParameters.add(textFieldName, gridBagConstraint);

		gridBagConstraint.gridx = 0;
		gridBagConstraint.gridy = 1;
		panelCenterParameters.add(new JLabel(Localisation.getLocalisedString("mParameterDescription")),
				gridBagConstraint);

		gridBagConstraint.gridx = 1;
		gridBagConstraint.gridy = 1;
		panelCenterParameters.add(textAreaDescription, gridBagConstraint);

		gridBagConstraint.gridx = 1;
		gridBagConstraint.gridy = 2;
		panelCenterParameters.add(new JLabel(), gridBagConstraint);

		gridBagConstraint.gridx = 0;
		gridBagConstraint.gridy = 3;
		panelCenterParameters.add(buttonBrowse, gridBagConstraint);

		gridBagConstraint.gridx = 1;
		gridBagConstraint.gridy = 3;
		panelCenterParameters.add(textFieldLocation, gridBagConstraint);

		gridBagConstraint.gridx = 1;
		gridBagConstraint.gridy = 4;
		panelCenterParameters.add(radioButtonUserInput, gridBagConstraint);

		gridBagConstraint.gridx = 1;
		gridBagConstraint.gridy = 5;
		panelCenterParameters.add(radioButtonAddFile, gridBagConstraint);

		gridBagConstraint.gridx = 1;
		gridBagConstraint.gridy = 6;
		panelCenterParameters.add(radioButtonComboBox, gridBagConstraint);

		gridBagConstraint.gridx = 1;
		gridBagConstraint.gridy = 7;
		panelCenterParameters.add(textAreaComboBox, gridBagConstraint);

	}

	public JTextField getTextFieldName() {
		return textFieldName;
	}

	public JTextArea getTextAreaDescription() {
		return textAreaDescription;
	}

	public JButton getButtonBrowse() {
		return buttonBrowse;
	}

	public JRadioButton getRadioButtonAddFile() {
		return radioButtonAddFile;
	}

	public JRadioButton getRadioButtonUserInput() {
		return radioButtonUserInput;
	}

	public JRadioButton getRadioButtonComboBox() {
		return radioButtonComboBox;
	}

	public JTextArea getTextAreaComboBox() {
		return textAreaComboBox;
	}

	public JTextField getTextFieldLocation() {
		return textFieldLocation;
	}

	public JPanel getPanelCenterParameters() {
		return panelCenterParameters;
	}

	public JFileChooser getFileChooser() {
		return fileChooser;
	}
}
