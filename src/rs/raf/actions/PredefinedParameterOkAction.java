package rs.raf.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingUtilities;

import rs.raf.gui.MainFrameInstaFram;
import rs.raf.gui.components.tree.InstaFramTree;
import rs.raf.gui.dialogs.PredefinedParameterDialog;
import rs.raf.model.InstaFramModule;
import rs.raf.model.InstaFramNode;
import rs.raf.model.InstaFramPredefinedParameter;
import rs.raf.model.InstaFramProduct;
import rs.raf.utils.NodeHelper;

public class PredefinedParameterOkAction implements ActionListener {
	private PredefinedParameterDialog predefinedDialog;

	public PredefinedParameterOkAction(PredefinedParameterDialog predefinedDialog) {
		this.predefinedDialog = predefinedDialog;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		StringBuilder errorCodesBuilder = new StringBuilder("");

		if (predefinedDialog.getTextFieldName().getText().isEmpty())
			errorCodesBuilder.append("name");

		if (predefinedDialog.getTextAreaDescription().getText().isEmpty())
			errorCodesBuilder.append("description");

		if (!(predefinedDialog.getRadioButtonCompanyName().isSelected()
				|| predefinedDialog.getRadioButtonSystem().isSelected()
				|| predefinedDialog.getRadioButtonLogo().isSelected()
				|| predefinedDialog.getRadioButtonLanguage().isSelected()
				|| predefinedDialog.getRadioButtonLookAndFeel().isSelected()
				|| predefinedDialog.getRadioButtonVersion().isSelected()
				|| predefinedDialog.getRadioButtonSoftware().isSelected()
				|| predefinedDialog.getRadioButtonEntryPoint().isSelected())) {
			errorCodesBuilder.append("type");
		}

		String errorCodes = errorCodesBuilder.toString();

		if (errorCodes.length() > 0) {
			MainFrameInstaFram.getInstance().getExceptionsManager().predefinedParameterErrors(errorCodes);

			if (errorCodes.contains("name"))
				predefinedDialog.getTextFieldName().requestFocus();
			else if (errorCodes.contains("description"))
				predefinedDialog.getTextAreaDescription().requestFocus();
		} else {
			InstaFramPredefinedParameter newPredefinedParameterNode = new InstaFramPredefinedParameter(
					predefinedDialog.getTextFieldName().getText(), predefinedDialog.getTextAreaDescription().getText(),
					"predefined", "", predefinedDialog.getRadioButtonCompanyName().isSelected(),
					predefinedDialog.getRadioButtonSystem().isSelected(),
					predefinedDialog.getRadioButtonLogo().isSelected(),
					predefinedDialog.getRadioButtonLanguage().isSelected(),
					predefinedDialog.getRadioButtonLookAndFeel().isSelected(),
					predefinedDialog.getRadioButtonVersion().isSelected(),
					predefinedDialog.getRadioButtonSoftware().isSelected(),
					predefinedDialog.getRadioButtonEntryPoint().isSelected());

			InstaFramTree tree = MainFrameInstaFram.getInstance().getTree();
			Object selectedNode = tree.getLastSelectedPathComponent();

			if (!(selectedNode instanceof InstaFramModule || selectedNode instanceof InstaFramProduct)) {
				MainFrameInstaFram.getInstance().getExceptionsManager().nodeCreationException("mPredefinedParameter",
						"mModuleOrProduct");
				return;
			}

			InstaFramNode node = (InstaFramNode) selectedNode;

			if (node != null) {
				if (NodeHelper.isDuplicate(node, newPredefinedParameterNode)) {
					MainFrameInstaFram.getInstance().getExceptionsManager()
							.duplicateException(newPredefinedParameterNode.getName());
					return;
				}

				newPredefinedParameterNode.setParent(node);

				if (node instanceof InstaFramProduct)
					((InstaFramProduct) node).addNode(newPredefinedParameterNode);
				else
					((InstaFramModule) node).addParameter(newPredefinedParameterNode);
			}

			SwingUtilities.updateComponentTreeUI(tree);
			MainFrameInstaFram.getInstance().getTree()
					.expandPath(MainFrameInstaFram.getInstance().getTree().getSelectionPath());
			MainFrameInstaFram.getInstance().setSaved(false);

			predefinedDialog.dispose();
		}
	}

}
