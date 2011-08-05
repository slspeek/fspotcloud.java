package fspotcloud.peer;

import org.apache.xmlrpc.XmlRpcException;

public interface RemoteExecutor {
	Object execute(String command, Object[] args) throws XmlRpcException;
}
