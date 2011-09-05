package fspotcloud.peer;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.xmlrpc.XmlRpcException;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

public class CommandWorkerImpl implements CommandWorker {

	final static private Logger log = Logger.getLogger(Bot.class.getName());

	final private NamingUtil namingUtil = new NamingUtil();
	final private DataSender sender;
	final private DataFetcher fetcher;

	final private String dataMethod;

	final private Object[] args;

	@Inject
	public CommandWorkerImpl(DataSender sender, DataFetcher fetcher,
			@Assisted String dataMethod, @Assisted Object[] args) {
		super();
		this.sender = sender;
		this.fetcher = fetcher;
		this.dataMethod = dataMethod;
		this.args = args;
	}

	@Override
	public void run() {
		Object result;
		try {
			Object[] data; 
			result = fetcher.getData(getDataMethod(), args);
			if (dataMethod.equals("sendImageData")) {
				data = (Object[]) result;
			} else {
				data = new Object[] { result };
			}
			sender.sendData(getRemoteMethod(), data);
		} catch (MethodNotFoundException e) {
			log.log(Level.SEVERE, "Method not found", e);
		} catch (XmlRpcException e) {
			log.log(Level.SEVERE, "Could not call remote", e);
		}
	}

	private String getDataMethod() throws MethodNotFoundException {
		return namingUtil.getDataMethod(dataMethod);
	}

	private String getRemoteMethod() throws MethodNotFoundException {
		return namingUtil.getRemoteMethod(dataMethod);
	}

}
