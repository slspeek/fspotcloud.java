package fspotcloud.server.command;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import fspotcloud.server.util.PMF;

public class Scheduler {

	public static boolean schedule(String cmd, List<String> args) {
		if ( !hasDuplicates(cmd,args)) {
			Command c = new Command();
			c.setCmd(cmd);
			c.setArgs(args);
			PersistenceManager pm = PMF.get().getPersistenceManager();
			try {
				pm.makePersistent(c);
			} finally {
				pm.close();
			}
			return true;
		} else {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	private static boolean hasDuplicates(String cmd, List<String> args) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(Command.class);
		query.setFilter("cmd == cmdParam");
		query.setFilter("argsString == argsStringParam");
		query.declareParameters("String cmdParam, String argsStringParam");
		List<Command> rs = (List<Command>) query.execute(cmd, String
				.valueOf(args));
		return rs.size() > 0;
	}

}
