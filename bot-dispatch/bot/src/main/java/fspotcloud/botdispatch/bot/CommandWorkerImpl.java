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

	final private Dispatch dispatch;
	final private Action<?> action;

	@Inject
	public CommandWorkerImpl(Dispatch dispatch, @Assisted Action<?> action) {
		super();
		this.dispatch = dispatch;
		this.action = action;
	}

	@Override
	public byte[] doExecute() {
		byte[] data = null;
		try {
			Object result = dispatch.execute(action);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream out = new ObjectOutputStream(bos);
			out.writeObject(result);
			out.close();
			data = bos.toByteArray();
		} catch (Exception e) {
			try {
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				ObjectOutputStream out = new ObjectOutputStream(bos);
				out.writeObject(e);
				out.close();
				data = bos.toByteArray();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		return data;
	}

}
