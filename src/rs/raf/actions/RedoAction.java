package rs.raf.actions;

import java.awt.event.ActionEvent;

import rs.raf.actions.manager.InstaFramAbstractAction;
import rs.raf.gui.MainFrameInstaFram;
import rs.raf.settings.Settings;
import rs.raf.utils.AcceleratorHelper;
import rs.raf.utils.Localisation;

@SuppressWarnings("serial")
public class RedoAction extends InstaFramAbstractAction {
	public RedoAction() {
		putValue(ACCELERATOR_KEY, AcceleratorHelper.getFormatedAccelerator('y'));
		putValue(SMALL_ICON, getIcon(Settings.ICON_REDO));
		putValue(NAME, Localisation.getLocalisedString("mRedo"));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Redo");
		MainFrameInstaFram.getInstance().getCommandManager().doCommand();
	}
}
