package rs.raf.utils;

import rs.raf.gui.MainFrameInstaFram;

public class Localisation {
	public static String getLocalisedString(String text) {
		if (MainFrameInstaFram.getInstance() == null)
			return "";

		if (MainFrameInstaFram.getInstance().getResourceBundle() == null)
			return "";

		return MainFrameInstaFram.getInstance().getResourceBundle().getString(text);
	}

	public static String format(String text, String what, String with) {
		return text.replaceAll(what, with);
	}
}
