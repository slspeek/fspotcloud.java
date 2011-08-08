package fspotcloud.server.model.api;

import java.util.List;

public interface Commands {

	Command create();

	Object[] popOldestCommand();

	boolean allReadyExists(String cmd, List<String> args);

	void save(Command c);

	int getCountUnderAThousend();

}