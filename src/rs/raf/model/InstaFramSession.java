package rs.raf.model;

import java.io.Serializable;

public class InstaFramSession implements Serializable {
	private static final long serialVersionUID = 407182929376908579L;
	private InstaFramUser currentUser = null;
	private long expiration;

	public InstaFramSession(InstaFramUser currentUser, long expiration) {
		this.currentUser = currentUser;
		this.expiration = expiration;
	}

	public InstaFramUser getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(InstaFramUser currentUser) {
		this.currentUser = currentUser;
	}

	public long getExpiration() {
		return expiration;
	}

	public void setExpiration(long expiration) {
		this.expiration = expiration;
	}

	@Override
	public String toString() {
		return "Session [currentUser=" + currentUser + ", expiration" + expiration + "]";
	}
}
