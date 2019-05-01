package rs.raf.actions;

import java.awt.event.ActionEvent;

import rs.raf.actions.manager.InstaFramAbstractAction;
import rs.raf.gui.MainFrameInstaFram;
import rs.raf.gui.dialogs.Preview;
import rs.raf.settings.Settings;
import rs.raf.utils.AcceleratorHelper;
import rs.raf.utils.Installation;
import rs.raf.utils.Localisation;
import rs.raf.utils.SaveLoadHelper;

@SuppressWarnings("serial")
public class PreviewAction extends InstaFramAbstractAction {
	public PreviewAction() {
		putValue(ACCELERATOR_KEY, AcceleratorHelper.getFormatedAccelerator('p'));
		putValue(SMALL_ICON, getIcon(Settings.ICON_PREVIEW));
		putValue(NAME, Localisation.getLocalisedString("mPreview"));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (MainFrameInstaFram.getInstance().getCurrentProduct() != null) {
			if (SaveLoadHelper.getProjectEntryPoint(MainFrameInstaFram.getInstance().getCurrentProduct()).isEmpty()) {
				MainFrameInstaFram.getInstance().getExceptionsManager().previewMissingEntryPoint();
				return;
			}

			if (!MainFrameInstaFram.getInstance().getCurrentProductPath().isEmpty())
				new Preview(new Installation(MainFrameInstaFram.getInstance().getCurrentProduct()));
			else
				MainFrameInstaFram.getInstance().getExceptionsManager().previewMustOpenProject();
		} else
			MainFrameInstaFram.getInstance().getExceptionsManager().previewMustOpenProject();
	}
}
