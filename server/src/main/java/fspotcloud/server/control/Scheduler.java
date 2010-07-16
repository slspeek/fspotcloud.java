package fspotcloud.server.control;

import java.util.List;

import com.google.inject.Inject;

import fspotcloud.server.model.command.Command;
import fspotcloud.server.model.command.CommandDAO;

public class Scheduler {
	
	private CommandDAO commandDAO;

	@Inject
	public Scheduler(CommandDAO commandDAO) {
		this.commandDAO = commandDAO;
	}

	public boolean schedule(String cmd, List<String> args) {
		if (!hasDuplicates(cmd, args)) {
			Command c = new Command();
			c.setCmd(cmd);
			c.setArgs(args);
			commandDAO.save(c);
			return true;
		} else {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	private boolean hasDuplicates(String cmd, List<String> args) {
		return commandDAO.allReadyExists(cmd, args);
	}

}
