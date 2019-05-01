package rs.raf.actions;

import java.awt.event.ActionEvent;

import rs.raf.actions.manager.InstaFramAbstractAction;
import rs.raf.gui.MainFrameInstaFram;
import rs.raf.settings.Settings;
import rs.raf.utils.AcceleratorHelper;
import rs.raf.utils.Localisation;

@SuppressWarnings("serial")
public class UndoAction extends InstaFramAbstractAction {
	public UndoAction() {
		putValue(ACCELERATOR_KEY, AcceleratorHelper.getFormatedAccelerator('z'));
		putValue(SMALL_ICON, getIcon(Settings.ICON_UNDO));
		putValue(NAME, Localisation.getLocalisedString("mUndo"));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Undo");
		MainFrameInstaFram.getInstance().getCommandManager().undoCommand();
	}
}
