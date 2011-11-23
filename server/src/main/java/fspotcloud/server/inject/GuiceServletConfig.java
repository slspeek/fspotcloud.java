package fspotcloud.server.inject;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

import fspotcloud.botdispatch.controller.inject.ControllerServletModule;
import fspotcloud.botdispatch.model.MinimalCommandModelModule;
import fspotcloud.server.control.task.TaskActionsModule;
import fspotcloud.server.control.task.TaskModule;
import fspotcloud.server.model.ModelModule;
import fspotcloud.taskqueuedispatch.inject.TaskQueueDispatchModule;
import fspotcloud.taskqueuedispatch.inject.TaskQueueDispatchServletModule;

public class GuiceServletConfig extends GuiceServletContextListener {
	@Override
	protected Injector getInjector() {
		Injector i = Guice.createInjector(new FSpotCloudServletModule(),
				new FSpotCloudModule(), new ActionsModule(), new ModelModule(),
				new TaskModule(), new FSCControllerModule(),
				new ControllerServletModule(), new MinimalCommandModelModule(),
				new TaskQueueDispatchModule(), new TaskQueueDispatchServletModule(),new TaskActionsModule());
		return i;
	}
}
