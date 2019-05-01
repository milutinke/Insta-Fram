package rs.raf.model;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Observable;
import java.util.Observer;

import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

public class InstaFramCompany extends Observable implements InstaFramNode, Serializable {
	private static final long serialVersionUID = 8215862550632106514L;
	private String name;
	private ArrayList<InstaFramProduct> products = new ArrayList<InstaFramProduct>();
	private ArrayList<Observer> observers = new ArrayList<Observer>();
	private InstaFramNode parent = null;

	static public DataFlavor elementFlavor;

	public InstaFramCompany(String name) {
		this.setName(name);

		elementFlavor = new DataFlavor(InstaFramProduct.class, "Products");
	}

	public void addProduct(InstaFramProduct product) {
		if (products.contains(product))
			return;

		product.setParent(this);
		products.add(product);
		notifyAllObservers();
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public Enumeration<InstaFramProduct> children() {
		return Collections.enumeration(products);
	}

	@Override
	public boolean getAllowsChildren() {
		return true;
	}

	@Override
	public TreeNode getChildAt(int childIndex) {
		return products.get(childIndex);
	}

	@Override
	public int getChildCount() {
		return products.size();
	}

	@Override
	public int getIndex(TreeNode node) {
		for (int index = 0; index < products.size(); index++) {
			if (products.get(index).equals((InstaFramProduct) node))
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
		return false;
	}

	@Override
	public void insert(MutableTreeNode child, int index) {
		products.add(index, (InstaFramProduct) child);
		notifyAllObservers();
	}

	@Override
	public void remove(int index) {
		products.remove(index);
		notifyAllObservers();
	}

	@Override
	public void remove(MutableTreeNode child) {
		products.remove(child);
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

	public String getName() {
		return name;
	}

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
		return products;
	}

	@Override
	public DataFlavor[] getTransferDataFlavors() {
		DataFlavor[] supportedFlavors = { elementFlavor };
		return supportedFlavors;
	}

	@Override
	public boolean isDataFlavorSupported(DataFlavor flavor) {
		return true;
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
