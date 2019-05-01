package rs.raf.actions;

import java.awt.event.ActionEvent;

import rs.raf.actions.manager.InstaFramAbstractAction;
import rs.raf.settings.Settings;
import rs.raf.utils.AcceleratorHelper;
import rs.raf.utils.Localisation;

@SuppressWarnings("serial")
public class SwitchAction extends InstaFramAbstractAction {
	public SwitchAction() {
		putValue(ACCELERATOR_KEY, AcceleratorHelper.getFormatedAccelerator('d'));
		putValue(SMALL_ICON, getIcon(Settings.ICON_SWITCH));
		putValue(NAME, Localisation.getLocalisedString("mSwitch"));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
