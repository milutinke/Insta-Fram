package rs.raf.utils;

import javax.swing.KeyStroke;

public class AcceleratorHelper {
	public static KeyStroke getFormatedAccelerator(char mnemonic) {
		String temp = "" + mnemonic;

		return KeyStroke.getKeyStroke("control " + temp.toUpperCase());
	}
}
