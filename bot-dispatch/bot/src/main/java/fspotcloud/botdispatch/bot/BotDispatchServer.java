package fspotcloud.botdispatch.bot;

import net.customware.gwt.dispatch.server.Dispatch;

import com.google.inject.Inject;

public class BotDispatchServer {

	final private CommandFetcher fetcher;
	final private ResultSender sender;
	final private Dispatch dispatch;
	
	@Inject
	public BotDispatchServer(CommandFetcher fetcher, ResultSender sender,
			Dispatch dispatch) {
		super();
		this.fetcher = fetcher;
		this.sender = sender;
		this.dispatch = dispatch;
	}
	
	public void runForever() {
		
	}
}
