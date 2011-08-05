package fspotcloud.peer;

import com.google.inject.Inject;

public class CommandFetcherImpl implements CommandFetcher {
	
	private RemoteExecutor endpoint;

	@Inject  
	public CommandFetcherImpl(RemoteExecutor endpoint) {
		this.endpoint = endpoint;
	}

	@Override
	public Object[] getCommand() throws Exception {
		 Object[] result = (Object[]) endpoint.execute(
					"Controller.getCommand", new Object[] {});
		return result;
	}
	

}
