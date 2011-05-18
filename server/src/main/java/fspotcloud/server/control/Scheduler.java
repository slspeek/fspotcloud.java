package fspotcloud.server.control;

import java.util.List;
import java.util.logging.Logger;

import com.google.inject.Inject;

import fspotcloud.server.model.api.Command;
import fspotcloud.server.model.api.Commands;

public class Scheduler {

	private static final Logger log = Logger.getLogger(Scheduler.class
			.getName());

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
			log.info("Scheduled " + cmd + args + ".");
			return true;
		} else {
			log.info(cmd + args + " was already scheduled.");
			return false;
		}
	}

	private boolean hasDuplicates(String cmd, List<String> args) {
		return commandManager.allReadyExists(cmd, args);
	}

}
