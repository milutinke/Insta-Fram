package rs.raf.model;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Observable;
import java.util.Observer;

import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

public class InstaFramProduct extends Observable implements InstaFramNode {
	private static final long serialVersionUID = 1238950322562915890L;
	private String name;
	private ArrayList<InstaFramNode> nodes = new ArrayList<InstaFramNode>();
	private ArrayList<Observer> observers = new ArrayList<Observer>();
	private InstaFramNode parent = null;

	static public DataFlavor[] elementFlavors = { null, null };

	public InstaFramProduct(String name) {
		this.setName(name);

		elementFlavors[0] = new DataFlavor(InstaFramParameter.class, "Parameters");
		elementFlavors[1] = new DataFlavor(InstaFramModule.class, "Modules");
	}

	@Override
	public String toString() {
		return name;
	}

	public void addNode(InstaFramNode node) {
		if(nodes.contains(node))
			return;
		
		node.setParent(this);
		nodes.add(node);
		notifyAllObservers();
	}

	@Override
	public Enumeration<InstaFramNode> children() {
		return Collections.enumeration(nodes);
	}

	@Override
	public boolean getAllowsChildren() {
		return true;
	}

	@Override
	public TreeNode getChildAt(int childIndex) {
		return nodes.get(childIndex);
	}

	@Override
	public int getChildCount() {
		return nodes.size();
	}

	@Override
	public int getIndex(TreeNode node) {
		for (int index = 0; index < nodes.size(); index++) {
			if (nodes.get(index).equals((InstaFramNode) node))
				return index;
		}

		return -1;
	}

	@Override
	public TreeNode getParent() {
		return parent;
	}

	@Override
	public boolean isLeaf() {
		return nodes.size() == 0;
	}

	@Override
	public void insert(MutableTreeNode child, int index) {
		nodes.add(index, (InstaFramNode) child);
		notifyAllObservers();
	}

	@Override
	public void remove(int index) {
		nodes.remove(index);
		notifyAllObservers();
	}

	@Override
	public void remove(MutableTreeNode node) {
		nodes.remove(node);
		notifyAllObservers();
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
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
		notifyAllObservers();
	}

	public ArrayList<Observer> getObservers() {
		return observers;
	}

	public void notifyAllObservers() {
		for (Observer observer : observers)
			observer.update(this, null);
	}

	public void addObserver(Observer observer) {
		if (observers.contains(observer))
			return;

		observers.add(observer);
	}

	@Override
	public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
		return nodes;
	}

	@Override
	public DataFlavor[] getTransferDataFlavors() {
		DataFlavor[] supportedFlavors = { elementFlavors[0], elementFlavors[1] };

		return supportedFlavors;
	}

	@Override
	public boolean isDataFlavorSupported(DataFlavor flavor) {
		return true;
	}

	@Override
	public void lostOwnership(Clipboard arg0, Transferable arg1) {
		System.out.println(this + " has lost ownership!");
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
