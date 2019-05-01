package rs.raf.gui.dialogs;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import rs.raf.gui.MainFrameInstaFram;
import rs.raf.gui.components.cards.FirstCard;
import rs.raf.utils.Installation;
import rs.raf.utils.Localisation;

@SuppressWarnings("serial")
public class Preview extends JFrame {
	private JButton buttonBack;
	private JButton buttonNext;
	private JButton buttonCancel;

	private JPanel firstPanel;
	private JPanel secondPanel;
	private JPanel cards;

	private JLabel labelWelcome;
	private JLabel labelText;
	private JLabel labelText1;

	@SuppressWarnings("unused")
	private BoxLayout boxlayout;
	private Font firstLabelFont;
	private Font secondLabelFont;

	@SuppressWarnings("unused")
	private Installation installation;
	
	public Preview(Installation installation) {
		if(MainFrameInstaFram.getInstance() == null)
			return;
		
		this.installation = installation;
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0, 0, screenSize.width * 3 / 5, screenSize.height * 3 / 5);

		firstPanel = new JPanel();
		secondPanel = new JPanel();
		cards = new JPanel();

		buttonBack = new JButton(Localisation.getLocalisedString("mButtonBack"));
		buttonNext = new JButton(Localisation.getLocalisedString("mButtonNext"));
		buttonCancel = new JButton(Localisation.getLocalisedString("mButtonCancel"));

		labelWelcome = new JLabel(Localisation.format(Localisation.getLocalisedString("mWelcome"), "%name%",
				MainFrameInstaFram.getInstance().getCurrentProduct().getName()));
		labelText = new JLabel(Localisation.format(Localisation.getLocalisedString("mInstallText"), "%name%",
				MainFrameInstaFram.getInstance().getCurrentProduct().getName()));
		labelText1 = new JLabel(Localisation.getLocalisedString("mToContinue"));

		boxlayout = new BoxLayout(firstPanel, BoxLayout.Y_AXIS);
		firstLabelFont = new Font("Calibri", Font.BOLD, 40);
		secondLabelFont = new Font("Calibri", Font.ITALIC, 15);

		setLocationRelativeTo(null);
		setVisible(true);
		setTitle("Installer");
		setResizable(false);

		GridBagConstraints gridBagContraints = new GridBagConstraints();
		firstPanel.setLayout(new GridBagLayout());

		gridBagContraints.gridx = 3;
		gridBagContraints.gridy = 0;
		firstPanel.add(labelWelcome, gridBagContraints);

		gridBagContraints.gridx = 3;
		gridBagContraints.gridy = 2;
		firstPanel.add(labelText, gridBagContraints);

		gridBagContraints.gridx = 3;
		gridBagContraints.gridy = 4;
		firstPanel.add(labelText1, gridBagContraints);

		labelWelcome.setFont(firstLabelFont);
		labelText.setFont(secondLabelFont);
		labelText1.setFont(secondLabelFont);

		buttonBack.setEnabled(false);

		cards.setLayout(new CardLayout());

		secondPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10));
		secondPanel.add(buttonBack);
		secondPanel.add(buttonNext);
		secondPanel.add(buttonCancel);

		buttonNext.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new FirstCard(installation);
				setVisible(false);
			}
		});

		buttonCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});

		firstPanel.setBackground(Color.LIGHT_GRAY);
		this.add(firstPanel);
		this.add(secondPanel, BorderLayout.SOUTH);

		setVisible(true);
	}

	public JPanel getCards() {
		return cards;
	}
}
