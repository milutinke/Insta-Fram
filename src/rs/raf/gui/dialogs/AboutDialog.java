package rs.raf.gui.dialogs;

import java.awt.BorderLayout;
import java.awt.Frame;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;

import rs.raf.settings.Settings;

public class AboutDialog extends JDialog {
	private static final long serialVersionUID = 218774141767210273L;

	public AboutDialog(Frame parent, String title, boolean modal) {
		super(parent, title, modal);
		setResizable(false);
		setSize(300, 200);
		setLocationRelativeTo(parent);
		setLayout(new BorderLayout());

		JLabel aboutLabel = new JLabel("Author: Dusan Milutinovic - RN 105/18 - Group: 205");
		add(aboutLabel, BorderLayout.NORTH);

		JLabel image = new JLabel(new ImageIcon(Settings.ICON_FOLDER_PATH + Settings.AUTOR_IMAGE));
		image.setToolTipText("<html><b><span style='color: green;'>Al' sam lep xD</span></b></html>");
		add(image, BorderLayout.CENTER);
	}
}
