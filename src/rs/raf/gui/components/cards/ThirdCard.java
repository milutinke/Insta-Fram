package rs.raf.gui.components.cards;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

import rs.raf.gui.MainFrameInstaFram;
import rs.raf.utils.Installation;
import rs.raf.utils.Localisation;

@SuppressWarnings("serial")
public class ThirdCard extends JFrame {
	private JPanel firstPanel;
	private JPanel secondPanel;

	private JCheckBox checkBoxCreateShorcut;
	private JCheckBox checkBoxRunAfterInstall;

	private JButton buttonBack;
	private JButton buttonNext;
	private JButton buttonCancel;

	private Font firstFont;

	public ThirdCard(Installation installation) {
		if (MainFrameInstaFram.getInstance() == null)
			return;

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0, 0, screenSize.width * 3 / 5, screenSize.height * 3 / 5);

		firstPanel = new JPanel();
		secondPanel = new JPanel();

		checkBoxCreateShorcut = new JCheckBox(Localisation.getLocalisedString("mCreateShotCut"));
		checkBoxRunAfterInstall = new JCheckBox(Localisation.getLocalisedString("mRunAfterFinish"));

		checkBoxCreateShorcut.setSelected(true);
		checkBoxRunAfterInstall.setSelected(true);

		buttonBack = new JButton(Localisation.getLocalisedString("mButtonBack"));
		buttonNext = new JButton(Localisation.getLocalisedString("mButtonNext"));
		buttonCancel = new JButton(Localisation.getLocalisedString("mButtonCancel"));

		firstFont = new Font("Calibri", Font.BOLD, 20);

		setLocationRelativeTo(null);
		setVisible(true);
		setTitle("Installer");
		setResizable(false);

		firstPanel.setLayout(new GridBagLayout());

		GridBagConstraints girdBagContraint = new GridBagConstraints();
		girdBagContraint.insets = new Insets(5, 5, 5, 5);

		checkBoxCreateShorcut.setFont(firstFont);
		checkBoxRunAfterInstall.setFont(firstFont);

		girdBagContraint.gridx = 0;
		girdBagContraint.gridy = 0;
		firstPanel.add(checkBoxCreateShorcut, girdBagContraint);

		girdBagContraint.gridx = 0;
		girdBagContraint.gridy = 1;
		firstPanel.add(checkBoxRunAfterInstall, girdBagContraint);

		secondPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10));
		secondPanel.add(buttonBack);
		secondPanel.add(buttonNext);
		secondPanel.add(buttonCancel);

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		checkBoxCreateShorcut.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent event) {
				installation.setCreateShortcut(checkBoxCreateShorcut.isSelected());
			}
		});

		checkBoxRunAfterInstall.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent event) {
				installation.setRunAfterInstallation(checkBoxRunAfterInstall.isSelected());
			}
		});

		buttonBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				new SecondCard(installation);
				setVisible(false);
				dispose();
			}
		});

		buttonNext.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				new FourthCard(installation);

				setVisible(false);
				dispose();
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
