package fspotcloud.peer;

import org.apache.xmlrpc.XmlRpcException;

public interface DataSender {
	Object sendData(String remoteMethod, Object[] args) throws XmlRpcException;
}
