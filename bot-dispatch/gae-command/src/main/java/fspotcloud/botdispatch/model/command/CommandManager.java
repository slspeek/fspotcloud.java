package fspotcloud.botdispatch.model.command;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import net.customware.gwt.dispatch.shared.Action;
import net.customware.gwt.dispatch.shared.Result;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.gwt.user.client.rpc.AsyncCallback;
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

	@Override
	public Command createAndSave(Action<?> action,
			AsyncCallback<? extends Result> callback) {
		Command cmd = null;
		try {
			cmd = new CommandDO(action, callback);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		save(cmd);
		return cmd;
	}

	@Override
	public Command popFirstCommand() {
		PersistenceManager pm = pmProvider.get();
		try {
			Query query = pm.newQuery(CommandDO.class);
			query.setOrdering("ctime");
			query.setRange(0, 1);
			List<CommandDO> cmdList = (List<CommandDO>) query.execute();
			if (cmdList.size() > 0) {
				Command oldest = cmdList.get(0);
				Command result = null;
				try {
					result = new CommandDO(oldest.getAction(), oldest.getCallback());
					result.setId(oldest.getId());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				pm.deletePersistent(oldest);
				return result;
			} else {
				return NullCommand.INSTANCE;
			}
		} finally {
			pm.close();
		}
	}

	@Override
	public Command getById(long callbackId) {
		PersistenceManager pm = pmProvider.get();
		Command cmd = null;
		try {
//			cmd = pm.getObjectById(CommandDO.class, callbackId);
//			cmd = pm.detachCopy(cmd);
			Query q = pm.newQuery(CommandDO.class, "id == :callbackId");
			q.setUnique(true);
			
			cmd = (Command) q.execute(callbackId);
			cmd = pm.detachCopy(cmd);
		} finally {
			pm.close();
		}
		return cmd;
	}
}
