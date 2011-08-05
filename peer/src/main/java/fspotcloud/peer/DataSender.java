package fspotcloud.peer;

import org.apache.xmlrpc.client.XmlRpcClientException;

public interface DataSender {
	Object sendData(String remoteMethod, Object data) throws XmlRpcClientException;
}
