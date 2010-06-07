package fspotcloud.peer;

import java.net.URL;

import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.apache.xmlrpc.client.XmlRpcCommonsTransportFactory;

//import org.apache.xmlrpc.demo.proxy.Adder;

public class Client {
	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// create configuration
		XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
//		config.setServerURL(new URL("http://127.0.0.1:8888/xmlrpc"));
		config.setServerURL(new URL("http://jfspotcloud.appspot.com/xmlrpc"));
		config.setEnabledForExtensions(true);
		config.setConnectionTimeout(60 * 1000);
		config.setReplyTimeout(60 * 1000);

		XmlRpcClient client = new XmlRpcClient();

		// use Commons HttpClient as transport
		client.setTransportFactory(new XmlRpcCommonsTransportFactory(client));
		// set configuration
		client.setConfig(config);

		// make the a regular call
		Object[] params = new Object[] { new Integer(2), new Integer(3) };
		Integer result = (Integer) client.execute("Calculator.add", params);
		System.out.println("2 + 3 = " + result);
		
		params = new Object[] { new Integer(2), new Integer(3) };
		result = (Integer) client.execute("TagReciever.add", params);
		System.out.println("MetaReciever 2 - 3 = " + result);
		
		params = new Object[] { new Integer(2), new Integer(3) };
		result = (Integer) client.execute("Meta.subtract", params);
		System.out.println("MetaReciever 2 - 3 = " + result);

		params = new Object[] {};
		Object[] list = (Object[])client.execute("Calculator.list", params);
		System.out.println(list[1]);
		
		String[] a = new String[] { "aap", "noot", "Mies" };
		String[] b = new String[] { "Teun", "schaap", "weiden" };
		params = new Object[] { a, b};
		result = (Integer) client.execute("Calculator.recieve", params);
	}
}
