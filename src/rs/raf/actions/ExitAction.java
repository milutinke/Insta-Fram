package rs.raf.actions;

import java.awt.event.ActionEvent;

import rs.raf.actions.manager.InstaFramAbstractAction;
import rs.raf.gui.MainFrameInstaFram;
import rs.raf.settings.Settings;
import rs.raf.utils.AcceleratorHelper;
import rs.raf.utils.Localisation;

@SuppressWarnings({ "serial" })
public class ExitAction extends InstaFramAbstractAction {
	public ExitAction() {
		putValue(ACCELERATOR_KEY, AcceleratorHelper.getFormatedAccelerator('e'));
		putValue(SMALL_ICON, getIcon(Settings.ICON_CLOSE));
		putValue(NAME, Localisation.getLocalisedString("mExit"));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (!MainFrameInstaFram.getInstance().isSaved()) {
			if (MainFrameInstaFram.getInstance().getExceptionsManager().saveProjectWarning("mExit") == 1)
				return;
		}

		MainFrameInstaFram.getInstance().dispose();
		System.exit(0);
	}
}
