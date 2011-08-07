package fspotcloud.peer;

import org.apache.xmlrpc.XmlRpcException;

import com.google.inject.Inject;

public class DataSenderImpl implements DataSender {

	final private RemoteExecutor remote;
	
	@Inject
	public DataSenderImpl(RemoteExecutor remote) {
		super();
		this.remote = remote;
	}
	@Override
	public Object sendData(String remoteMethod, Object[] data)
			throws XmlRpcException {
		return remote.execute(remoteMethod, data);
	}

}
