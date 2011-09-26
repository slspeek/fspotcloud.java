package fspotcloud.botdispatch.model.command;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.google.inject.Inject;
import com.google.inject.Provider;

import fspotcloud.botdispatch.model.api.Command;
import fspotcloud.botdispatch.model.api.Commands;

public class CommandManager implements Commands {

	private final Provider<PersistenceManager> pmProvider;

	@Inject
	public CommandManager(Provider<PersistenceManager> pmProvider) {
		this.pmProvider = pmProvider;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fspotcloud.botdispatch.model.command.Commands#create()
	 */
	public Command create() {
		return new CommandDO();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fspotcloud.botdispatch.model.command.Commands#popOldestCommand()
	 */
	@SuppressWarnings("unchecked")
	public Object[] popOldestCommand() {
		PersistenceManager pm = pmProvider.get();
		try {
			Query query = pm.newQuery(CommandDO.class);
			query.setOrdering("ctime");
			query.setRange(0, 1);
			List<CommandDO> cmdList = (List<CommandDO>) query.execute();
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fspotcloud.botdispatch.model.command.Commands#allReadyExists(java.lang.String,
	 * java.util.List)
	 */
	@SuppressWarnings("unchecked")
	public boolean allReadyExists(String cmd, List<String> args) {
		PersistenceManager pm = pmProvider.get();
		try {
			Query query = pm.newQuery(CommandDO.class);
			query.setFilter("cmd == cmdParam && argsString == argsStringParam");
			query.declareParameters("String cmdParam, String argsStringParam");
			List<CommandDO> rs = (List<CommandDO>) query.execute(cmd,
					String.valueOf(args));
			return rs.size() > 0;
		} finally {
			pm.close();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fspotcloud.botdispatch.model.command.Commands#save(fspotcloud.botdispatch.model
	 * .command.Command)
	 */
	public void save(Command c) {
		PersistenceManager pm = pmProvider.get();
		try {
			pm.makePersistent(c);
		} finally {
			pm.close();
		}
	}

	@Override
	public int getCountUnderAThousend() {
		PersistenceManager pm = pmProvider.get();
		int count = -1;
		try {
			Query query = pm.newQuery(CommandDO.class);
			List<CommandDO> rs = (List<CommandDO>) query.execute();
			count =  rs.size();;
		} finally {
			pm.close();
		}

		return count;
	}

}
