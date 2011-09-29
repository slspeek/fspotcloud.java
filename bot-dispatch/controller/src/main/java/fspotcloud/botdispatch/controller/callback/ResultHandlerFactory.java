package fspotcloud.botdispatch.controller.callback;

import net.customware.gwt.dispatch.shared.Result;
import fspotcloud.botdispatch.model.api.Command;

public interface ResultHandlerFactory {
	ResultHandlerImpl get(Result result, Command command);
}
