package rs.raf.actions;

import java.awt.event.ActionEvent;

import rs.raf.actions.manager.InstaFramAbstractAction;
import rs.raf.settings.Settings;
import rs.raf.utils.AcceleratorHelper;
import rs.raf.utils.Localisation;

@SuppressWarnings("serial")
public class ImportFromWordAction extends InstaFramAbstractAction {
	public ImportFromWordAction() {
		putValue(ACCELERATOR_KEY, AcceleratorHelper.getFormatedAccelerator('k'));
		putValue(SMALL_ICON, getIcon(Settings.ICON_WORD));
		putValue(NAME, Localisation.getLocalisedString("mImpFromMsWord"));
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
}
