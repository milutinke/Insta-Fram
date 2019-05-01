package rs.raf.utils;

import java.awt.Toolkit;

public class ScreenUtils {
	public static int getScreenWidth() {
		return Toolkit.getDefaultToolkit().getScreenSize().width;
	}

	public static int getScreenHeight() {
		return Toolkit.getDefaultToolkit().getScreenSize().height;
	}
}
