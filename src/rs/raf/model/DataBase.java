package rs.raf.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Timestamp;
import java.util.ArrayList;

public class DataBase {
	private static DataBase instance = null;

	private ArrayList<InstaFramUser> users;
	private ArrayList<InstaFramUserGroup> groups;

	private InstaFramSession currentSession = null;

	private DataBase() {
		users = new ArrayList<InstaFramUser>();
		groups = new ArrayList<InstaFramUserGroup>();
	}

	public static DataBase getInstance() {
		if (instance == null)
			instance = new DataBase();

		return instance;
	}
	
	public void loadGroups(String fileName) {
		BufferedReader bufferedReader;

		try {
			bufferedReader = new BufferedReader(new FileReader(new File(fileName)));

			String currentLine = "";
			int lineNumber = 0;

			while ((currentLine = bufferedReader.readLine()) != null) {
				String[] groupInfo = currentLine.split(";");

				if (groupInfo.length != 2) {
					System.out.println("[" + fileName + "] Syntax error - Line: " + lineNumber);
					continue;
				}

				String[] permissions = groupInfo[1].split("-");
				InstaFramUserGroup group = new InstaFramUserGroup(groupInfo[0]);
				
				for(String permission : permissions)
					group.addPermission(permission);

				groups.add(group);
				lineNumber++;
			}

			bufferedReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void loadUsers(String fileName) {
		BufferedReader bufferedReader;

		try {
			bufferedReader = new BufferedReader(new FileReader(new File(fileName)));

			String currentLine = "";
			int lineNumber = 0;

			while ((currentLine = bufferedReader.readLine()) != null) {
				String[] userInfo = currentLine.split(";");

				if (userInfo.length != 3) {
					System.out.println("[" + fileName + "] Syntax error - Line: " + lineNumber);
					continue;
				}

				InstaFramUserGroup group = findGroupByName(userInfo[2]);

				if (group == null) {
					System.out.println("[" + fileName + "] Error: Unknown group '" + userInfo[2] + "' - Line: " + lineNumber);
					continue;
				}

				users.add(new InstaFramUser(userInfo[0], userInfo[1], group));
				lineNumber++;
			}

			bufferedReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public InstaFramSession login(String username, String password) {
		for (InstaFramUser currentUser : users) {
			if (currentUser.getUsername().equals(username) && currentUser.getPassword().equals(password))
				return new InstaFramSession(currentUser, (new Timestamp(System.currentTimeMillis()).toInstant().toEpochMilli()) + 604800);
		}

		return null;
	}

	public InstaFramUserGroup findGroupByName(String groupName) {
		for (InstaFramUserGroup currentGroup : groups) {
			if (currentGroup.getName().equalsIgnoreCase(groupName))
				return currentGroup;
		}

		return null;
	}

	public ArrayList<InstaFramUser> getUsers() {
		return users;
	}

	public ArrayList<InstaFramUserGroup> getGroups() {
		return groups;
	}

	public InstaFramSession getCurrentSession() {
		return currentSession;
	}

	public void setCurrentSession(InstaFramSession currentSession) {
		this.currentSession = currentSession;
	}
}
