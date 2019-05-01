package rs.raf.model;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.Enumeration;

import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

public class InstaFramParameter implements InstaFramNode {
	private static final long serialVersionUID = 2971816256370177310L;

	private String name;

	private String description;
	private String type;
	private String value;

	private InstaFramNode parent = null;

	public InstaFramParameter(String paramName) {
		this.name = paramName;
	}

	public InstaFramParameter(String name, String description, String type, String value) {
		this.name = name;
		this.description = description;
		this.type = type;
		this.value = value;
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	public MutableTreeNode getChildAt(int arg0) {
		return null;
	}

	public int getChildCount() {
		return 0;
	}

	public MutableTreeNode getParent() {
		return parent;
	}

	public int getIndex(MutableTreeNode arg0) {
		return -1;
	}

	public boolean getAllowsChildren() {
		return false;
	}

	public boolean isLeaf() {
		return true;
	}

	public Enumeration<?> children() {
		return null;
	}

	public String getDescription() {
		return description;
	}

	public String getType() {
		return type;
	}

	public String getValue() {
		return value;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public int getIndex(TreeNode arg0) {
		return 0;
	}

	@Override
	public void insert(MutableTreeNode arg0, int arg1) {
	}

	@Override
	public void remove(int arg0) {
	}

	@Override
	public void remove(MutableTreeNode arg0) {
	}

	@Override
	public void removeFromParent() {
		if (parent != null)
			parent.remove(this);
	}

	@Override
	public void setParent(MutableTreeNode newParent) {
		this.parent = (InstaFramNode) newParent;
	}

	@Override
	public void setUserObject(Object object) {
	}

	@Override
	public void notifyAllObservers() {
	}

	@Override
	public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
		return this;
	}

	@Override
	public DataFlavor[] getTransferDataFlavors() {
		return null;
	}

	@Override
	public boolean isDataFlavorSupported(DataFlavor flavor) {
		return false;
	}

	@Override
	public void lostOwnership(Clipboard clipboard, Transferable contents) {
		System.out.println(this + " has lost ownership!");
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
