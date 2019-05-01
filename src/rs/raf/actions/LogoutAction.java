package rs.raf.actions;

import java.awt.event.ActionEvent;
import java.io.File;

import rs.raf.Main;
import rs.raf.actions.manager.InstaFramAbstractAction;
import rs.raf.gui.MainFrameInstaFram;
import rs.raf.settings.Settings;
import rs.raf.utils.AcceleratorHelper;
import rs.raf.utils.Localisation;

@SuppressWarnings("serial")
public class LogoutAction extends InstaFramAbstractAction {
	public LogoutAction() {
		putValue(ACCELERATOR_KEY, AcceleratorHelper.getFormatedAccelerator('l'));
		putValue(SMALL_ICON, getIcon(Settings.ICON_LOGOUT));
		putValue(NAME, Localisation.getLocalisedString("mLogout"));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (!MainFrameInstaFram.getInstance().isSaved()) {
			if (MainFrameInstaFram.getInstance().getExceptionsManager().saveProjectWarning("mLogout") == 1)
				return;
		}
		
		File sessionFile = new File(System.getenv("APPDATA") + "/" + Settings.APP_SESSION_LOCATION);

		if(sessionFile.exists()) {
			sessionFile.delete();
		}
		
		MainFrameInstaFram.getInstance().logout();
		Main.main(null);
	}
}
