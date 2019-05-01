package rs.raf.model;

import java.io.Serializable;

public class InstaFramUser implements Serializable {
	private static final long serialVersionUID = -4688014114032088326L;
	private String username;
	private String password;
	private InstaFramUserGroup group;

	public InstaFramUser(String username, String password, InstaFramUserGroup group) {
		this.username = username;
		this.password = password;
		this.group = group;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public InstaFramUserGroup getGroup() {
		return group;
	}

	public void setGroup(InstaFramUserGroup group) {
		this.group = group;
	}

	@Override
	public String toString() {
		return "InstaFramUser [username=" + username + ", password=" + password + ", group=" + group + "]";
	}
}
