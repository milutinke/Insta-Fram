package rs.raf.gui.components.cards;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import rs.raf.gui.MainFrameInstaFram;
import rs.raf.utils.Installation;
import rs.raf.utils.InstallationProgress;
import rs.raf.utils.Localisation;

@SuppressWarnings("serial")
public class FourthCard extends JFrame {
	private JPanel firstPanel;
	private JPanel secondPanel;

	private JProgressBar progressBar;

	private JLabel labelChoosePath;

	private JButton buttonFinish;

	private Font firstFont;

	public FourthCard(Installation installation) {
		if (MainFrameInstaFram.getInstance() == null)
			return;

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0, 0, screenSize.width * 3 / 5, screenSize.height * 3 / 5);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		firstPanel = new JPanel();
		secondPanel = new JPanel();

		progressBar = new JProgressBar(0, 100);

		labelChoosePath = new JLabel(Localisation.getLocalisedString("mInstallationProgress"));

		buttonFinish = new JButton(Localisation.getLocalisedString("mButtonFinish"));
		buttonFinish.setEnabled(false);

		firstFont = new Font("Calibri", Font.BOLD, 20);

		setLocationRelativeTo(null);
		setVisible(true);
		setTitle("Installer");
		setResizable(false);

		labelChoosePath.setFont(firstFont);
		firstPanel.setLayout(new GridBagLayout());

		GridBagConstraints girdBagContraint = new GridBagConstraints();
		girdBagContraint.insets = new Insets(5, 5, 5, 5);

		progressBar.setPreferredSize(new Dimension(900, 25));

		girdBagContraint.gridx = 0;
		girdBagContraint.gridy = 0;
		firstPanel.add(labelChoosePath, girdBagContraint);

		girdBagContraint.gridx = 0;
		girdBagContraint.gridy = 1;
		firstPanel.add(progressBar, girdBagContraint);

		girdBagContraint.gridx = 0;
		girdBagContraint.gridy = 2;

		secondPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10));
		secondPanel.add(buttonFinish);

		buttonFinish.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			@Override
			public void actionPerformed(ActionEvent event) {
				if (installation.isRunAfterInstallation()) {
					Desktop desktop = Desktop.getDesktop();

					if (desktop.isDesktopSupported()) {
						File file = new File(installation.getEntryPoint());

						if (file.exists())
							try {
								desktop.open(file);
							} catch (IOException e) {
								e.printStackTrace();
							}
					}
				}

				// Mozda ne radi na MacOS-u i Linuxu
				// Ako treba ukljucicu na odbrani
				// Na Win-u radi
				if (installation.isCreateShortcut()) {
					/*new ShorcutMaker(installation.getEntryPoint())
							.createDesktopShortcut(Paths.get(installation.getEntryPoint()).getFileName().toString());*/
				}

				setVisible(false);
				dispose();
			}
		});

		this.add(firstPanel);
		this.add(secondPanel, BorderLayout.SOUTH);

		try {
			installation.install(progressBar, new InstallationProgress() {
				@Override
				public void done() {
					if (installation.getTimer() != null)
						installation.getTimer().stop();

					MainFrameInstaFram.getInstance().getExceptionsManager()
							.installationDone(installation.getProduct().getName());
					buttonFinish.setEnabled(true);
				}
			});
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
