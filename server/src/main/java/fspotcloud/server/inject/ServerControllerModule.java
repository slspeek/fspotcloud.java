package fspotcloud.server.inject;

import com.googlecode.botdispatch.controller.callback.ControllerHook;
import com.googlecode.botdispatch.controller.inject.AbstractControllerModule;
import fspotcloud.server.control.hook.TimeLoggingControllerHook;

public class ServerControllerModule extends AbstractControllerModule {

	public void configure() {
		super.configure();
		bind(ControllerHook.class).to(TimeLoggingControllerHook.class);
	}
	
	
}
