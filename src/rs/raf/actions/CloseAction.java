package rs.raf.actions;

import java.awt.event.ActionEvent;

import rs.raf.actions.manager.InstaFramAbstractAction;
import rs.raf.settings.Settings;
import rs.raf.utils.AcceleratorHelper;
import rs.raf.utils.Localisation;

@SuppressWarnings("serial")
public class CloseAction extends InstaFramAbstractAction {
	public CloseAction() {
		putValue(ACCELERATOR_KEY, AcceleratorHelper.getFormatedAccelerator('c'));
		putValue(SMALL_ICON, getIcon(Settings.ICON_CLOSE));
		putValue(NAME, Localisation.getLocalisedString("mClose"));
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

}
