package rs.raf.gui.components.menus;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import rs.raf.gui.MainFrameInstaFram;
import rs.raf.utils.Localisation;

@SuppressWarnings("serial")
public class FileMenu extends JMenu {
	private JMenuItem itemNewComapny;
	private JMenuItem itemNewProduct;
	private JMenuItem itemNewModule;
	private JMenuItem itemNewPredefinedParamter;
	private JMenuItem itemNewCustomParamter;
	private JMenuItem itemOpen;
	private JMenuItem itemClose;
	private JMenuItem itemSwitch;
	private JMenuItem itemSave;
	private JMenuItem itemSaveAs;
	private JMenuItem itemPreview;
	private JMenuItem itemLogout;
	private JMenuItem itemExit;

	public FileMenu() {
		super(Localisation.getLocalisedString("mFile"));

		// Action: New Company
		itemNewComapny = new JMenuItem(MainFrameInstaFram.getInstance().getActionManager().getNewCompanyAction());
		add(itemNewComapny);
		addSeparator();

		// Action: New Product
		itemNewProduct = new JMenuItem(MainFrameInstaFram.getInstance().getActionManager().getNewProductAction());
		add(itemNewProduct);
		addSeparator();

		// Action: New Module
		itemNewModule = new JMenuItem(MainFrameInstaFram.getInstance().getActionManager().getNewModuleAction());
		add(itemNewModule);
		addSeparator();

		// Action: New Predefined Parameter
		itemNewPredefinedParamter = new JMenuItem(
				MainFrameInstaFram.getInstance().getActionManager().getNewPredefinedParameterAction());
		add(itemNewPredefinedParamter);
		addSeparator();

		// Action: New Predefined Parameter
		itemNewCustomParamter = new JMenuItem(
				MainFrameInstaFram.getInstance().getActionManager().getNewCustomParameterAction());
		add(itemNewCustomParamter);
		addSeparator();

		// Action: Open
		itemOpen = new JMenuItem(MainFrameInstaFram.getInstance().getActionManager().getOpenAction());
		add(itemOpen);
		addSeparator();

		// Action: Close
		itemClose = new JMenuItem(MainFrameInstaFram.getInstance().getActionManager().getCloseAction());
		add(itemClose);
		addSeparator();

		// Action: Switch
		itemSwitch = new JMenuItem(MainFrameInstaFram.getInstance().getActionManager().getSwitchAction());
		add(itemSwitch);
		addSeparator();

		// Action: Save
		itemSave = new JMenuItem(MainFrameInstaFram.getInstance().getActionManager().getSaveAction());
		add(itemSave);
		addSeparator();

		// Action: Save As
		itemSaveAs = new JMenuItem(MainFrameInstaFram.getInstance().getActionManager().getSaveAsAction());
		add(itemSaveAs);
		addSeparator();

		// Action: Preview
		itemPreview = new JMenuItem(MainFrameInstaFram.getInstance().getActionManager().getPreviewAction());
		add(itemPreview);
		addSeparator();

		// Menu: Export
		add(new ExportMenu());
		addSeparator();

		// Menu: Import
		add(new ImportMenu());
		addSeparator();

		// Action: Logout
		itemLogout = new JMenuItem(MainFrameInstaFram.getInstance().getActionManager().getLogoutAction());
		add(itemLogout);
		addSeparator();

		// Action: Exit
		itemExit = new JMenuItem(MainFrameInstaFram.getInstance().getActionManager().getExitAction());
		add(itemExit);
	}

	public JMenuItem getItemNewCompany() {
		return itemNewComapny;
	}

	public JMenuItem getItemNewProduct() {
		return itemNewComapny;
	}

	public JMenuItem getItemNewModule() {
		return itemNewModule;
	}

	public JMenuItem getItemNewPredefinedParamter() {
		return itemNewPredefinedParamter;
	}

	public JMenuItem getItemNewCustomParameter() {
		return itemNewCustomParamter;
	}

	public JMenuItem getItemOpen() {
		return itemOpen;
	}

	public JMenuItem getItemClose() {
		return itemClose;
	}

	public JMenuItem getItemSwitch() {
		return itemSwitch;
	}

	public JMenuItem getItemSave() {
		return itemSave;
	}

	public JMenuItem getItemSaveAs() {
		return itemSaveAs;
	}

	public JMenuItem getItemPreview() {
		return itemPreview;
	}

	public JMenuItem getItemLogout() {
		return itemLogout;
	}

	public JMenuItem getItemExit() {
		return itemExit;
	}
}
