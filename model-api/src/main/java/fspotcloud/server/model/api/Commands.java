package fspotcloud.server.model.api;

import java.util.List;

public interface Commands {

	public abstract Command create();

	@SuppressWarnings("unchecked")
	public abstract Object[] popOldestCommand();

	@SuppressWarnings("unchecked")
	public abstract boolean allReadyExists(String cmd, List<String> args);

	public abstract void save(Command c);

}