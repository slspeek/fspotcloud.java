package fspotcloud.server.control;

import com.google.inject.Inject;

import fspotcloud.server.model.command.CommandDO;
import fspotcloud.server.model.command.Commands;

public class Controller {

	private Commands commandManager;

	@Inject
	public Controller(Commands commandManager) {
		this.commandManager = commandManager;
	}

	@SuppressWarnings("unchecked")
	public Object[] getCommand() {
		return commandManager.popOldestCommand();
	}

}
