package rs.raf.gui.components.toolbar;

import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import rs.raf.actions.manager.InstaFramAbstractAction;
import rs.raf.gui.MainFrameInstaFram;

public class ToolBar extends JToolBar {
	private static final long serialVersionUID = 1L;

	private JButton buttonNewCompany;
	private JButton buttonNewProduct;
	private JButton buttonNewModule;
	private JButton buttonNewPredefinedParameter;
	private JButton buttonNewCustomParameter;
	private JButton buttonOpen;
	private JButton buttonSave;
	private JButton buttonSaveAs;
	private JButton buttonCopy;
	private JButton buttonPaste;
	private JButton buttonUndo;
	private JButton buttonRedo;
	private JButton buttonPreview;

	public ToolBar() {
		super(SwingConstants.HORIZONTAL);
		setFloatable(true);

		// Action: New Company
		buttonNewCompany = createNewStylisedButton(
				MainFrameInstaFram.getInstance().getActionManager().getNewCompanyAction());
		add(buttonNewCompany);
		addSeparator();

		// Action: New Product
		buttonNewProduct = createNewStylisedButton(
				MainFrameInstaFram.getInstance().getActionManager().getNewProductAction());
		add(buttonNewProduct);
		addSeparator();

		// Action: New Module
		buttonNewModule = createNewStylisedButton(
				MainFrameInstaFram.getInstance().getActionManager().getNewModuleAction());
		add(buttonNewModule);
		addSeparator();

		// Action: New Predefined Parameter
		buttonNewPredefinedParameter = createNewStylisedButton(
				MainFrameInstaFram.getInstance().getActionManager().getNewPredefinedParameterAction());
		add(buttonNewPredefinedParameter);
		addSeparator();

		// Action: New Predefined Parameter
		buttonNewCustomParameter = createNewStylisedButton(
				MainFrameInstaFram.getInstance().getActionManager().getNewCustomParameterAction());
		add(buttonNewCustomParameter);
		addSeparator();

		// Action: Open
		buttonOpen = createNewStylisedButton(MainFrameInstaFram.getInstance().getActionManager().getOpenAction());
		add(buttonOpen);
		addSeparator();

		// Action: Save
		buttonSave = createNewStylisedButton(MainFrameInstaFram.getInstance().getActionManager().getSaveAction());
		add(buttonSave);
		addSeparator();

		// Action: Save As
		buttonSaveAs = createNewStylisedButton(MainFrameInstaFram.getInstance().getActionManager().getSaveAsAction());
		add(buttonSaveAs);
		addSeparator();

		// Action: Copy
		buttonCopy = createNewStylisedButton(MainFrameInstaFram.getInstance().getActionManager().getCopyAction());
		add(buttonCopy);
		addSeparator();

		// Action: Copy
		buttonPaste = createNewStylisedButton(MainFrameInstaFram.getInstance().getActionManager().getPasteAction());
		add(buttonPaste);
		addSeparator();

		// Action: Undo
		buttonUndo = createNewStylisedButton(MainFrameInstaFram.getInstance().getActionManager().getUndoAction());
		add(buttonUndo);
		addSeparator();

		// Action: Redo
		buttonRedo = createNewStylisedButton(MainFrameInstaFram.getInstance().getActionManager().getRedoAction());
		add(buttonRedo);

		// Action: Preview
		buttonPreview = createNewStylisedButton(MainFrameInstaFram.getInstance().getActionManager().getPreviewAction());
		add(buttonPreview);
	}

	@SuppressWarnings("static-access")
	public JButton createNewStylisedButton(InstaFramAbstractAction abstractAction) {
		JButton button = new JButton(abstractAction);
		button.setText("");
		button.setToolTipText((String) abstractAction.getValue(abstractAction.NAME));
		button.setBorderPainted(false);
		button.setBorder(null);
		button.setMargin(new Insets(0, 0, 0, 0));
		button.setContentAreaFilled(false);

		return button;
	}

	@SuppressWarnings("static-access")
	public void reinitialise() {
		buttonNewCompany
				.setToolTipText((String) MainFrameInstaFram.getInstance().getActionManager().getNewCompanyAction()
						.getValue(MainFrameInstaFram.getInstance().getActionManager().getNewCompanyAction().NAME));
		buttonNewProduct
				.setToolTipText((String) MainFrameInstaFram.getInstance().getActionManager().getNewProductAction()
						.getValue(MainFrameInstaFram.getInstance().getActionManager().getNewProductAction().NAME));
		buttonNewModule.setToolTipText((String) MainFrameInstaFram.getInstance().getActionManager().getNewModuleAction()
				.getValue(MainFrameInstaFram.getInstance().getActionManager().getNewModuleAction().NAME));
		buttonNewPredefinedParameter.setToolTipText(
				(String) MainFrameInstaFram.getInstance().getActionManager().getNewPredefinedParameterAction()
						.getValue(MainFrameInstaFram.getInstance().getActionManager().getNewModuleAction().NAME));
		buttonNewCustomParameter.setToolTipText(
				(String) MainFrameInstaFram.getInstance().getActionManager().getNewCustomParameterAction()
						.getValue(MainFrameInstaFram.getInstance().getActionManager().getNewModuleAction().NAME));
		buttonOpen.setToolTipText((String) MainFrameInstaFram.getInstance().getActionManager().getOpenAction()
				.getValue(MainFrameInstaFram.getInstance().getActionManager().getOpenAction().NAME));
		buttonSave.setToolTipText((String) MainFrameInstaFram.getInstance().getActionManager().getSaveAction()
				.getValue(MainFrameInstaFram.getInstance().getActionManager().getSaveAction().NAME));
		buttonSaveAs.setToolTipText((String) MainFrameInstaFram.getInstance().getActionManager().getSaveAsAction()
				.getValue(MainFrameInstaFram.getInstance().getActionManager().getSaveAsAction().NAME));
		buttonCopy.setToolTipText((String) MainFrameInstaFram.getInstance().getActionManager().getCopyAction()
				.getValue(MainFrameInstaFram.getInstance().getActionManager().getCopyAction().NAME));
		buttonPaste.setToolTipText((String) MainFrameInstaFram.getInstance().getActionManager().getPasteAction()
				.getValue(MainFrameInstaFram.getInstance().getActionManager().getPasteAction().NAME));
		buttonUndo.setToolTipText((String) MainFrameInstaFram.getInstance().getActionManager().getUndoAction()
				.getValue(MainFrameInstaFram.getInstance().getActionManager().getUndoAction().NAME));
		buttonRedo.setToolTipText((String) MainFrameInstaFram.getInstance().getActionManager().getRedoAction()
				.getValue(MainFrameInstaFram.getInstance().getActionManager().getRedoAction().NAME));
		buttonPreview.setToolTipText((String) MainFrameInstaFram.getInstance().getActionManager().getPreviewAction()
				.getValue(MainFrameInstaFram.getInstance().getActionManager().getPreviewAction().NAME));
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public JButton getButtonNewCompany() {
		return buttonNewCompany;
	}

	public JButton getButtonNewModule() {
		return buttonNewModule;
	}

	public JButton getButtonNewProduct() {
		return buttonNewProduct;
	}

	public JButton getButtonNewPredefinedParameter() {
		return buttonNewPredefinedParameter;
	}

	public JButton getButtonNewCustomParameter() {
		return buttonNewCustomParameter;
	}

	public JButton getButtonOpen() {
		return buttonOpen;
	}

	public JButton getButtonSave() {
		return buttonSave;
	}

	public JButton getButtonSaveAs() {
		return buttonSaveAs;
	}

	public JButton getButtonCopy() {
		return buttonCopy;
	}

	public JButton getButtonPaste() {
		return buttonPaste;
	}

	public JButton getButtonUndo() {
		return buttonUndo;
	}

	public JButton getButtonRedo() {
		return buttonRedo;
	}

	public JButton getButtonPreview() {
		return buttonPreview;
	}
}
