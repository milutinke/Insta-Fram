package rs.raf.gui.components.other;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JTabbedPane;
import javax.swing.tree.MutableTreeNode;

import rs.raf.gui.MainFrameInstaFram;
import rs.raf.model.InstaFramNode;

@SuppressWarnings("serial")
public class ClosableTabbedPane extends JTabbedPane {
	private TabCloseUI closeUI = new TabCloseUI(this);
	private InstaFramNode tabNode = null;

	public void paint(Graphics g) {
		super.paint(g);
		closeUI.paint(g);
	}

	public void addTab(String title, InstaFramNode node, Component component) {
		String indentation = "";

		for (int i = 0; i < title.length(); i++)
			indentation += " ";

		super.addTab(title + indentation, component);

		tabNode = node;
	}

	public String getTabTitleAt(int index) {
		return super.getTitleAt(index).trim();
	}

	private class TabCloseUI implements MouseListener, MouseMotionListener {
		private ClosableTabbedPane tabbedPane;
		private int closeX = 0, closeY = 0, meX = 0, meY = 0;
		private int selectedTab;
		private final int width = 8, height = 8;
		private Rectangle rectangle = new Rectangle(0, 0, width, height);

		@SuppressWarnings("unused")
		private TabCloseUI() {
		}

		public TabCloseUI(ClosableTabbedPane pane) {

			tabbedPane = pane;
			tabbedPane.addMouseMotionListener(this);
			tabbedPane.addMouseListener(this);
		}

		public void mouseEntered(MouseEvent me) {
		}

		public void mouseExited(MouseEvent me) {
		}

		public void mousePressed(MouseEvent me) {
		}

		public void mouseClicked(MouseEvent me) {
		}

		public void mouseDragged(MouseEvent me) {
		}

		public void mouseReleased(MouseEvent me) {
			if (closeUnderMouse(me.getX(), me.getY())) {
				boolean isToCloseTab = tabAboutToClose(selectedTab);
				if (isToCloseTab && selectedTab > -1) {
					// Bugfix
					if (selectedTab == 0 && tabbedPane.getTabCount() <= 1) {
						while (MainFrameInstaFram.getInstance().getRightPanel().getTabCount() > 0)
							MainFrameInstaFram.getInstance().getRightPanel().remove(0);

						return;
					}

					tabbedPane.removeTabAt(selectedTab);
				}
				selectedTab = tabbedPane.getSelectedIndex();
			}
		}

		public void mouseMoved(MouseEvent me) {
			meX = me.getX();
			meY = me.getY();
			if (mouseOverTab(meX, meY)) {
				controlCursor();
				tabbedPane.repaint();
			}
		}

		private void controlCursor() {
			if (tabbedPane.getTabCount() > 0)
				if (closeUnderMouse(meX, meY)) {
					tabbedPane.setCursor(new Cursor(Cursor.HAND_CURSOR));
					if (selectedTab > -1)
						tabbedPane.setToolTipTextAt(selectedTab, "Close " + tabbedPane.getTitleAt(selectedTab));
				} else {
					tabbedPane.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					if (selectedTab > -1)
						tabbedPane.setToolTipTextAt(selectedTab, "");
				}
		}

		private boolean closeUnderMouse(int x, int y) {
			rectangle.x = closeX;
			rectangle.y = closeY;
			return rectangle.contains(x, y);
		}

		public void paint(Graphics g) {

			int tabCount = tabbedPane.getTabCount();
			for (int j = 0; j < tabCount; j++)
				if (tabbedPane.getComponent(j).isShowing()) {
					int x = tabbedPane.getBoundsAt(j).x + tabbedPane.getBoundsAt(j).width - width - 5;
					int y = tabbedPane.getBoundsAt(j).y + 5;
					drawClose(g, x, y);
					break;
				}
			if (mouseOverTab(meX, meY)) {
				drawClose(g, closeX, closeY);
			}
		}

		private void drawClose(Graphics g, int x, int y) {
			if (tabbedPane != null && tabbedPane.getTabCount() > 0) {
				Graphics2D g2 = (Graphics2D) g;
				drawColored(g2, isUnderMouse(x, y) ? Color.RED : Color.WHITE, x, y);
			}
		}

		private void drawColored(Graphics2D graphics2D, Color color, int x, int y) {
			graphics2D.setStroke(new BasicStroke(5, BasicStroke.JOIN_ROUND, BasicStroke.CAP_ROUND));
			graphics2D.setColor(Color.BLACK);
			graphics2D.drawLine(x, y, x + width, y + height);
			graphics2D.drawLine(x + width, y, x, y + height);
			graphics2D.setColor(color);
			graphics2D.setStroke(new BasicStroke(3, BasicStroke.JOIN_ROUND, BasicStroke.CAP_ROUND));
			graphics2D.drawLine(x, y, x + width, y + height);
			graphics2D.drawLine(x + width, y, x, y + height);

		}

		private boolean isUnderMouse(int x, int y) {
			return (Math.abs(x - meX) < width && Math.abs(y - meY) < height) ? true : false;
		}

		private boolean mouseOverTab(int x, int y) {
			int tabCount = tabbedPane.getTabCount();

			for (int tab = 0; tab < tabCount; tab++)
				if (tabbedPane.getBoundsAt(tab).contains(meX, meY)) {
					selectedTab = tab;
					closeX = tabbedPane.getBoundsAt(tab).x + tabbedPane.getBoundsAt(tab).width - width - 5;
					closeY = tabbedPane.getBoundsAt(tab).y + 5;
					return true;
				}
			return false;
		}

	}

	public boolean tabAboutToClose(int tabIndex) {
		return true;
	}

	public MutableTreeNode getTabNode() {
		return tabNode;
	}

	public void setTabNode(InstaFramNode tabNode) {
		this.tabNode = tabNode;
	}

}
