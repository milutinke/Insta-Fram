package rs.raf.actions;

import java.awt.event.ActionEvent;

import rs.raf.actions.manager.InstaFramAbstractAction;
import rs.raf.settings.Settings;
import rs.raf.utils.AcceleratorHelper;
import rs.raf.utils.Localisation;

@SuppressWarnings("serial")
public class ExportToWordAction extends InstaFramAbstractAction {
	public ExportToWordAction() {
		putValue(ACCELERATOR_KEY, AcceleratorHelper.getFormatedAccelerator('r'));
		putValue(SMALL_ICON, getIcon(Settings.ICON_WORD));
		putValue(NAME, Localisation.getLocalisedString("mExpToMsWord"));
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

}
