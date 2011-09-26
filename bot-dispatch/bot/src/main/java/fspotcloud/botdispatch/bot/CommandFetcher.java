package fspotcloud.botdispatch.bot;

public interface CommandFetcher {
		Object[] getCommand() throws Exception;
}