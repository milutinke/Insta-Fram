package rs.raf.actions;

import java.awt.event.ActionEvent;

import javax.swing.JFileChooser;

import rs.raf.actions.manager.InstaFramAbstractAction;
import rs.raf.gui.MainFrameInstaFram;
import rs.raf.gui.components.tree.InstaFramTree;
import rs.raf.model.InstaFramProduct;
import rs.raf.settings.Settings;
import rs.raf.utils.AcceleratorHelper;
import rs.raf.utils.Localisation;
import rs.raf.utils.SaveLoadHelper;

@SuppressWarnings("serial")
public class SaveAsAction extends InstaFramAbstractAction {
	public SaveAsAction() {
		putValue(ACCELERATOR_KEY, AcceleratorHelper.getFormatedAccelerator('t'));
		putValue(SMALL_ICON, getIcon(Settings.ICON_SAVE_AS));
		putValue(NAME, Localisation.getLocalisedString("mSaveAs"));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		InstaFramTree tree = MainFrameInstaFram.getInstance().getTree();

		if (tree == null) {
			MainFrameInstaFram.getInstance().getExceptionsManager().saveType();
			return;
		}

		Object selectedNode = tree.getLastSelectedPathComponent();

		if (selectedNode == null) {
			MainFrameInstaFram.getInstance().getExceptionsManager().saveType();
			return;
		}

		if (!(selectedNode instanceof InstaFramProduct)) {
			MainFrameInstaFram.getInstance().getExceptionsManager().saveType();
			return;
		}

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle(Localisation.getLocalisedString("mSaveAs"));
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		int userSelection = fileChooser.showSaveDialog(MainFrameInstaFram.getInstance());

		if (userSelection == JFileChooser.APPROVE_OPTION)
			SaveLoadHelper.saveProject(fileChooser.getSelectedFile(), (InstaFramProduct) selectedNode);
	}
}
