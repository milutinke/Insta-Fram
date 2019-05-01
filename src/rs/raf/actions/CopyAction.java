package rs.raf.actions;

import java.awt.event.ActionEvent;

import javax.swing.SwingUtilities;

import rs.raf.actions.manager.InstaFramAbstractAction;
import rs.raf.gui.MainFrameInstaFram;
import rs.raf.gui.components.tree.InstaFramTree;
import rs.raf.model.InstaFramCompany;
import rs.raf.model.InstaFramCustomParameter;
import rs.raf.model.InstaFramModule;
import rs.raf.model.InstaFramNode;
import rs.raf.model.InstaFramParameter;
import rs.raf.model.InstaFramPredefinedParameter;
import rs.raf.model.InstaFramProduct;
import rs.raf.model.InstaFramWorkspace;
import rs.raf.settings.Settings;
import rs.raf.utils.Localisation;

@SuppressWarnings("serial")
public class CopyAction extends InstaFramAbstractAction {
	public CopyAction() {
		putValue(SMALL_ICON, getIcon(Settings.ICON_COPY));
		putValue(NAME, Localisation.getLocalisedString("mCopy"));
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		InstaFramTree tree = MainFrameInstaFram.getInstance().getTree();
		Object selectedNode = tree.getLastSelectedPathComponent();

		if (!(selectedNode instanceof InstaFramNode))
			return;

		InstaFramNode node = (InstaFramNode) selectedNode;

		if (node != null) {
			if (node instanceof InstaFramWorkspace || node instanceof InstaFramCompany
					|| node instanceof InstaFramProduct) {
				MainFrameInstaFram.getInstance().getExceptionsManager().copyException();
				return;
			}

			if (node instanceof InstaFramModule) {
				try {
					MainFrameInstaFram.getInstance().getClipboard().setContents(
							(InstaFramModule) ((InstaFramModule) node).clone(), MainFrameInstaFram.getInstance());
				} catch (CloneNotSupportedException e) {
					e.printStackTrace();
				}
			} else if (node instanceof InstaFramParameter) {
				try {
					if (node instanceof InstaFramPredefinedParameter)
						MainFrameInstaFram.getInstance().getClipboard().setContents(
								(InstaFramPredefinedParameter) ((InstaFramPredefinedParameter) node).clone(),
								MainFrameInstaFram.getInstance());
					else
						MainFrameInstaFram.getInstance().getClipboard().setContents(
								(InstaFramCustomParameter) ((InstaFramCustomParameter) node).clone(),
								MainFrameInstaFram.getInstance());
				} catch (CloneNotSupportedException e) {
					e.printStackTrace();
				}
			}

			System.out.println(node.hashCode());
			System.out.println(MainFrameInstaFram.getInstance().getClipboard()
					.getContents(MainFrameInstaFram.getInstance()).hashCode());
		}

		SwingUtilities.updateComponentTreeUI(tree);
		MainFrameInstaFram.getInstance().setSaved(false);
	}

}
