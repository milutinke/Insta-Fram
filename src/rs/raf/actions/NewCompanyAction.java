package rs.raf.actions;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import rs.raf.actions.manager.InstaFramAbstractAction;
import rs.raf.gui.MainFrameInstaFram;
import rs.raf.gui.components.tree.InstaFramTree;
import rs.raf.model.InstaFramCompany;
import rs.raf.model.InstaFramNode;
import rs.raf.model.InstaFramWorkspace;
import rs.raf.settings.Settings;
import rs.raf.utils.Localisation;
import rs.raf.utils.NodeHelper;

@SuppressWarnings("serial")
public class NewCompanyAction extends InstaFramAbstractAction {
	public NewCompanyAction() {
		putValue(SMALL_ICON, getIcon(Settings.ICON_NEW));
		putValue(NAME, Localisation.getLocalisedString("mNewCompany"));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String newNodeName = JOptionPane.showInputDialog(MainFrameInstaFram.getInstance(),
				Localisation.getLocalisedString("mCompanyNameLabel"));

		if (newNodeName == null)
			return;

		InstaFramCompany newCompany = new InstaFramCompany(newNodeName);

		InstaFramTree tree = MainFrameInstaFram.getInstance().getTree();
		Object selectedNode = tree.getLastSelectedPathComponent();

		if (!(selectedNode instanceof InstaFramNode))
			return;

		InstaFramNode node = (InstaFramNode) selectedNode;

		if (node != null) {
			if (!(node instanceof InstaFramWorkspace)) {
				MainFrameInstaFram.getInstance().getExceptionsManager().nodeCreationException("mCompany", "mWorkspace");
				return;
			}

			if (NodeHelper.isDuplicate(node, newCompany)) {
				MainFrameInstaFram.getInstance().getExceptionsManager().duplicateException(newCompany.getName());
				return;
			}

			newCompany.setParent(node);
			((InstaFramWorkspace) node).addCompany(newCompany);
		}

		SwingUtilities.updateComponentTreeUI(tree);
		MainFrameInstaFram.getInstance().getTree()
				.expandPath(MainFrameInstaFram.getInstance().getTree().getSelectionPath());
		MainFrameInstaFram.getInstance().setSaved(false);
	}

}
