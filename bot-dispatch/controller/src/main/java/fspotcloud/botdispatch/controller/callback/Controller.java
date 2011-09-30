package fspotcloud.botdispatch.controller.callback;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import net.customware.gwt.dispatch.shared.Action;
import net.customware.gwt.dispatch.shared.Result;

import com.google.inject.Inject;

import fspotcloud.botdispatch.model.api.Command;
import fspotcloud.botdispatch.model.api.Commands;
import fspotcloud.botdispatch.model.command.NullCommand;

public class Controller {

	final private Commands commandManager;
	final private ResultHandlerFactory handlerFactory;

	@Inject
	public Controller(Commands commandManager,
			ResultHandlerFactory handlerFactory) {
		super();
		this.commandManager = commandManager;
		this.handlerFactory = handlerFactory;
	}

	@SuppressWarnings("unchecked")
	public Object[] callback(long callbackId, byte[] serializedResult) throws IOException {
		Object[] result;
		if (callbackId != -1L) {
			try {
				doCallback(callbackId, serializedResult);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		Command newCommand = commandManager.getAndLockFirstCommand();
		if (newCommand instanceof NullCommand) {
			result = new Object[] { -1L, null };
		} else {
			Action<?> action = newCommand.getAction();
			// Serialize to a byte array
		    ByteArrayOutputStream bos = new ByteArrayOutputStream() ;
		    ObjectOutputStream out = new ObjectOutputStream(bos) ;
		    out.writeObject(action);
		    out.close();

		    // Get the bytes of the serialized object
		    byte[] actionBytes = bos.toByteArray();
		    result = new Object[] { newCommand.getId(), actionBytes };
		}
		return result;
	}

	private void doCallback(long callbackId, byte[] serializedResult)
			throws IOException, ClassNotFoundException {
		Command callbackCommand = commandManager.getById(callbackId);
		ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(
				serializedResult));
		Result result = (Result) in.readObject();
		in.close();
		ResultHandlerImpl handler = handlerFactory.get(result, callbackCommand);
		handler.callback();
	}

}
