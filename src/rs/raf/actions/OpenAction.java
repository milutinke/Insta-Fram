package rs.raf.actions;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;

import rs.raf.actions.manager.InstaFramAbstractAction;
import rs.raf.gui.MainFrameInstaFram;
import rs.raf.gui.components.tree.InstaFramTree;
import rs.raf.model.InstaFramCompany;
import rs.raf.model.InstaFramProduct;
import rs.raf.model.filters.ProjectFileFilter;
import rs.raf.settings.Settings;
import rs.raf.utils.AcceleratorHelper;
import rs.raf.utils.Localisation;
import rs.raf.utils.SaveLoadHelper;

@SuppressWarnings("serial")
public class OpenAction extends InstaFramAbstractAction {
	public OpenAction() {
		putValue(ACCELERATOR_KEY, AcceleratorHelper.getFormatedAccelerator('o'));
		putValue(SMALL_ICON, getIcon(Settings.ICON_OPEN));
		putValue(NAME, Localisation.getLocalisedString("mOpen"));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		InstaFramTree tree = MainFrameInstaFram.getInstance().getTree();

		if (tree == null) {
			MainFrameInstaFram.getInstance().getExceptionsManager().loadType();
			return;
		}

		Object selectedNode = tree.getLastSelectedPathComponent();

		if (selectedNode == null) {
			MainFrameInstaFram.getInstance().getExceptionsManager().loadType();
			return;
		}

		if (!(selectedNode instanceof InstaFramCompany)) {
			MainFrameInstaFram.getInstance().getExceptionsManager().loadType();
			return;
		}

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileFilter(new ProjectFileFilter());
		fileChooser.setDialogTitle(Localisation.getLocalisedString("mSelectAFileTitle"));
		fileChooser.setMultiSelectionEnabled(false);

		int choosen = fileChooser.showOpenDialog(MainFrameInstaFram.getInstance());

		if (choosen != JFileChooser.APPROVE_OPTION)
			return;

		File file = fileChooser.getSelectedFile();

		if (file == null)
			return;

		try {
			InstaFramProduct product = SaveLoadHelper.deserializeTree(file.getAbsolutePath());

			if (product == null) {
				MainFrameInstaFram.getInstance().getExceptionsManager().loadFailed();
				return;
			}

			((InstaFramCompany) selectedNode).addProduct(product);
			MainFrameInstaFram.getInstance().setCurrentProduct(product);
			MainFrameInstaFram.getInstance().setCurrentProductPath(file.getAbsolutePath().replace(file.getName(), "").trim());
			MainFrameInstaFram.getInstance().getExceptionsManager().loadSucceded(product.getName());
		} catch (ClassNotFoundException | IOException e1) {
			e1.printStackTrace();
		}

		SwingUtilities.updateComponentTreeUI(tree);
		MainFrameInstaFram.getInstance().getTree()
				.expandPath(MainFrameInstaFram.getInstance().getTree().getSelectionPath());
		MainFrameInstaFram.getInstance().setSaved(false);

		while (MainFrameInstaFram.getInstance().getRightPanel().getTabCount() > 0)
			MainFrameInstaFram.getInstance().getRightPanel().remove(0);
	}
}
