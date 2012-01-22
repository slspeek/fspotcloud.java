package fspotcloud.server.admin.actions;

import java.util.logging.Logger;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.server.SimpleActionHandler;
import net.customware.gwt.dispatch.shared.ActionException;
import net.customware.gwt.dispatch.shared.DispatchException;

import com.google.inject.Inject;

import fspotcloud.botdispatch.model.api.Commands;
import fspotcloud.shared.dashboard.actions.CommandDeleteAll;
import fspotcloud.shared.dashboard.actions.VoidResult;

public class CommandDeleteAllHandler extends
		SimpleActionHandler<CommandDeleteAll, VoidResult> {
	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(CommandDeleteAllHandler.class
			.getName());
	final private Commands commandManager;

	@Inject
	public CommandDeleteAllHandler(Commands commandManager
			) {
		super();
		this.commandManager = commandManager;
	}

	@Override
	public VoidResult execute(CommandDeleteAll action, ExecutionContext context)
			throws DispatchException {
		try {
			commandManager.deleteAll();
		} catch (Exception e) {
			throw new ActionException(e);
		}
		return new VoidResult();
	}

}