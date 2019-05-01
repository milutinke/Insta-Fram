package rs.raf.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.SwingUtilities;
import javax.swing.tree.TreePath;

import rs.raf.commands.DeleteCommand;
import rs.raf.gui.MainFrameInstaFram;
import rs.raf.gui.components.tree.InstaFramTree;
import rs.raf.model.InstaFramModel;
import rs.raf.model.InstaFramNode;
import rs.raf.model.InstaFramWorkspace;
import rs.raf.utils.NodeHelper;

@SuppressWarnings("serial")
public class DeleteNodeAction extends AbstractAction {
	@Override
	public void actionPerformed(ActionEvent e) {
		InstaFramTree tree = MainFrameInstaFram.getInstance().getTree();

		if (tree == null) {
			MainFrameInstaFram.getInstance().getExceptionsManager().nodeDelectionSelectException();
			return;
		}

		TreePath path = (TreePath) tree.getSelectionPath();

		if (path == null) {
			MainFrameInstaFram.getInstance().getExceptionsManager().nodeDelectionSelectException();
			return;
		}

		InstaFramNode node = (InstaFramNode) path.getLastPathComponent();

		if (node instanceof InstaFramWorkspace) {
			MainFrameInstaFram.getInstance().getExceptionsManager().workspaceDeletionException();
			return;
		}

		if (node.getChildCount() > 0) {
			if (MainFrameInstaFram.getInstance().getExceptionsManager().nodeDeletionQuestion(node.getName()) == 1)
				return;
		}

		MainFrameInstaFram.getInstance().getCommandManager()
				.addCommand(new DeleteCommand((InstaFramModel) MainFrameInstaFram.getInstance().getTree().getModel()));

		String removedNodes = "";
		removedNodes = NodeHelper.getAllRemovedChindren((InstaFramNode) node, removedNodes);

		String nodeName = node.getName();

		node.removeFromParent();
		tree.setSelectionPath(null);
		SwingUtilities.updateComponentTreeUI(MainFrameInstaFram.getInstance().getTree());
		MainFrameInstaFram.getInstance().setSaved(false);

		MainFrameInstaFram.getInstance().getExceptionsManager().successfullyDeletedNode(nodeName);
		NodeHelper.closeDeletedNodesTabs(removedNodes);
	}

}
