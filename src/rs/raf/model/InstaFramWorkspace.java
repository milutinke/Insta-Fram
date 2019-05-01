package rs.raf.model;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;

import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

public class InstaFramWorkspace implements InstaFramNode {
	private static final long serialVersionUID = 8140435820090882906L;
	private ArrayList<InstaFramCompany> companies = new ArrayList<InstaFramCompany>();
	private String name;

	public InstaFramWorkspace(String name) {
		this.name = name;
	}

	public void addCompany(InstaFramCompany company) {
		companies.add(company);
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public Enumeration<InstaFramCompany> children() {
		return Collections.enumeration(companies);
	}

	@Override
	public boolean getAllowsChildren() {
		return true;
	}

	@Override
	public TreeNode getChildAt(int childIndex) {
		return companies.get(childIndex);
	}

	@Override
	public int getChildCount() {
		return companies.size();
	}

	@Override
	public int getIndex(TreeNode node) {
		for (int index = 0; index < companies.size(); index++) {
			if (companies.get(index).equals(node))
				return index;
		}

		return -1;
	}

	@Override
	public TreeNode getParent() {
		return null;
	}

	@Override
	public boolean isLeaf() {
		return false;
	}

	@Override
	public void insert(MutableTreeNode child, int index) {
		companies.add(index, (InstaFramCompany) child);
	}

	@Override
	public void remove(int index) {
		companies.remove(index);
	}

	@Override
	public void remove(MutableTreeNode node) {
		companies.remove(node);
	}

	@Override
	public void removeFromParent() {
	}

	@Override
	public void setParent(MutableTreeNode newParent) {
	}

	@Override
	public void setUserObject(Object object) {
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void notifyAllObservers() {
	}

	@Override
	public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
		return null;
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
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
