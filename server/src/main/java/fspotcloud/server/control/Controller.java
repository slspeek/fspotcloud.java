package fspotcloud.server.control;

import com.google.inject.Inject;

import fspotcloud.server.model.command.Command;
import fspotcloud.server.model.command.CommandDAO;

public class Controller {

	
	private CommandDAO commandDAO;
	
	@Inject public Controller(CommandDAO commandDAO) {
		this.commandDAO = commandDAO;
	}
	
	@SuppressWarnings("unchecked")
	public Object[] getCommand() {
		Command oldest = commandDAO.popOldestCommand();
		Object[] result = new Object[2];
		result[0] = oldest.getCmd();
		result[1] = oldest.getArgs().toArray();
		return new Object[] {};
	}

}
