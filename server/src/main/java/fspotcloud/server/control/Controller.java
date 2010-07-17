package fspotcloud.server.control;

import com.google.inject.Inject;

import fspotcloud.server.model.command.Command;
import fspotcloud.server.model.command.CommandDAO;

public class Controller {

	private CommandDAO commandDAO;

	@Inject
	public Controller(CommandDAO commandDAO) {
		this.commandDAO = commandDAO;
	}

	@SuppressWarnings("unchecked")
	public Object[] getCommand() {
		return commandDAO.popOldestCommand();
	}

}
