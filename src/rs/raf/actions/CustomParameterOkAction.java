package rs.raf.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingUtilities;

import rs.raf.gui.MainFrameInstaFram;
import rs.raf.gui.components.tree.InstaFramTree;
import rs.raf.gui.dialogs.CustomParameterDialog;
import rs.raf.model.InstaFramCustomParameter;
import rs.raf.model.InstaFramModule;
import rs.raf.model.InstaFramNode;
import rs.raf.model.InstaFramProduct;
import rs.raf.utils.NodeHelper;

public class CustomParameterOkAction implements ActionListener {
	private CustomParameterDialog customParameterDialog;

	public CustomParameterOkAction(CustomParameterDialog customParameterDialog) {
		this.customParameterDialog = customParameterDialog;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		StringBuilder errorCodesBuilder = new StringBuilder("");

		if (customParameterDialog.getTextFieldName().getText().isEmpty())
			errorCodesBuilder.append("name");

		if (customParameterDialog.getTextAreaDescription().getText().isEmpty())
			errorCodesBuilder.append("description");

		if (customParameterDialog.getRadioButtonAddFile().isSelected()
				&& customParameterDialog.getTextFieldLocation().getText().isEmpty())
			errorCodesBuilder.append("txt_file");

		if (customParameterDialog.getRadioButtonComboBox().isSelected()
				&& customParameterDialog.getTextAreaComboBox().getText().isEmpty())
			errorCodesBuilder.append("combox_box");

		if (!customParameterDialog.getRadioButtonComboBox().isSelected()
				&& !customParameterDialog.getRadioButtonUserInput().isSelected()
				&& !customParameterDialog.getRadioButtonAddFile().isSelected())
			errorCodesBuilder.append("not_selected");

		String errorCodes = errorCodesBuilder.toString();
		if (errorCodes.length() > 0) {
			MainFrameInstaFram.getInstance().getExceptionsManager().customParameterErrors(errorCodes);

			if (errorCodes.contains("name"))
				customParameterDialog.getTextFieldName().requestFocus();
			else if (errorCodes.contains("description"))
				customParameterDialog.getTextAreaDescription().requestFocus();
			return;
		} else {
			InstaFramCustomParameter newCustomParameter = new InstaFramCustomParameter(
					customParameterDialog.getTextFieldName().getText(),
					customParameterDialog.getTextAreaDescription().getText(), "custom", customParameterDialog.getTextFieldLocation().getText(),
					customParameterDialog.getTextFieldLocation().getText(),
					customParameterDialog.getTextAreaComboBox().getText(),
					customParameterDialog.getRadioButtonUserInput().isSelected(),
					customParameterDialog.getRadioButtonAddFile().isSelected(),
					customParameterDialog.getRadioButtonComboBox().isSelected());

			InstaFramTree tree = MainFrameInstaFram.getInstance().getTree();
			Object selectedNode = tree.getLastSelectedPathComponent();

			if (!(selectedNode instanceof InstaFramModule || selectedNode instanceof InstaFramProduct)) {
				MainFrameInstaFram.getInstance().getExceptionsManager().nodeCreationException("mCustomParameter",
						"mModuleOrProduct");
				return;
			}

			InstaFramNode node = (InstaFramNode) selectedNode;

			if (node != null) {
				if (NodeHelper.isDuplicate(node, newCustomParameter)) {
					MainFrameInstaFram.getInstance().getExceptionsManager().duplicateException(newCustomParameter.getName());
					return;
				}

				newCustomParameter.setParent(node);

				if (node instanceof InstaFramProduct)
					((InstaFramProduct) node).addNode(newCustomParameter);
				else
					((InstaFramModule) node).addParameter(newCustomParameter);
			}

			SwingUtilities.updateComponentTreeUI(tree);
			MainFrameInstaFram.getInstance().getTree()
					.expandPath(MainFrameInstaFram.getInstance().getTree().getSelectionPath());
			MainFrameInstaFram.getInstance().setSaved(false);

			customParameterDialog.dispose();
		}
	}

	public CustomParameterDialog getCustomParameterDialog() {
		return customParameterDialog;
	}
}
