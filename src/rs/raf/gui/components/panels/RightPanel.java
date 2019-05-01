package rs.raf.gui.components.panels;

import java.awt.Color;
import java.util.Enumeration;

import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.tree.MutableTreeNode;

import rs.raf.gui.MainFrameInstaFram;
import rs.raf.gui.components.other.ClosableTabbedPane;
import rs.raf.model.InstaFramModel;
import rs.raf.model.InstaFramNode;

@SuppressWarnings("serial")
public class RightPanel extends ClosableTabbedPane {
	public RightPanel() {
		setBackground(Color.WHITE);

		addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent changeEvent) {
				JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent.getSource();
				int index = sourceTabbedPane.getSelectedIndex();

				// Bugfix
				if (index < 0)
					return;

				String name = sourceTabbedPane.getTitleAt(index);

				if (name == null)
					return;

				InstaFramModel model = (InstaFramModel) MainFrameInstaFram.getInstance().getTree().getModel();

				if (model == null)
					return;

				InstaFramNode foundNode = (InstaFramNode) findNode((MutableTreeNode) model.getRoot(), name.trim());

				if (foundNode != null)
					foundNode.notifyAllObservers();
			}
		});
	}

	public static MutableTreeNode findNode(MutableTreeNode root, String name) {
		if (root.toString().trim().contains(name))
			return root;

		MutableTreeNode found = null;
		@SuppressWarnings("unchecked")
		Enumeration<MutableTreeNode> children = root.children();

		if (children != null) {
			while (children.hasMoreElements()) {
				found = findNode((MutableTreeNode) children.nextElement(), name);

				if (found != null)
					return found;
			}
		}

		return found;
	}
}
