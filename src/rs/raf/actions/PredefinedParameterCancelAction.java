package rs.raf.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import rs.raf.gui.dialogs.PredefinedParameterDialog;

public class PredefinedParameterCancelAction implements ActionListener {

	private PredefinedParameterDialog predefinedDialog;

	public PredefinedParameterCancelAction(PredefinedParameterDialog predefinedDialog) {
		this.predefinedDialog = predefinedDialog;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		predefinedDialog.dispose();
	}

	public PredefinedParameterDialog getPredefinedDialog() {
		return predefinedDialog;
	}
}
