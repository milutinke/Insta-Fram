package rs.raf.commands;

import java.io.Serializable;

public abstract class Command implements Serializable {
	private static final long serialVersionUID = 6314756180304291118L;

	public abstract void doCommand();

	public abstract void undoCommand();
}
