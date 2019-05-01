package rs.raf.gui;

import java.awt.BorderLayout;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import rs.raf.actions.CloseWindowAction;
import rs.raf.actions.manager.ActionManager;
import rs.raf.commands.CommandManager;
import rs.raf.gui.components.menus.MenuBar;
import rs.raf.gui.components.other.ExceptionsManager;
import rs.raf.gui.components.panels.BottomPanel;
import rs.raf.gui.components.panels.LeftPanel;
import rs.raf.gui.components.panels.RightPanel;
import rs.raf.gui.components.toolbar.ToolBar;
import rs.raf.gui.components.tree.InstaFramTree;
import rs.raf.model.InstaFramProduct;
import rs.raf.settings.Settings;
import rs.raf.utils.ScreenUtils;

public class MainFrameInstaFram extends JFrame implements ClipboardOwner {
	private static final long serialVersionUID = 1L;
	private static MainFrameInstaFram instance = null;

	private ActionManager actionManager = null;
	private ExceptionsManager exceptionsManager = null;
	private CommandManager commandManager;

	private ResourceBundle resourceBundle = null;

	private InstaFramTree tree = null;
	private boolean saved = true;

	private ToolBar toolBar;
	private LeftPanel leftPanel;
	private RightPanel rightPanel;
	private BottomPanel bottomPanel;

	private Clipboard clipboard;

	private InstaFramProduct currentProduct = null;
	private String currentProductPath = "";

	private MainFrameInstaFram() {
		super();
	}

	public static MainFrameInstaFram getInstance() {
		if (instance == null)
			instance = new MainFrameInstaFram();

		return instance;
	}

	public void init() {
		// Initialize Resource bundle
		resourceBundle = ResourceBundle.getBundle("rs.raf.settings.localisation.InstaFram", new Locale("en", "US"));

		// Initialize Action Manager
		actionManager = new ActionManager();

		// Exceptions Manager
		exceptionsManager = new ExceptionsManager(this);

		// Initialize Command Manager
		commandManager = new CommandManager();

		// Initialise Clipboard
		clipboard = new Clipboard("InstaFram_Clipboard");

		// Initialize GUI
		initGui();
	}

	private void initGui() {
		// Initialize Window
		setSize((ScreenUtils.getScreenWidth() / 2) + 80, ScreenUtils.getScreenHeight() / 2);
		setTitle(Settings.APP_NAME);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new CloseWindowAction());
		setLocationRelativeTo(null);
		setResizable(false);

		// JTree
		tree = new InstaFramTree();

		// Icon
		setIconImage(new ImageIcon(Settings.APP_ICON).getImage());

		// Menu Bar
		setJMenuBar(new MenuBar());

		// Toolbar
		toolBar = new ToolBar();
		add(toolBar, BorderLayout.NORTH);

		// Left panel
		leftPanel = new LeftPanel();
		add(leftPanel, BorderLayout.WEST);

		// Right panel
		rightPanel = new RightPanel();
		add(rightPanel, BorderLayout.CENTER);

		// Bottom panel
		bottomPanel = new BottomPanel();
		add(bottomPanel, BorderLayout.SOUTH);

		// Set look and feel
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}

	public void logout() {
		instance = null;
		resourceBundle = null;
		actionManager = null;
		exceptionsManager = null;
		tree.setModel(null);
		tree = null;
		saved = false;

		toolBar = null;
		leftPanel = null;
		rightPanel = null;
		bottomPanel = null;

		commandManager = null;

		currentProduct = null;
		currentProductPath = "";

		this.setVisible(false);
		this.dispose();
	}

	@SuppressWarnings("static-access")
	public void changeLanguage() {
		this.resourceBundle.clearCache();
		this.resourceBundle = ResourceBundle.getBundle("rs.raf.settings.localisation.InstaFram", Locale.getDefault());

		setJMenuBar(new MenuBar());
		bottomPanel.reinitialise();
		toolBar.reinitialise();
		
		SwingUtilities.updateComponentTreeUI(this);
		invalidate();
		validate();
		repaint();
	}

	public ResourceBundle getResourceBundle() {
		return resourceBundle;
	}

	public ActionManager getActionManager() {
		return actionManager;
	}

	public ExceptionsManager getExceptionsManager() {
		return exceptionsManager;
	}

	public CommandManager getCommandManager() {
		return commandManager;
	}

	public Clipboard getClipboard() {
		return clipboard;
	}

	public InstaFramTree getTree() {
		return tree;
	}

	public void setTree(InstaFramTree tree) {
		this.tree = tree;
	}

	public ToolBar getToolBar() {
		return toolBar;
	}

	public LeftPanel getLeftPanel() {
		return leftPanel;
	}

	public RightPanel getRightPanel() {
		return rightPanel;
	}

	public BottomPanel getBottomPanel() {
		return bottomPanel;
	}

	public boolean isSaved() {
		return saved;
	}

	public void setSaved(boolean saved) {
		this.saved = saved;
	}

	@Override
	public void lostOwnership(Clipboard arg0, Transferable arg1) {
	}

	public void setLookAndFeel(String className) {
		try {
			UIManager.setLookAndFeel(className);
			SwingUtilities.updateComponentTreeUI(tree);

			this.setJMenuBar(new MenuBar());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}

	public InstaFramProduct getCurrentProduct() {
		return currentProduct;
	}

	public String getCurrentProductPath() {
		return currentProductPath;
	}

	public void setCurrentProduct(InstaFramProduct currentProduct) {
		this.currentProduct = currentProduct;
	}

	public void setCurrentProductPath(String currentProductPath) {
		this.currentProductPath = currentProductPath;
	}
}
