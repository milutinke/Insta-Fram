package rs.raf.gui.components.panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

import rs.raf.model.DataBase;
import rs.raf.utils.Localisation;
import rs.raf.utils.ScreenUtils;

@SuppressWarnings("serial")
public class BottomPanel extends JPanel {
	public LocalDateTime now = null;
	private JTextField firstTextField;
	private JTextField secondTextField;
	private JTextField thirdTextField;
	private JTextField forthTextField;

	public BottomPanel() {
		setBackground(Color.white);
		setBorder(BorderFactory.createLineBorder(Color.gray));

		Dimension standardDimension = new Dimension((ScreenUtils.getScreenWidth() / 2) / 4, 24);

		firstTextField = createStatusElement(standardDimension, Color.LIGHT_GRAY,
				Localisation.getLocalisedString("mCurrentTime"));
		secondTextField = createStatusElement(standardDimension, Color.LIGHT_GRAY,
				Localisation.getLocalisedString("mCurrentStatus"));
		thirdTextField = createStatusElement(standardDimension, Color.YELLOW,
				Localisation.format(Localisation.getLocalisedString("mCurrentUser"), "%user%",
						DataBase.getInstance().getCurrentSession().getCurrentUser().getUsername()));
		forthTextField = createStatusElement(standardDimension, Color.ORANGE,
				Localisation.getLocalisedString("mCurrentAction"));

		add(firstTextField);
		add(secondTextField);
		add(thirdTextField);
		add(forthTextField);

		setLayout(new FlowLayout(FlowLayout.LEFT));

		Timer timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				firstTextField.setText(Localisation.format(Localisation.getLocalisedString("mCurrentTime"), "%time%",
						DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(LocalDateTime.now())));
			}
		});

		timer.setInitialDelay(0);
		timer.start();
	}

	private JTextField createStatusElement(Dimension dimension, Color color, String text) {
		JTextField textField = new JTextField();

		textField.setBackground(color);
		textField.setPreferredSize(dimension);
		textField.setEditable(false);
		textField.setText(text);

		return textField;
	}

	public void reinitialise() {
		secondTextField.setText(Localisation.getLocalisedString("mCurrentStatus"));
		thirdTextField.setText(Localisation.format(Localisation.getLocalisedString("mCurrentUser"), "%user%",
				DataBase.getInstance().getCurrentSession().getCurrentUser().getUsername()));
		forthTextField.setText(Localisation.getLocalisedString("mCurrentAction"));
	}
}
