package rs.raf.actions;

import java.awt.event.ActionEvent;

import rs.raf.actions.manager.InstaFramAbstractAction;
import rs.raf.settings.Settings;
import rs.raf.utils.AcceleratorHelper;
import rs.raf.utils.Localisation;

@SuppressWarnings("serial")
public class ExportToExcelAction extends InstaFramAbstractAction {
	public ExportToExcelAction() {
		putValue(ACCELERATOR_KEY, AcceleratorHelper.getFormatedAccelerator('q'));
		putValue(SMALL_ICON, getIcon(Settings.ICON_EXCEL));
		putValue(NAME, Localisation.getLocalisedString("mExpToMsExcel"));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}

}
