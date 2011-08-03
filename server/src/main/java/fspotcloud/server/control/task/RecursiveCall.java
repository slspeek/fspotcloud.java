package fspotcloud.server.control.task;

public interface RecursiveCall {
	void scheduleRemainder(int offset, int limit);
}
