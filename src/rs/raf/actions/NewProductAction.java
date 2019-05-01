package rs.raf.actions;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import rs.raf.actions.manager.InstaFramAbstractAction;
import rs.raf.gui.MainFrameInstaFram;
import rs.raf.gui.components.tree.InstaFramTree;
import rs.raf.model.InstaFramCompany;
import rs.raf.model.InstaFramNode;
import rs.raf.model.InstaFramProduct;
import rs.raf.settings.Settings;
import rs.raf.utils.Localisation;
import rs.raf.utils.NodeHelper;

@SuppressWarnings("serial")
public class NewProductAction extends InstaFramAbstractAction {
	public NewProductAction() {
		putValue(SMALL_ICON, getIcon(Settings.ICON_NEW));
		putValue(NAME, Localisation.getLocalisedString("mNewProduct"));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String newNodeName = JOptionPane.showInputDialog(MainFrameInstaFram.getInstance(),
				Localisation.getLocalisedString("mProductNameLabel"));

		if (newNodeName == null)
			return;

		InstaFramProduct newProduct = new InstaFramProduct(newNodeName);

		InstaFramTree tree = MainFrameInstaFram.getInstance().getTree();
		Object selectedNode = tree.getLastSelectedPathComponent();

		if (!(selectedNode instanceof InstaFramNode))
			return;

		InstaFramNode node = (InstaFramNode) selectedNode;

		if (node != null) {
			if (!(node instanceof InstaFramCompany)) {
				MainFrameInstaFram.getInstance().getExceptionsManager().nodeCreationException("mProduct", "mCompany");
				return;
			}

			if (NodeHelper.isDuplicate(node, newProduct)) {
				MainFrameInstaFram.getInstance().getExceptionsManager().duplicateException(newProduct.getName());
				return;
			}

			newProduct.setParent(node);
			((InstaFramCompany) node).addProduct(newProduct);
		}

		SwingUtilities.updateComponentTreeUI(tree);
		MainFrameInstaFram.getInstance().getTree()
				.expandPath(MainFrameInstaFram.getInstance().getTree().getSelectionPath());
		MainFrameInstaFram.getInstance().setSaved(false);
	}

}
