package rs.raf.actions.manager;

import rs.raf.actions.AboutAction;
import rs.raf.actions.CloseAction;
import rs.raf.actions.CopyAction;
import rs.raf.actions.DeleteNodeAction;
import rs.raf.actions.ExitAction;
import rs.raf.actions.ExportToExcelAction;
import rs.raf.actions.ExportToPdfAction;
import rs.raf.actions.ExportToWordAction;
import rs.raf.actions.ImportFromExcelAction;
import rs.raf.actions.ImportFromPdfAction;
import rs.raf.actions.ImportFromWordAction;
import rs.raf.actions.LogoutAction;
import rs.raf.actions.NewCompanyAction;
import rs.raf.actions.NewCustomParameterAction;
import rs.raf.actions.NewModuleAction;
import rs.raf.actions.NewPredefinedParameterAction;
import rs.raf.actions.NewProductAction;
import rs.raf.actions.OpenAction;
import rs.raf.actions.PasteAction;
import rs.raf.actions.PreviewAction;
import rs.raf.actions.RedoAction;
import rs.raf.actions.RenameNodeAction;
import rs.raf.actions.SaveAction;
import rs.raf.actions.SaveAsAction;
import rs.raf.actions.SwitchAction;
import rs.raf.actions.UndoAction;

public class ActionManager {
	private NewCompanyAction newCompanyAction;
	private NewProductAction newProductAction;
	private NewModuleAction newModuleAction;
	private NewPredefinedParameterAction newPredefinedParameterAction;
	private NewCustomParameterAction newCustomParameterAction;
	private OpenAction openAction;
	private CloseAction closeAction;
	private SwitchAction switchAction;
	private SaveAction saveAction;
	private SaveAsAction saveAsAction;
	private ExitAction exitAction;
	private LogoutAction logoutAction;
	private CopyAction copyAction;
	private PasteAction pasteAction;
	private UndoAction undoAction;
	private RedoAction redoAction;
	private AboutAction aboutAction;
	private ExportToPdfAction exportToPdfAction;
	private ExportToExcelAction exportToExcelAction;
	private ExportToWordAction exportToWordAction;
	private ImportFromPdfAction importFromPdfAction;
	private ImportFromExcelAction importFromExcelAction;
	private ImportFromWordAction importFromWordAction;
	private RenameNodeAction renameNodeAction;
	private DeleteNodeAction deleteNodeAction;
	private PreviewAction previewAction;

	public ActionManager() {
		newCompanyAction = new NewCompanyAction();
		newProductAction = new NewProductAction();
		newModuleAction = new NewModuleAction();
		newPredefinedParameterAction = new NewPredefinedParameterAction();
		newCustomParameterAction = new NewCustomParameterAction();
		openAction = new OpenAction();
		closeAction = new CloseAction();
		switchAction = new SwitchAction();
		saveAction = new SaveAction();
		saveAsAction = new SaveAsAction();
		exitAction = new ExitAction();
		logoutAction = new LogoutAction();
		copyAction = new CopyAction();
		pasteAction = new PasteAction();
		undoAction = new UndoAction();
		redoAction = new RedoAction();
		aboutAction = new AboutAction();
		exportToPdfAction = new ExportToPdfAction();
		exportToExcelAction = new ExportToExcelAction();
		exportToWordAction = new ExportToWordAction();
		importFromPdfAction = new ImportFromPdfAction();
		importFromExcelAction = new ImportFromExcelAction();
		importFromWordAction = new ImportFromWordAction();
		renameNodeAction = new RenameNodeAction();
		deleteNodeAction = new DeleteNodeAction();
		previewAction = new PreviewAction();
	}

	public NewCompanyAction getNewCompanyAction() {
		return newCompanyAction;
	}

	public NewProductAction getNewProductAction() {
		return newProductAction;
	}

	public NewModuleAction getNewModuleAction() {
		return newModuleAction;
	}

	public NewPredefinedParameterAction getNewPredefinedParameterAction() {
		return newPredefinedParameterAction;
	}

	public NewCustomParameterAction getNewCustomParameterAction() {
		return newCustomParameterAction;
	}

	public OpenAction getOpenAction() {
		return openAction;
	}

	public CloseAction getCloseAction() {
		return closeAction;
	}

	public SwitchAction getSwitchAction() {
		return switchAction;
	}

	public SaveAction getSaveAction() {
		return saveAction;
	}

	public SaveAsAction getSaveAsAction() {
		return saveAsAction;
	}

	public ExitAction getExitAction() {
		return exitAction;
	}

	public LogoutAction getLogoutAction() {
		return logoutAction;
	}

	public CopyAction getCopyAction() {
		return copyAction;
	}

	public PasteAction getPasteAction() {
		return pasteAction;
	}

	public UndoAction getUndoAction() {
		return undoAction;
	}

	public RedoAction getRedoAction() {
		return redoAction;
	}

	public AboutAction getAboutAction() {
		return aboutAction;
	}

	public ExportToPdfAction getExportToPdfAction() {
		return exportToPdfAction;
	}

	public ExportToExcelAction getExportToExcelAction() {
		return exportToExcelAction;
	}

	public ExportToWordAction getExportToWordAction() {
		return exportToWordAction;
	}

	public ImportFromPdfAction getImportFromPdfAction() {
		return importFromPdfAction;
	}

	public ImportFromExcelAction getImportFromExcelAction() {
		return importFromExcelAction;
	}

	public ImportFromWordAction getImportFromWordAction() {
		return importFromWordAction;
	}

	public RenameNodeAction getRenameNodeAction() {
		return renameNodeAction;
	}

	public DeleteNodeAction getDeleteNodeAction() {
		return deleteNodeAction;
	}

	public PreviewAction getPreviewAction() {
		return previewAction;
	}

	public void setPreviewAction(PreviewAction previewAction) {
		this.previewAction = previewAction;
	}
}
