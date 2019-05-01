package rs.raf.gui.components.menus;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import rs.raf.settings.Settings;
import rs.raf.utils.Localisation;

public class MenuItem {
	private String name;
	private char mnemonic = ' ';
	private String icon;

	public MenuItem withName(String name) {
		this.name = name;
		return this;
	}

	public MenuItem withMnemonic(char mnemonic) {
		this.mnemonic = mnemonic;
		return this;
	}

	public MenuItem withIcon(String icon) {
		this.icon = icon;
		return this;
	}

	public JMenuItem build() {
		JMenuItem item = new JMenuItem(Localisation.getLocalisedString((name)));

		if (mnemonic != ' ') {
			String temp = "" + mnemonic;
			item.setAccelerator(KeyStroke.getKeyStroke("control " + temp.toUpperCase()));
		}

		if (!icon.isEmpty())
			item.setIcon(new ImageIcon( Settings.ICON_FOLDER_PATH + icon));

		return item;
	}
}
