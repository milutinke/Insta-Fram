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

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import rs.raf.gui.MainFrameInstaFram;
import rs.raf.utils.Installation;
import rs.raf.utils.Localisation;

@SuppressWarnings("serial")
public class SecondCard extends JFrame {
	private JPanel firstPanel;
	private JPanel secondPanel;
	private JTextArea textArea;

	private JLabel labelChoosePath;

	private JButton buttonBack;
	private JButton buttonNext;
	private JButton buttonCancel;
	private JButton buttonBrowse;

	private Font firstFont;
	private Font secondFont;

	public SecondCard(Installation installation) {
		if (MainFrameInstaFram.getInstance() == null)
			return;

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0, 0, screenSize.width * 3 / 5, screenSize.height * 3 / 5);

		firstPanel = new JPanel();
		secondPanel = new JPanel();
		textArea = new JTextArea();

		labelChoosePath = new JLabel(Localisation.getLocalisedString("mInstallationLocation"));

		buttonBack = new JButton(Localisation.getLocalisedString("mButtonBack"));
		buttonNext = new JButton(Localisation.getLocalisedString("mButtonNext"));
		buttonCancel = new JButton(Localisation.getLocalisedString("mButtonCancel"));
		buttonBrowse = new JButton(Localisation.getLocalisedString("mBrowse"));

		firstFont = new Font("Calibri", Font.BOLD, 20);
		secondFont = new Font("Calibri", Font.ITALIC, 15);

		setLocationRelativeTo(null);
		setVisible(true);
		setTitle("Installer");
		setResizable(false);

		labelChoosePath.setFont(firstFont);
		firstPanel.setLayout(new GridBagLayout());

		GridBagConstraints girdBagContraint = new GridBagConstraints();
		girdBagContraint.insets = new Insets(5, 5, 5, 5);

		textArea.setText(installation.getPath());

		textArea.setFont(secondFont);
		textArea.setPreferredSize(new Dimension(900, 25));

		girdBagContraint.gridx = 0;
		girdBagContraint.gridy = 0;
		firstPanel.add(labelChoosePath, girdBagContraint);

		girdBagContraint.gridx = 0;
		girdBagContraint.gridy = 1;
		firstPanel.add(textArea, girdBagContraint);

		girdBagContraint.anchor = GridBagConstraints.WEST;
		girdBagContraint.gridx = 0;
		girdBagContraint.gridy = 3;
		firstPanel.add(buttonBrowse, girdBagContraint);

		secondPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10));
		secondPanel.add(buttonBack);
		secondPanel.add(buttonNext);
		secondPanel.add(buttonCancel);

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		buttonBrowse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					textArea.setText(fileChooser.getSelectedFile().toString());
					installation.setPath(textArea.getText());
				}
			}
		});

		buttonBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				new FirstCard(installation);
				setVisible(false);
				dispose();
			}
		});

		buttonNext.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				new ThirdCard(installation);

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
