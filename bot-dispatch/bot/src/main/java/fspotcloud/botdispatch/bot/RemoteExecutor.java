package fspotcloud.botdispatch.bot;

import org.apache.xmlrpc.XmlRpcException;

public interface RemoteExecutor {
	Object execute(String command, Object[] args) throws XmlRpcException;
}
