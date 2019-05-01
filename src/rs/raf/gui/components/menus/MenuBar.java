package rs.raf.gui.components.menus;

import javax.swing.Box;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import rs.raf.gui.MainFrameInstaFram;
import rs.raf.utils.Localisation;

@SuppressWarnings("serial")
public class MenuBar extends JMenuBar {
	public MenuBar() {
		super();

		add(new FileMenu());
		add(new JMenu(Localisation.getLocalisedString("mView")));
		add(new JMenu(Localisation.getLocalisedString("mTools")));
		add(Box.createHorizontalGlue());
		add(new JMenu(Localisation.getLocalisedString("mParametrisation")));
		add(new JMenu(Localisation.getLocalisedString("mHelp")));

		JMenu aboutMenu = new JMenu(Localisation.getLocalisedString("mAbout"));
		aboutMenu.add(new JMenuItem(MainFrameInstaFram.getInstance().getActionManager().getAboutAction()));

		add(aboutMenu);
	}
}
