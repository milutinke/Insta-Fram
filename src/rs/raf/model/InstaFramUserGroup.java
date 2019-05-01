package rs.raf.model;

import java.io.Serializable;
import java.util.ArrayList;

public class InstaFramUserGroup implements Serializable {
	private static final long serialVersionUID = -262336428356015655L;
	private String name;
	private ArrayList<String> permissions = new ArrayList<String>();

	public InstaFramUserGroup(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<String> getPermissions() {
		return permissions;
	}

	public void addPermission(String permission) {
		if (permissions.contains(permission))
			return;

		permissions.add(permission);
	}

	@Override
	public String toString() {
		return "InstaFramUserGroup [name=" + name + ", permissions=" + permissions + "]";
	}
}
