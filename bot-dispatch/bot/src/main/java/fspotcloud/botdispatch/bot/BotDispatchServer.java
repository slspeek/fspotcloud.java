package fspotcloud.botdispatch.bot;

import com.google.inject.Inject;

public class BotDispatchServer {

	final private CommandFetcher fetcher;
	final private ResultSender sender;
	final private CommandWorkerFactory factory;
	
	@Inject
	public BotDispatchServer(CommandFetcher fetcher, ResultSender sender,
			CommandWorkerFactory factory) {
		super();
		this.fetcher = fetcher;
		this.sender = sender;
		this.factory = factory;
	}

	
	public void runForever() {
		
		
	}
	
	
}
