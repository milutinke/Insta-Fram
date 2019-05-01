package rs.raf;

import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;

import rs.raf.gui.LoginFrame;
import rs.raf.gui.MainFrameInstaFram;
import rs.raf.model.DataBase;
import rs.raf.model.InstaFramSession;
import rs.raf.settings.Settings;
import rs.raf.utils.SaveLoadHelper;

public class Main {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				DataBase.getInstance().loadGroups(Settings.GROUPS_DB_FILE);
				DataBase.getInstance().loadUsers(Settings.USERS_DB_FILE);

				File sessionFile = new File(System.getenv("APPDATA") + "/" + Settings.APP_SESSION_LOCATION);

				if(sessionFile.exists()) {
					InstaFramSession session = null;
					
					try {
						session = SaveLoadHelper.deserialiseSession();
					} catch (ClassNotFoundException | IOException e) {
						e.printStackTrace();
					}
					
					if(session == null) {
						displayLogin();
						return;
					}
					
					if(session.getExpiration() <= new Timestamp(System.currentTimeMillis()).toInstant().toEpochMilli()) {
						sessionFile.delete();
						displayLogin();
						return;
					}
					
					DataBase.getInstance().setCurrentSession(session);
					
					MainFrameInstaFram.getInstance().init();
					MainFrameInstaFram.getInstance().setVisible(true);
				} else displayLogin();
			}
		});
	}
	
	public static void displayLogin() {
		LoginFrame loginFrame = new LoginFrame();
		loginFrame.setVisible(true);
	}
}
