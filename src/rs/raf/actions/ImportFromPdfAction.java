package rs.raf.actions;

import java.awt.event.ActionEvent;

import rs.raf.actions.manager.InstaFramAbstractAction;
import rs.raf.settings.Settings;
import rs.raf.utils.AcceleratorHelper;
import rs.raf.utils.Localisation;

@SuppressWarnings("serial")
public class ImportFromPdfAction extends InstaFramAbstractAction {
	public ImportFromPdfAction() {
		putValue(ACCELERATOR_KEY, AcceleratorHelper.getFormatedAccelerator('g'));
		putValue(SMALL_ICON, getIcon(Settings.ICON_PDF));
		putValue(NAME, Localisation.getLocalisedString("mImpFromPdf"));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}
