package fspotcloud.peer;

public interface CommandWorkerFactory {
	CommandWorker get(String cmd, Object[] args);
}
