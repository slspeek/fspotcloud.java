package fspotcloud.peer;

import org.apache.xmlrpc.client.XmlRpcClient;

import com.google.inject.Inject;

public class CommandFetcherImpl implements CommandFetcher {
	
	private XmlRpcClient endpoint;

	@Inject  
	public CommandFetcherImpl(XmlRpcClient endpoint) {
		this.endpoint = endpoint;
	}

	@Override
	public Object[] getCommand() throws Exception {
		 Object[] result = (Object[]) endpoint.execute(
					"Controller.getCommand", new Object[] {});
		return result;
	}
	

}
