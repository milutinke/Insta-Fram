package rs.raf.actions;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import rs.raf.actions.manager.InstaFramAbstractAction;
import rs.raf.gui.MainFrameInstaFram;
import rs.raf.gui.components.tree.InstaFramTree;
import rs.raf.model.InstaFramModule;
import rs.raf.model.InstaFramNode;
import rs.raf.model.InstaFramProduct;
import rs.raf.settings.Settings;
import rs.raf.utils.Localisation;
import rs.raf.utils.NodeHelper;

@SuppressWarnings("serial")
public class NewModuleAction extends InstaFramAbstractAction {
	public NewModuleAction() {
		putValue(SMALL_ICON, getIcon(Settings.ICON_NEW));
		putValue(NAME, Localisation.getLocalisedString("mNewModule"));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String newNodeName = JOptionPane.showInputDialog(MainFrameInstaFram.getInstance(),
				Localisation.getLocalisedString("mModuleNameLabel"));

		if (newNodeName == null)
			return;

		InstaFramModule newModule = new InstaFramModule(newNodeName);

		InstaFramTree tree = MainFrameInstaFram.getInstance().getTree();
		Object selectedNode = tree.getLastSelectedPathComponent();

		if (!(selectedNode instanceof InstaFramNode))
			return;

		InstaFramNode node = (InstaFramNode) selectedNode;

		if (node != null) {
			if (!(node instanceof InstaFramProduct)) {
				MainFrameInstaFram.getInstance().getExceptionsManager().nodeCreationException("mModule", "mProduct");
				return;
			}

			if (NodeHelper.isDuplicate(node, newModule)) {
				MainFrameInstaFram.getInstance().getExceptionsManager().duplicateException(newModule.getName());
				return;
			}

			newModule.setParent(node);
			((InstaFramProduct) node).addNode(newModule);
		}

		SwingUtilities.updateComponentTreeUI(tree);
		MainFrameInstaFram.getInstance().getTree()
				.expandPath(MainFrameInstaFram.getInstance().getTree().getSelectionPath());
		MainFrameInstaFram.getInstance().setSaved(false);
	}

}
