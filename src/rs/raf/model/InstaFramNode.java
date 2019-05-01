package rs.raf.model;

import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;
import java.io.Serializable;

import javax.swing.tree.MutableTreeNode;

public interface InstaFramNode extends MutableTreeNode, Serializable, Transferable, ClipboardOwner, Cloneable {
	public void setName(String name);

	public String getName();

	public void notifyAllObservers();
}
