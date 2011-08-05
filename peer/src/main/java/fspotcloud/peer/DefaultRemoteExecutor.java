package fspotcloud.peer;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;

import com.google.inject.Inject;

public class DefaultRemoteExecutor implements RemoteExecutor {

	final private XmlRpcClient client;
	
	@Inject
	public DefaultRemoteExecutor(XmlRpcClient client) {
		super();
		this.client = client;
	}
	@Override
	public Object execute(String command, Object[] args) throws XmlRpcException {
		return client.execute(command, args);
	}

}
