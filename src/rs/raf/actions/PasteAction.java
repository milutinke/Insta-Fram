package rs.raf.actions;

import java.awt.event.ActionEvent;

import javax.swing.SwingUtilities;

import rs.raf.actions.manager.InstaFramAbstractAction;
import rs.raf.gui.MainFrameInstaFram;
import rs.raf.gui.components.tree.InstaFramTree;
import rs.raf.model.InstaFramModule;
import rs.raf.model.InstaFramNode;
import rs.raf.model.InstaFramParameter;
import rs.raf.model.InstaFramProduct;
import rs.raf.settings.Settings;
import rs.raf.utils.Localisation;

@SuppressWarnings("serial")
public class PasteAction extends InstaFramAbstractAction {
	public PasteAction() {
		putValue(SMALL_ICON, getIcon(Settings.ICON_PASTE));
		putValue(NAME, Localisation.getLocalisedString("mPaste"));
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		InstaFramTree tree = MainFrameInstaFram.getInstance().getTree();
		Object selectedNode = tree.getLastSelectedPathComponent();

		if (!(selectedNode instanceof InstaFramNode))
			return;

		InstaFramNode node = (InstaFramNode) selectedNode;

		if (node != null) {
			Object clipboardContent = MainFrameInstaFram.getInstance().getClipboard()
					.getContents(MainFrameInstaFram.getInstance());

			if (clipboardContent == null)
				return;

			if (!(clipboardContent instanceof InstaFramNode))
				return;

			if (node instanceof InstaFramProduct) {
				if (!(clipboardContent instanceof InstaFramParameter || clipboardContent instanceof InstaFramModule))
					return;

				if (clipboardContent instanceof InstaFramParameter)
					((InstaFramProduct) node).addNode((InstaFramParameter) clipboardContent);
				else
					((InstaFramProduct) node).addNode((InstaFramModule) clipboardContent);
			} else if (node instanceof InstaFramModule) {
				if (!(clipboardContent instanceof InstaFramParameter))
					return;

				((InstaFramModule) node).addParameter((InstaFramParameter) clipboardContent);
			}
		}

		SwingUtilities.updateComponentTreeUI(tree);
		MainFrameInstaFram.getInstance().setSaved(false);
	}
}
