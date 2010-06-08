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
		config.setServerURL(new URL("http://127.0.0.1:8888/xmlrpc"));
//		config.setServerURL(new URL("http://jfspotcloud.appspot.com/xmlrpc"));
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
		
		String[] a = new String[] { "aap", "noot", "Mies" };
		String[] b = new String[] { "Teun", "schaap", "weiden" };
		params = new Object[] { a, b};
		result = (Integer) client.execute("Calculator.recieve", params);
		
		Object[] tag1 = new Object[] { "1", "Poezen", "Fotos van poezen", "0", 100 };
		Object[] tag2 = new Object[] { "2", "Fietsten", "Fietsen enzo voort enzo", "1", 100 };
		
		Object[] tags = new Object[] { tag1, tag2 };
		params = new Object[] { tags };
		result = (Integer) client.execute("TagReciever.recieveTags", params);
		
		
	}
}
