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
	public Object[] execute(long callbackId, byte[] serializedResult) throws XmlRpcException {
		Object[] args = new Object[] { callbackId, serializedResult };
		return (Object[]) client.execute("Controller.callback", args);
	}

}
