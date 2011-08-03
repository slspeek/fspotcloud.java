package fspotcloud.server.inject;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

import fspotcloud.server.control.task.TaskModule;

public class GuiceServletConfig extends GuiceServletContextListener {
	@Override
	protected Injector getInjector() {
		Injector i = Guice.createInjector(new FSpotCloudServletModule(),
				new FSpotCloudModule(), new TaskModule());
		return i;
	}
}
