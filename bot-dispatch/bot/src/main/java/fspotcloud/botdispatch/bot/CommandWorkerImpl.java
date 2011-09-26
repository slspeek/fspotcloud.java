package fspotcloud.botdispatch.bot;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.customware.gwt.dispatch.server.Dispatch;
import net.customware.gwt.dispatch.shared.Action;
import net.customware.gwt.dispatch.shared.DispatchException;
import net.customware.gwt.dispatch.shared.Result;

import org.apache.xmlrpc.XmlRpcException;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

public class CommandWorkerImpl implements CommandWorker {

	final static private Logger log = Logger.getLogger(CommandWorkerImpl.class
			.getName());

	final private ResultSender sender;
	final private Dispatch dispatch;

	final private Action<?> action;
	final private String callbackId;

	@Inject
	public CommandWorkerImpl(ResultSender sender, Dispatch dispatch,
			@Assisted Action<?> action, @Assisted String callbackId) {
		super();
		this.sender = sender;
		this.dispatch = dispatch;
		this.action = action;
		this.callbackId = callbackId;
	}

	@Override
	public void run() {
		Result result;
		try {
			result = dispatch.execute(action);
			// Serialize to a byte array
		    ByteArrayOutputStream bos = new ByteArrayOutputStream() ;
		    ObjectOutputStream out = new ObjectOutputStream(bos) ;
		    out.writeObject(result);
		    out.close();

		    // Get the bytes of the serialized object
		    byte[] buf = bos.toByteArray();
			Object[] data = new Object[]{ callbackId, buf };
			sender.sendResult("Controller.callback", data);
		}  catch (XmlRpcException e) {
			log.log(Level.SEVERE, "Could not call remote", e);
		} catch (DispatchException e) {
			//todo sent the error to the controller
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
