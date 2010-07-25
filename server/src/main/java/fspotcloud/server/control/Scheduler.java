package fspotcloud.server.control;

import java.util.List;

import com.google.inject.Inject;

import fspotcloud.server.model.command.Command;
import fspotcloud.server.model.command.Commands;

public class Scheduler {
	
	private Commands commandManager;

	@Inject
	public Scheduler(Commands commandManager) {
		this.commandManager = commandManager;
	}

	public boolean schedule(String cmd, List<String> args) {
		if (!hasDuplicates(cmd, args)) {
			Command c = commandManager.create();
			c.setCmd(cmd);
			c.setArgs(args);
			commandManager.save(c);
			return true;
		} else {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	private boolean hasDuplicates(String cmd, List<String> args) {
		return commandManager.allReadyExists(cmd, args);
	}

}
