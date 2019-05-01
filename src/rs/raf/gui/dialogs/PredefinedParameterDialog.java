package rs.raf.gui.dialogs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import rs.raf.actions.PredefinedParameterCancelAction;
import rs.raf.actions.PredefinedParameterOkAction;
import rs.raf.utils.Localisation;
import rs.raf.utils.ScreenUtils;

@SuppressWarnings("serial")
public class PredefinedParameterDialog extends JFrame {
	private JTextField textFieldName;
	private JTextArea textAreaDescription;

	private JRadioButton radioButtonCompanyName;
	private JRadioButton radioButtonSystem;
	private JRadioButton radioButtonLogo;
	private JRadioButton radioButtonLanguage;
	private JRadioButton radioButtonLookAndFeel;
	private JRadioButton radioButtonVersion;
	private JRadioButton radioButtonSoftware;
	private JRadioButton radioButtonEntryPoint;

	private JPanel parametersPanel;

	public PredefinedParameterDialog() {
		setBounds(0, 0, ScreenUtils.getScreenWidth() * 2 / 5, ScreenUtils.getScreenHeight() * 2 / 5);
		setLocationRelativeTo(null);
		setVisible(true);
		setTitle(Localisation.getLocalisedString("mPredefinedParameterDalogTitle"));

		intitaliseParameters();
		add(parametersPanel, BorderLayout.CENTER);

		JButton buttonOk = new JButton(Localisation.getLocalisedString("mOk"));
		JButton buttonCancel = new JButton(Localisation.getLocalisedString("mCancel"));

		buttonOk.addActionListener(new PredefinedParameterOkAction(this));
		buttonCancel.addActionListener(new PredefinedParameterCancelAction(this));

		JPanel optionsPanel = new JPanel();
		optionsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10));
		optionsPanel.add(buttonOk);
		optionsPanel.add(buttonCancel);

		add(optionsPanel, BorderLayout.SOUTH);
	}

	private void intitaliseParameters() {
		parametersPanel = new JPanel();

		textFieldName = new JTextField();
		textAreaDescription = new JTextArea();

		radioButtonCompanyName = new JRadioButton(Localisation.getLocalisedString("mPredefinedParameterCompanyName"));
		radioButtonSystem = new JRadioButton(Localisation.getLocalisedString("mPredefinedParameterSystem"));
		radioButtonLogo = new JRadioButton(Localisation.getLocalisedString("mPredefinedParameterLogo"));
		radioButtonLanguage = new JRadioButton(Localisation.getLocalisedString("mPredefinedParameterLanguage"));
		radioButtonLookAndFeel = new JRadioButton(Localisation.getLocalisedString("mPredefinedParameterLookAndFeel"));
		radioButtonVersion = new JRadioButton(Localisation.getLocalisedString("mPredefinedParameterVersion"));
		radioButtonSoftware = new JRadioButton(Localisation.getLocalisedString("mPredefinedParameterSoftware"));
		radioButtonEntryPoint = new JRadioButton(Localisation.getLocalisedString("mPredefinedParameterEntryPoint"));

		textFieldName.setPreferredSize(new Dimension(300, 20));
		textAreaDescription.setPreferredSize(new Dimension(300, 100));
		parametersPanel.setLayout(new GridBagLayout());

		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(radioButtonCompanyName);
		buttonGroup.add(radioButtonLanguage);
		buttonGroup.add(radioButtonLogo);
		buttonGroup.add(radioButtonLookAndFeel);
		buttonGroup.add(radioButtonSoftware);
		buttonGroup.add(radioButtonSystem);
		buttonGroup.add(radioButtonVersion);
		buttonGroup.add(radioButtonEntryPoint);

		GridBagConstraints gridBagConstraint = new GridBagConstraints();
		gridBagConstraint.insets = new Insets(5, 5, 5, 5);
		gridBagConstraint.gridx = 1;
		gridBagConstraint.gridy = 0;
		parametersPanel.add(textFieldName, gridBagConstraint);

		gridBagConstraint.gridx = 0;
		gridBagConstraint.gridy = 0;
		parametersPanel.add(new JLabel(Localisation.getLocalisedString("mParameterName")), gridBagConstraint);

		gridBagConstraint.gridx = 1;
		gridBagConstraint.gridy = 1;
		parametersPanel.add(textAreaDescription, gridBagConstraint);

		gridBagConstraint.gridx = 0;
		gridBagConstraint.gridy = 1;
		parametersPanel.add(new JLabel(Localisation.getLocalisedString("mParameterDescription")), gridBagConstraint);

		gridBagConstraint.gridx = 0;
		gridBagConstraint.gridy = 2;
		parametersPanel.add(new JLabel(Localisation.getLocalisedString("mPredefinedParameterChoose")),
				gridBagConstraint);

		gridBagConstraint.gridx = 1;
		gridBagConstraint.gridy = 3;
		parametersPanel.add(radioButtonCompanyName, gridBagConstraint);

		gridBagConstraint.gridx = 1;
		gridBagConstraint.gridy = 7;
		parametersPanel.add(radioButtonLogo, gridBagConstraint);

		gridBagConstraint.gridx = 1;
		gridBagConstraint.gridy = 4;
		parametersPanel.add(radioButtonSystem, gridBagConstraint);

		gridBagConstraint.gridx = 2;
		gridBagConstraint.gridy = 3;
		parametersPanel.add(radioButtonSoftware, gridBagConstraint);

		gridBagConstraint.gridx = 1;
		gridBagConstraint.gridy = 5;
		parametersPanel.add(radioButtonVersion, gridBagConstraint);

		gridBagConstraint.gridx = 2;
		gridBagConstraint.gridy = 4;
		parametersPanel.add(radioButtonEntryPoint, gridBagConstraint);

		gridBagConstraint.gridx = 1;
		gridBagConstraint.gridy = 6;
		parametersPanel.add(radioButtonLanguage, gridBagConstraint);
		
		gridBagConstraint.gridx = 2;
		gridBagConstraint.gridy = 5;
		parametersPanel.add(radioButtonLookAndFeel, gridBagConstraint);
	}

	public JTextField getTextFieldName() {
		return textFieldName;
	}

	public JTextArea getTextAreaDescription() {
		return textAreaDescription;
	}

	public JRadioButton getRadioButtonCompanyName() {
		return radioButtonCompanyName;
	}

	public JRadioButton getRadioButtonSystem() {
		return radioButtonSystem;
	}

	public JRadioButton getRadioButtonLogo() {
		return radioButtonLogo;
	}

	public JRadioButton getRadioButtonLanguage() {
		return radioButtonLanguage;
	}

	public JRadioButton getRadioButtonLookAndFeel() {
		return radioButtonLookAndFeel;
	}

	public JRadioButton getRadioButtonVersion() {
		return radioButtonVersion;
	}

	public JRadioButton getRadioButtonSoftware() {
		return radioButtonSoftware;
	}
	
	public JRadioButton getRadioButtonEntryPoint() {
		return radioButtonEntryPoint;
	}

	public JPanel getParametersPanel() {
		return parametersPanel;
	}

	public void setTextFieldName(JTextField textFieldName) {
		this.textFieldName = textFieldName;
	}

	public void setTextAreaDescription(JTextArea textAreaDescription) {
		this.textAreaDescription = textAreaDescription;
	}

	public void setRadioButtonCompanyName(JRadioButton radioButtonCompanyName) {
		this.radioButtonCompanyName = radioButtonCompanyName;
	}

	public void setRadioButtonSystem(JRadioButton radioButtonSystem) {
		this.radioButtonSystem = radioButtonSystem;
	}

	public void setRadioButtonLogo(JRadioButton radioButtonLogo) {
		this.radioButtonLogo = radioButtonLogo;
	}

	public void setRadioButtonLanguage(JRadioButton radioButtonLanguage) {
		this.radioButtonLanguage = radioButtonLanguage;
	}

	public void setRadioButtonLookAndFeel(JRadioButton radioButtonLookAndFeel) {
		this.radioButtonLookAndFeel = radioButtonLookAndFeel;
	}

	public void setRadioButtonVersion(JRadioButton radioButtonVersion) {
		this.radioButtonVersion = radioButtonVersion;
	}

	public void setRadioButtonSoftware(JRadioButton radioButtonSoftware) {
		this.radioButtonSoftware = radioButtonSoftware;
	}

	public void setRadioButtonEntryPoint(JRadioButton radioButtonEntryPoint) {
		this.radioButtonEntryPoint = radioButtonEntryPoint;
	}
}
