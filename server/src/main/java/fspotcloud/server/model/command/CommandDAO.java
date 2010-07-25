package fspotcloud.server.model.command;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.name.Named;

public class CommandDAO {

	private final Provider<PersistenceManager> pmProvider;

	@Inject
	public CommandDAO(Provider<PersistenceManager> pmProvider,
			@Named("maxDelete") int maxDelete) {
		this.pmProvider = pmProvider;
	}

	@SuppressWarnings("unchecked")
	public Object[] popOldestCommand() {
		PersistenceManager pm = pmProvider.get();
		try {
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
			} else {
				return new Object[] {};
			}
		} finally {
			pm.close();
		}
	}

	@SuppressWarnings("unchecked")
	public boolean allReadyExists(String cmd, List<String> args) {
		PersistenceManager pm = pmProvider.get();
		try {
			Query query = pm.newQuery(Command.class);
			query.setFilter("cmd == cmdParam");
			query.setFilter("argsString == argsStringParam");
			query.declareParameters("String cmdParam, String argsStringParam");
			List<Command> rs = (List<Command>) query.execute(cmd, String
					.valueOf(args));
			return rs.size() > 0;
		} finally {
			pm.close();
		}
	}

	public void save(Command c) {
		PersistenceManager pm = pmProvider.get();
		try {
			pm.makePersistent(c);
		} finally {
			pm.close();
		}
	}

}
