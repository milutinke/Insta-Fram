package rs.raf.gui.components.tree;

import javax.swing.JTree;

import rs.raf.actions.listeners.TreeMouseListener;
import rs.raf.model.InstaFramModel;
import rs.raf.model.InstaFramWorkspace;

@SuppressWarnings("serial")
public class InstaFramTree extends JTree {
	private TreePopup popup;

	public InstaFramTree() {
		setModel(new InstaFramModel(new InstaFramWorkspace("Workspace")));
		setEditable(true);

		popup = new TreePopup();

		addMouseListener(new TreeMouseListener());
	}

	public TreePopup getPopup() {
		return popup;
	}
}
