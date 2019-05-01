package rs.raf.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.tree.TreePath;

import rs.raf.gui.MainFrameInstaFram;
import rs.raf.gui.components.tree.InstaFramTree;

public class RenameNodeAction implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		InstaFramTree tree = MainFrameInstaFram.getInstance().getTree();
		TreePath path = tree.getSelectionPath();
		
		if (path != null) {
			tree.startEditingAtPath(path);
		}
	}
}
