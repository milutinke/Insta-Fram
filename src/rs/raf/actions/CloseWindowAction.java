package rs.raf.actions;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import rs.raf.gui.MainFrameInstaFram;

public class CloseWindowAction extends WindowAdapter {
	@Override
	public void windowClosing(WindowEvent e) {
		if (!MainFrameInstaFram.getInstance().isSaved()) {
			if (MainFrameInstaFram.getInstance().getExceptionsManager().saveProjectWarning("mExit") == 1)
				return;
		}

		MainFrameInstaFram.getInstance().dispose();
		System.exit(0);
	}
}
