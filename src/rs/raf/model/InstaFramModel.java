package rs.raf.model;

import java.io.Serializable;

import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import rs.raf.gui.MainFrameInstaFram;

public class InstaFramModel extends DefaultTreeModel implements Serializable, Cloneable {
	private static final long serialVersionUID = 5678217618262182519L;

	public InstaFramModel(InstaFramWorkspace workspace) {
		super(workspace);
	}

	public void addCompany(InstaFramCompany company) {
		((InstaFramWorkspace) getRoot()).addCompany(company);
	}

	@Override
	public void valueForPathChanged(TreePath path, Object newValue) {
		InstaFramNode node = (InstaFramNode) ((InstaFramNode) path.getLastPathComponent());

		if (node != null && newValue != null) {
			if (newValue.toString().length() == 0)
				return;

			node.setName(newValue.toString());
			super.valueForPathChanged(path, node);
			MainFrameInstaFram.getInstance().setSaved(false);
		}
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}