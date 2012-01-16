package fspotcloud.server.inject;

import fspotcloud.botdispatch.controller.callback.ControllerHook;
import fspotcloud.botdispatch.controller.inject.AbstractControllerModule;
import fspotcloud.server.control.hook.TimeLoggingControllerHook;

public class ServerControllerModule extends AbstractControllerModule {

	public void configure() {
		super.configure();
		bind(ControllerHook.class).to(TimeLoggingControllerHook.class);
	}
	
	
}
