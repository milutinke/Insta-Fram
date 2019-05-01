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

public class InstaFramModule extends Observable implements InstaFramNode {
	private static final long serialVersionUID = 1364258037686015325L;
	private String name;
	private ArrayList<InstaFramParameter> parameters = new ArrayList<InstaFramParameter>();
	private ArrayList<Observer> observers = new ArrayList<Observer>();
	private InstaFramNode parent = null;
	private InstaFramNode userObject;

	static public DataFlavor elementFlavor;

	public InstaFramModule(String name) {
		this.setName(name);

		elementFlavor = new DataFlavor(InstaFramParameter.class, "Parameters");
	}

	public void addParameter(InstaFramParameter parameter) {
		if (parameters.contains(parameter))
			return;

		parameter.setParent(this);
		parameters.add(parameter);
		notifyAllObservers();
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public Enumeration<InstaFramParameter> children() {
		return Collections.enumeration(parameters);
	}

	@Override
	public boolean getAllowsChildren() {
		return true;
	}

	@Override
	public TreeNode getChildAt(int childIndex) {
		return parameters.get(childIndex);
	}

	@Override
	public int getChildCount() {
		return parameters.size();
	}

	@Override
	public int getIndex(TreeNode node) {
		for (int index = 0; index < parameters.size(); index++) {
			if (parameters.get(index).equals((InstaFramNode) node))
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
		return parameters.size() == 0;
	}

	@Override
	public void insert(MutableTreeNode child, int index) {
		parameters.add(index, (InstaFramParameter) child);
		notifyAllObservers();
	}

	@Override
	public void remove(int index) {
		parameters.remove(index);
		notifyAllObservers();
	}

	@Override
	public void remove(MutableTreeNode node) {
		parameters.remove(node);
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
		this.userObject = (InstaFramNode) object;
	}

	public Object getUserObject() {
		return userObject;
	}

	@Override
	public void setName(String name) {
		this.name = name;

		notifyAllObservers();
	}

	@Override
	public String getName() {
		return name;
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
	public Object getTransferData(DataFlavor arg0) throws UnsupportedFlavorException, IOException {
		return parameters;
	}

	@Override
	public DataFlavor[] getTransferDataFlavors() {
		DataFlavor[] supportedFlavors = { elementFlavor };
		return supportedFlavors;
	}

	@Override
	public boolean isDataFlavorSupported(DataFlavor arg0) {
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
