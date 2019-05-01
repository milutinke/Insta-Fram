package rs.raf.commands;

import java.io.Serializable;
import java.util.ArrayList;

public class CommandManager implements Serializable {
	private static final long serialVersionUID = -3890513032157453240L;
	private ArrayList<Command> commands = new ArrayList<Command>();
	private int currentCommand = 0;

	public void addCommand(Command command) {
		while (currentCommand < commands.size())
			commands.remove(currentCommand);
		commands.add(command);
		doCommand();
	}

	public void doCommand() {
		if (currentCommand < commands.size())
			commands.get(currentCommand++).doCommand();
	}

	public void undoCommand() {
		if (currentCommand > 0)
			commands.get(--currentCommand).undoCommand();
	}
}