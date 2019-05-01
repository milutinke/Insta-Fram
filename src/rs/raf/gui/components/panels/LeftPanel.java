package rs.raf.gui.components.panels;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;

import rs.raf.gui.MainFrameInstaFram;

@SuppressWarnings("serial")
public class LeftPanel extends JScrollPane {
	public LeftPanel() {
		super(MainFrameInstaFram.getInstance().getTree());

		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(200, 100));
		setBorder(BorderFactory.createLineBorder(Color.gray));
	}
}
