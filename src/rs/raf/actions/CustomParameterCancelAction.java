package rs.raf.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import rs.raf.gui.dialogs.CustomParameterDialog;

public class CustomParameterCancelAction implements ActionListener {
	private CustomParameterDialog customParameterDialog;

	public CustomParameterCancelAction(CustomParameterDialog customParameterDialog) {
		this.customParameterDialog = customParameterDialog;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		customParameterDialog.dispose();
	}

	public CustomParameterDialog getCustomParameterDialog() {
		return customParameterDialog;
	}
}
