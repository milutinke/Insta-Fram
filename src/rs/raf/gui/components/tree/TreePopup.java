package rs.raf.gui.components.tree;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import rs.raf.gui.MainFrameInstaFram;
import rs.raf.utils.Localisation;

@SuppressWarnings("serial")
public class TreePopup extends JPopupMenu {
	private JMenuItem renameNode;
	private JMenuItem deleteNode;

	public TreePopup() {
		CreateItems();
		AddItems();
	}

	private void CreateItems() {
		renameNode = new JMenuItem(Localisation.getLocalisedString("mRenameNode"));
		renameNode.addActionListener(MainFrameInstaFram.getInstance().getActionManager().getRenameNodeAction());
		
		deleteNode = new JMenuItem(Localisation.getLocalisedString("mDeleteNode"));
		deleteNode.addActionListener(MainFrameInstaFram.getInstance().getActionManager().getDeleteNodeAction());
	}

	private void AddItems() {
		add(renameNode);
		addSeparator();
		add(deleteNode);
	}

	public JMenuItem getRenameNode() {
		return renameNode;
	}
}
