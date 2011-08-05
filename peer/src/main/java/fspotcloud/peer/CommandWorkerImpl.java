package fspotcloud.peer;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.xmlrpc.client.XmlRpcClientException;

import com.google.inject.Inject;

public class CommandWorkerImpl implements CommandWorker {

	final static private Logger log = Logger.getLogger(Bot.class.getName());

	final private DataSender sender;
	final private DataFetcher fetcher;

	final private String dataMethod;

	final private Object[] args;

	@Inject
	public CommandWorkerImpl(DataSender sender, DataFetcher fetcher,
			String dataMethod, Object[] args) {
		super();
		this.sender = sender;
		this.fetcher = fetcher;
		this.dataMethod = dataMethod;
		this.args = args;
	}

	@Override
	public void run() {
		Object data;
		try {
			data = fetcher.getData(dataMethod, args);
			Object[] args = new Object[] { data };
			sender.sendData(getRemoteMethod(), args);
		} catch (MethodNotFoundException e) {
			log.log(Level.SEVERE, "Method not found", e);
		} catch (XmlRpcClientException e) {
			log.log(Level.SEVERE, "Could not call remote", e);
		}

	}

	private String getRemoteMethod() {
		// TODO Auto-generated method stub
		return null;
	}

}
