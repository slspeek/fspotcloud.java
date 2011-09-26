package fspotcloud.botdispatch.bot;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;

import com.google.inject.Inject;

public class RemoteExecutorImpl implements RemoteExecutor {

	final private XmlRpcClient client;
	
	@Inject
	public RemoteExecutorImpl(XmlRpcClient client) {
		super();
		this.client = client;
	}
	@Override
	public Object execute(String command, Object[] args) throws XmlRpcException {
		return client.execute(command, args);
	}

}
