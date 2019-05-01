package rs.raf.gui.components.cards;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import rs.raf.gui.MainFrameInstaFram;
import rs.raf.gui.dialogs.Preview;
import rs.raf.utils.Installation;
import rs.raf.utils.Localisation;

@SuppressWarnings("serial")
public class FirstCard extends JFrame {
	private JPanel firstPanel;
	private JPanel secondPanel;
	private JTextArea textArea;

	private JRadioButton radioButtonAccept;
	private JRadioButton radioButtonDecline;

	private JLabel labelAgreement;
	private JLabel labelAgreementPleaseRead;

	private JButton buttonBack;
	private JButton buttonNext;
	private JButton buttonCancel;

	private Font firstLabelFont;
	private Font secondLabelFont;

	public FirstCard(Installation installation) {
		if (MainFrameInstaFram.getInstance() == null)
			return;

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0, 0, screenSize.width * 3 / 5, screenSize.height * 3 / 5);

		firstPanel = new JPanel();
		secondPanel = new JPanel();
		textArea = new JTextArea();

		radioButtonAccept = new JRadioButton(Localisation.getLocalisedString("mAccept"));
		radioButtonDecline = new JRadioButton(Localisation.getLocalisedString("mDecline"));

		labelAgreement = new JLabel(Localisation.getLocalisedString("mAgreement"));
		labelAgreementPleaseRead = new JLabel(Localisation.getLocalisedString("mAgreementPleaseRead"));

		buttonBack = new JButton(Localisation.getLocalisedString("mButtonBack"));
		buttonNext = new JButton(Localisation.getLocalisedString("mButtonNext"));
		buttonCancel = new JButton(Localisation.getLocalisedString("mButtonCancel"));

		firstLabelFont = new Font("Calibri", Font.BOLD, 20);
		secondLabelFont = new Font("Calibri", Font.ITALIC, 25);

		setLocationRelativeTo(null);
		setVisible(true);
		setTitle("Installer");
		setResizable(false);

		ButtonGroup group = new ButtonGroup();
		group.add(radioButtonAccept);
		group.add(radioButtonDecline);

		labelAgreement.setFont(firstLabelFont);
		labelAgreementPleaseRead.setFont(firstLabelFont);
		firstPanel.setLayout(new GridBagLayout());

		GridBagConstraints girdBagContraint = new GridBagConstraints();
		girdBagContraint.insets = new Insets(5, 5, 5, 5);

		textArea.setText(Localisation.getLocalisedString("mLicenceText"));

		textArea.setFont(secondLabelFont);
		textArea.setPreferredSize(new Dimension(900, 250));

		girdBagContraint.gridx = 0;
		girdBagContraint.gridy = 0;
		firstPanel.add(labelAgreement, girdBagContraint);

		girdBagContraint.gridx = 0;
		girdBagContraint.gridy = 1;
		firstPanel.add(labelAgreementPleaseRead, girdBagContraint);

		girdBagContraint.gridx = 0;
		girdBagContraint.gridy = 2;
		firstPanel.add(textArea, girdBagContraint);

		girdBagContraint.anchor = GridBagConstraints.WEST;
		girdBagContraint.gridx = 0;
		girdBagContraint.gridy = 3;
		firstPanel.add(radioButtonAccept, girdBagContraint);

		girdBagContraint.gridx = 0;
		girdBagContraint.gridy = 4;
		firstPanel.add(radioButtonDecline, girdBagContraint);
		firstPanel.setBackground(Color.LIGHT_GRAY);

		secondPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10));
		secondPanel.add(buttonBack);
		secondPanel.add(buttonNext);
		secondPanel.add(buttonCancel);

		buttonBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				new Preview(installation);
				setVisible(false);
				dispose();
			}
		});

		buttonNext.setEnabled(false);
		radioButtonDecline.setSelected(true);

		radioButtonAccept.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent event) {
				if (radioButtonAccept.isSelected())
					buttonNext.setEnabled(true);
			}
		});

		radioButtonDecline.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent event) {
				if (radioButtonDecline.isSelected())
					buttonNext.setEnabled(false);
			}
		});

		buttonNext.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if (radioButtonAccept.isSelected()) {
					new SecondCard(installation);
					setVisible(false);
					dispose();
				}
			}
		});

		buttonCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				setVisible(false);
				dispose();
			}
		});

		this.add(firstPanel);
		this.add(secondPanel, BorderLayout.SOUTH);
	}
}
