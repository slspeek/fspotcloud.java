package fspotcloud.peer;

public interface CommandFetcher {
		Object[] getCommand() throws Exception;
}