package fspotcloud.server.control;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import fspotcloud.server.command.Command;
import fspotcloud.server.util.PMF;

public class Controller {
	
	@SuppressWarnings("unchecked")
	public Object[] getCommand() {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(Command.class);
		query.setOrdering("ctime");
		query.setRange(0, 1);
		List<Command> cmdList = (List<Command>) query.execute();
		if (cmdList.size() > 0) {
			Command oldest = cmdList.get(0);
			Object[] result = new Object[2];
			result[0] = oldest.getCmd();
			result[1] = oldest.getArgs().toArray();
			pm.deletePersistent(oldest);
			return result;
		}
		return new Object[]{};
	}

}
