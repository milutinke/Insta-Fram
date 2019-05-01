package rs.raf.gui.components.menus;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import rs.raf.gui.MainFrameInstaFram;
import rs.raf.settings.Settings;
import rs.raf.utils.Localisation;

@SuppressWarnings("serial")
public class ExportMenu extends JMenu {
	private JMenuItem exportToPdf;
	private JMenuItem exportToExcel;
	private JMenuItem exportToWord;

	public ExportMenu() {
		super(Localisation.getLocalisedString("mExport"));
		setIcon(new ImageIcon(Settings.ICON_FOLDER_PATH + Settings.ICON_EXPORT));

		// Action: Export To Pdf
		JMenuItem exportToPdf = new JMenuItem(MainFrameInstaFram.getInstance().getActionManager().getExportToPdfAction());
		add(exportToPdf);
		addSeparator();

		// Action: Export To Excel
		JMenuItem exportToExcel = new JMenuItem(MainFrameInstaFram.getInstance().getActionManager().getExportToExcelAction());
		add(exportToExcel);
		addSeparator();

		// Action: Export To Word
		JMenuItem exportToWord = new JMenuItem(MainFrameInstaFram.getInstance().getActionManager().getExportToWordAction());
		add(exportToWord);
	}

	public JMenuItem getExportToPdf() {
		return exportToPdf;
	}

	public JMenuItem getExportToExcel() {
		return exportToExcel;
	}

	public JMenuItem getExportToWord() {
		return exportToWord;
	}
}
