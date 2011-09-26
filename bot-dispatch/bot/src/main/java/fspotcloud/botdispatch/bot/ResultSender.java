package fspotcloud.botdispatch.bot;

import org.apache.xmlrpc.XmlRpcException;

public interface ResultSender {
	Object sendResult(String remoteMethod, Object[] args) throws XmlRpcException;
}
