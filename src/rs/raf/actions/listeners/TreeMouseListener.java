package rs.raf.actions.listeners;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.tree.MutableTreeNode;

import rs.raf.gui.MainFrameInstaFram;
import rs.raf.gui.components.panels.CustomParameterView;
import rs.raf.gui.components.panels.PredefinedParameterView;
import rs.raf.gui.components.panels.RightPanel;
import rs.raf.gui.components.tree.InstaFramTree;
import rs.raf.model.InstaFramCustomParameter;
import rs.raf.model.InstaFramNode;
import rs.raf.model.InstaFramParameter;
import rs.raf.model.InstaFramPredefinedParameter;

public class TreeMouseListener extends MouseAdapter {
	public void mousePressed(MouseEvent e) {
		InstaFramTree tree = MainFrameInstaFram.getInstance().getTree();
		InstaFramNode selectedNode = (InstaFramNode) tree.getLastSelectedPathComponent();

		if (selectedNode != null) {
			if (e.getClickCount() == 2) {
				((InstaFramNode) selectedNode).notifyAllObservers();

				if (!(selectedNode instanceof InstaFramParameter))
					return;

				int tabIndex = findTabByName(selectedNode.getName(), MainFrameInstaFram.getInstance().getRightPanel());
				
				if (tabIndex != -1) {
					MainFrameInstaFram.getInstance().getRightPanel().setSelectedIndex(tabIndex);
					return;
				}

				MainFrameInstaFram.getInstance().getRightPanel().addTab(selectedNode.getName(), selectedNode,
						makeNewTabComponent(selectedNode));
				return;
			}

			if (SwingUtilities.isRightMouseButton(e))
				tree.getPopup().show(tree, e.getX(), e.getY());
		}
	}

	private JComponent makeNewTabComponent(MutableTreeNode node) {
		return (node instanceof InstaFramPredefinedParameter)
				? new PredefinedParameterView((InstaFramPredefinedParameter) node)
				: new CustomParameterView((InstaFramCustomParameter) node);

	}

	public int findTabByName(String title, RightPanel tab) {
		int tabCount = tab.getTabCount();
		
		for (int index = 0; index< tabCount; index++) {
			String tabTitle = tab.getTitleAt(index);
			
			if (tabTitle.trim().equals(title))
				return index;
		}
		
		return -1;
	}
}
