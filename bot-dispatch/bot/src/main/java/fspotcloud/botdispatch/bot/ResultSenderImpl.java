package fspotcloud.botdispatch.bot;

import org.apache.xmlrpc.XmlRpcException;

import com.google.inject.Inject;

public class ResultSenderImpl implements ResultSender {

	final private RemoteExecutor remote;
	
	@Inject
	public ResultSenderImpl(RemoteExecutor remote) {
		super();
		this.remote = remote;
	}
	@Override
	public Object sendResult(String remoteMethod, Object[] data)
			throws XmlRpcException {
		return remote.execute(remoteMethod, data);
	}

}
