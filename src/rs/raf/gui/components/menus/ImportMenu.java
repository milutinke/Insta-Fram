package rs.raf.gui.components.menus;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import rs.raf.gui.MainFrameInstaFram;
import rs.raf.settings.Settings;
import rs.raf.utils.Localisation;

@SuppressWarnings("serial")
public class ImportMenu extends JMenu {
	private JMenuItem importFromPdf;
	private JMenuItem importFromExcel;
	private JMenuItem importFromWord;

	public ImportMenu() {
		super(Localisation.getLocalisedString("mImport"));
		setIcon(new ImageIcon(Settings.ICON_FOLDER_PATH + Settings.ICON_IMPORT));

		// Action: Import from Pdf
		importFromPdf = new JMenuItem(MainFrameInstaFram.getInstance().getActionManager().getImportFromPdfAction());
		add(importFromPdf);
		addSeparator();

		importFromExcel = new JMenuItem(MainFrameInstaFram.getInstance().getActionManager().getImportFromExcelAction());
		add(importFromExcel);
		addSeparator();

		// Action: Import from Word
		importFromWord = new JMenuItem(MainFrameInstaFram.getInstance().getActionManager().getImportFromWordAction());
		add(importFromWord);
	}
	
	public JMenuItem getImportFromPdf() {
		return importFromPdf;
	}

	public JMenuItem getImportFromExcel() {
		return importFromExcel;
	}

	public JMenuItem getImportFromWord() {
		return importFromWord;
	}
}
