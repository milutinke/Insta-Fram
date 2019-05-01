package rs.raf.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import rs.raf.model.DataBase;
import rs.raf.model.InstaFramSession;
import rs.raf.utils.Localisation;
import rs.raf.utils.SaveLoadHelper;

@SuppressWarnings("serial")
public class LoginFrame extends JFrame {
	private JTextField textFiledUsername;
	private JPasswordField pfPassword;
	private JLabel labelUsername;
	private JLabel labelPassword;
	private JButton buttonLogin;
	private JButton buttonCancel;
	private boolean succeeded;

	private ResourceBundle resourceBundle;

	public LoginFrame() {
		resourceBundle = ResourceBundle.getBundle("rs.raf.settings.localisation.InstaFram", new Locale("en", "US"));

		initialiseGUI();
	}

	private void initialiseGUI() {
		setTitle(resourceBundle.getString("mLoginTitle"));
		setLocationRelativeTo(null);
		setResizable(false);

		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints gridBagConstraints = new GridBagConstraints();

		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;

		labelUsername = new JLabel(resourceBundle.getString("mUsername") + ": ");
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridwidth = 1;
		panel.add(labelUsername, gridBagConstraints);

		textFiledUsername = new JTextField(20);
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridwidth = 2;
		panel.add(textFiledUsername, gridBagConstraints);

		labelPassword = new JLabel(resourceBundle.getString("mPassword") + ": ");
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.gridwidth = 1;
		panel.add(labelPassword, gridBagConstraints);

		pfPassword = new JPasswordField(20);
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.gridwidth = 2;
		panel.add(pfPassword, gridBagConstraints);
		panel.setBorder(new LineBorder(Color.GRAY));

		buttonLogin = new JButton(resourceBundle.getString("mLogin"));

		buttonLogin.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				InstaFramSession session = DataBase.getInstance().login(getUsername(), getPassword());

				if (session != null) {
					DataBase.getInstance().setCurrentSession(session);
					try {
						SaveLoadHelper.serialiseSession(session);
					} catch (IOException e1) {
						e1.printStackTrace();
					}

					JOptionPane.showMessageDialog(LoginFrame.this,
							Localisation.format(resourceBundle.getString("mLoginSuccess"), "%user%", getUsername()),
							resourceBundle.getString("mLogin"), JOptionPane.INFORMATION_MESSAGE);

					dispose();

					MainFrameInstaFram.getInstance().init();
					MainFrameInstaFram.getInstance().setVisible(true);
				} else {
					JOptionPane.showMessageDialog(LoginFrame.this, resourceBundle.getString("mLoginFail"),
							resourceBundle.getString("mLogin"), JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		buttonCancel = new JButton(resourceBundle.getString("mCancel"));
		buttonCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		JPanel bp = new JPanel();
		bp.add(buttonLogin);
		bp.add(buttonCancel);

		getContentPane().add(panel, BorderLayout.CENTER);
		getContentPane().add(bp, BorderLayout.PAGE_END);

		pack();
	}

	public String getUsername() {
		return textFiledUsername.getText().trim();
	}

	public String getPassword() {
		return new String(pfPassword.getPassword());
	}

	public boolean isSucceeded() {
		return succeeded;
	}
}