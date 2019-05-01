package rs.raf.gui.components.other;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import rs.raf.gui.MainFrameInstaFram;
import rs.raf.utils.Localisation;

public class ExceptionsManager {
	private JFrame mainFrame = null;

	public ExceptionsManager(MainFrameInstaFram mainFrame) {
		this.mainFrame = mainFrame;
	}

	public int saveProjectWarning(String action) {
		return JOptionPane.showConfirmDialog(mainFrame,
				Localisation.format(Localisation.getLocalisedString("mDidNotSaveTheProject"), "%action%",
						Localisation.getLocalisedString(action)),
				Localisation.getLocalisedString("mUnsavedProjectTitle"), JOptionPane.YES_NO_OPTION);
	}

	public void fatalError() {
		JOptionPane.showMessageDialog(mainFrame, Localisation.getLocalisedString("mFatalError"),
				Localisation.getLocalisedString("mFatalErrorTitle"), JOptionPane.ERROR_MESSAGE);
	}

	public void emptyTreeError() {
		JOptionPane.showMessageDialog(mainFrame, Localisation.getLocalisedString("mEmptyTree"),
				Localisation.getLocalisedString("mErrorTitle"), JOptionPane.ERROR_MESSAGE);
	}

	public void predefinedParameterErrors(String errors) {
		StringBuilder errorMessages = new StringBuilder("");

		if (errors.contains("name"))
			errorMessages.append(Localisation.getLocalisedString("mEnterTheParameterName"));

		if (errors.contains("description"))
			errorMessages.append(Localisation.getLocalisedString("mEnterTheParameterDescription"));

		if (errors.contains("type"))
			errorMessages.append(Localisation.getLocalisedString("mSelectPredefinedParameterType"));

		JOptionPane.showMessageDialog(mainFrame, errorMessages.toString(),
				Localisation.getLocalisedString("mErrorTitle"), JOptionPane.ERROR_MESSAGE);
	}

	public void customParameterErrors(String errors) {
		StringBuilder errorMessages = new StringBuilder("");

		if (errors.contains("name"))
			errorMessages.append(Localisation.getLocalisedString("mEnterTheParameterName"));

		if (errors.contains("description"))
			errorMessages.append(Localisation.getLocalisedString("mEnterTheParameterDescription"));

		if (errors.contains("file"))
			errorMessages.append(Localisation.getLocalisedString("mCustomParametarFileError"));

		if (errors.contains("combox_box"))
			errorMessages.append(Localisation.getLocalisedString("mCustomParametarComboBoxOptions"));

		if (errors.contains("not_selected"))
			errorMessages.append(Localisation.getLocalisedString("mCustomParametarSelectOption"));

		JOptionPane.showMessageDialog(mainFrame, errorMessages.toString(),
				Localisation.getLocalisedString("mErrorTitle"), JOptionPane.ERROR_MESSAGE);
	}

	public void nodeCreationException(String nodeType, String parentType) {
		JOptionPane.showMessageDialog(mainFrame,
				Localisation.format(
						Localisation.format(Localisation.getLocalisedString("mNodeTypeException"), "%node_type%",
								Localisation.getLocalisedString(nodeType)),
						"%parent_type%", Localisation.getLocalisedString(parentType)),
				Localisation.getLocalisedString("mErrorTitle"), JOptionPane.ERROR_MESSAGE);
	}

	public void workspaceDeletionException() {
		JOptionPane.showMessageDialog(mainFrame, Localisation.getLocalisedString("mWorkspaceDeletionError"),
				Localisation.getLocalisedString("mErrorTitle"), JOptionPane.ERROR_MESSAGE);
	}

	public void nodeDelectionSelectException() {
		JOptionPane.showMessageDialog(mainFrame, Localisation.getLocalisedString("mNodeDeletionSelectError"),
				Localisation.getLocalisedString("mErrorTitle"), JOptionPane.ERROR_MESSAGE);
	}

	public void successfullyDeletedNode(String name) {
		JOptionPane.showMessageDialog(mainFrame,
				Localisation.format(Localisation.getLocalisedString("mDeletedNode"), "%node%", name),
				Localisation.getLocalisedString("mInfoTitle"), JOptionPane.INFORMATION_MESSAGE);
	}

	public int nodeDeletionQuestion(String name) {
		return JOptionPane.showConfirmDialog(mainFrame,
				Localisation.format(Localisation.getLocalisedString("mDoYouWantToDeleteNode"), "%node%", name),
				"Deletion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
	}

	public void loadType() {
		JOptionPane.showMessageDialog(mainFrame, Localisation.getLocalisedString("mLoadSelectedNode"),
				Localisation.getLocalisedString("mErrorTitle"), JOptionPane.ERROR_MESSAGE);
	}

	public void loadFailed() {
		JOptionPane.showMessageDialog(mainFrame, Localisation.getLocalisedString("mFailedToLoad"),
				Localisation.getLocalisedString("mErrorTitle"), JOptionPane.ERROR_MESSAGE);
	}

	public void loadSucceded(String name) {
		JOptionPane.showMessageDialog(mainFrame,
				Localisation.format(Localisation.getLocalisedString("mLoadedProject"), "%node%", name),
				Localisation.getLocalisedString("mInfoTitle"), JOptionPane.INFORMATION_MESSAGE);
	}

	public void saveType() {
		JOptionPane.showMessageDialog(mainFrame, Localisation.getLocalisedString("mSaveSelectedNode"),
				Localisation.getLocalisedString("mErrorTitle"), JOptionPane.ERROR_MESSAGE);
	}

	public void duplicateException(String name) {
		JOptionPane.showMessageDialog(mainFrame,
				Localisation.format(Localisation.getLocalisedString("mAlreadyExists"), "%node%", name),
				Localisation.getLocalisedString("mErrorTitle"), JOptionPane.ERROR_MESSAGE);
	}

	public void copyException() {
		JOptionPane.showMessageDialog(mainFrame, Localisation.getLocalisedString("mCopyException"),
				Localisation.getLocalisedString("mErrorTitle"), JOptionPane.ERROR_MESSAGE);
	}

	public void previewMustOpenProject() {
		JOptionPane.showMessageDialog(mainFrame, Localisation.getLocalisedString("mPreviewExceptionOpen"),
				Localisation.getLocalisedString("mErrorTitle"), JOptionPane.ERROR_MESSAGE);
	}

	public void previewMissingEntryPoint() {
		JOptionPane.showMessageDialog(mainFrame, Localisation.getLocalisedString("mPreviewMissingEntryPoint"),
				Localisation.getLocalisedString("mErrorTitle"), JOptionPane.ERROR_MESSAGE);
	}
	
	public void installationDone(String name) {
		JOptionPane.showMessageDialog(mainFrame,
				Localisation.format(Localisation.getLocalisedString("mProductInstalled"), "%name%", name),
				Localisation.getLocalisedString("mInfoTitle"), JOptionPane.INFORMATION_MESSAGE);
	}
}
