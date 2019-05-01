package rs.raf.utils;

import java.util.Enumeration;

import rs.raf.gui.MainFrameInstaFram;
import rs.raf.model.InstaFramNode;

public class NodeHelper {
	public static Boolean isDuplicate(InstaFramNode root, InstaFramNode newNode) {
		@SuppressWarnings("unchecked")
		Enumeration<InstaFramNode> children = root.children();

		if (children != null) {
			while (children.hasMoreElements()) {
				InstaFramNode node = (InstaFramNode) children.nextElement();

				if (node.getName().equals(newNode.getName()))
					return true;
			}
		}

		return false;
	}

	public static String getAllRemovedChindren(InstaFramNode root, String text) {
		String finalText = root + "~";

		@SuppressWarnings("unchecked")
		Enumeration<InstaFramNode> children = root.children();

		if (children != null) {
			while (children.hasMoreElements()) {
				InstaFramNode node = (InstaFramNode) children.nextElement();
				finalText += getAllRemovedChindren(node, text + "~" + node);
			}
		}

		return finalText;
	}

	public static void closeDeletedNodesTabs(String removedNodes) {
		if (removedNodes.length() > 0) {
			String[] removedNodesList = removedNodes.split("~");

			for (String removedNode : removedNodesList) {
				for (int openedTabs = 0; openedTabs < MainFrameInstaFram.getInstance().getRightPanel()
						.getTabCount(); openedTabs++) {
					String title = MainFrameInstaFram.getInstance().getRightPanel().getTitleAt(openedTabs).trim();

					if (title == null)
						continue;

					if (removedNode.trim().equalsIgnoreCase(title))
						MainFrameInstaFram.getInstance().getRightPanel().removeTabAt(openedTabs);
				}
			}
		}
	}
}
