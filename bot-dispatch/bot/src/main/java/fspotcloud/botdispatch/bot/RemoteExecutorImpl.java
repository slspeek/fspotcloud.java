package fspotcloud.botdispatch.bot;

import java.util.logging.Logger;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;

import com.google.inject.Inject;

public class RemoteExecutorImpl implements RemoteExecutor {
	final static private Logger log = Logger.getLogger(RemoteExecutorImpl.class
			.getName());
	final private XmlRpcClient client;

	static volatile int  activeCall;
	
	@Inject
	public RemoteExecutorImpl(XmlRpcClient client) {
		super();
		this.client = client;
		activeCall = 0;
	}

	@Override
	public Object[] execute(long callbackId, byte[] serializedResult)
			throws XmlRpcException {
		Object[] args = new Object[] { callbackId, serializedResult };
		log.info("Calling remote");
		activeCall++;
		Object[] result = (Object[]) client.execute("Controller.callback", args);
		activeCall--;
		log.info("Remote answered: " +activeCall);
		return result;
	}

}
