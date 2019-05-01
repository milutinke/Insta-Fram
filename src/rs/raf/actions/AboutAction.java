package rs.raf.actions;

import java.awt.event.ActionEvent;

import rs.raf.actions.manager.InstaFramAbstractAction;
import rs.raf.gui.MainFrameInstaFram;
import rs.raf.gui.dialogs.AboutDialog;
import rs.raf.utils.Localisation;

@SuppressWarnings("serial")
public class AboutAction extends InstaFramAbstractAction {
	public AboutAction() {
		putValue(NAME, Localisation.getLocalisedString("mAboutInstaFram"));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		AboutDialog dialog = new AboutDialog(MainFrameInstaFram.getInstance(), "About InstaFram", true);
		dialog.setVisible(true);
	}

}
