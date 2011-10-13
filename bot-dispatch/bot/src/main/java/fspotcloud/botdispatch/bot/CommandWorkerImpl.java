package fspotcloud.botdispatch.bot;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.logging.Logger;

import net.customware.gwt.dispatch.server.Dispatch;
import net.customware.gwt.dispatch.shared.Action;
import net.customware.gwt.dispatch.shared.Result;

import org.apache.commons.lang.SerializationUtils;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

public class CommandWorkerImpl implements CommandWorker {

	@SuppressWarnings("unused")
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
			Result result = dispatch.execute(action);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream out = new ObjectOutputStream(bos);
			out.writeObject(result);
			out.close();
			data = SerializationUtils.serialize((Serializable) result);
		} catch (Exception e) {
			data = SerializationUtils.serialize(e);
		}
		return data;
	}

}
