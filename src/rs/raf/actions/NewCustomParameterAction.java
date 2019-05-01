package rs.raf.actions;

import java.awt.event.ActionEvent;

import rs.raf.actions.manager.InstaFramAbstractAction;
import rs.raf.gui.MainFrameInstaFram;
import rs.raf.gui.components.tree.InstaFramTree;
import rs.raf.gui.dialogs.CustomParameterDialog;
import rs.raf.model.InstaFramModule;
import rs.raf.model.InstaFramProduct;
import rs.raf.settings.Settings;
import rs.raf.utils.Localisation;

@SuppressWarnings("serial")
public class NewCustomParameterAction extends InstaFramAbstractAction {
	public NewCustomParameterAction() {
		putValue(SMALL_ICON, getIcon(Settings.ICON_NEW));
		putValue(NAME, Localisation.getLocalisedString("mNewCustomParameter"));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		InstaFramTree tree = MainFrameInstaFram.getInstance().getTree();
		Object selectedNode = tree.getLastSelectedPathComponent();

		if (!(selectedNode instanceof InstaFramModule || selectedNode instanceof InstaFramProduct)) {
			MainFrameInstaFram.getInstance().getExceptionsManager().nodeCreationException("mCustomParameter", "mModuleOrProduct");
			return;
		}

		new CustomParameterDialog();
	}
}
