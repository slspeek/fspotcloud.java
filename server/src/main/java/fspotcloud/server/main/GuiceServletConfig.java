package fspotcloud.server.main;

import aecs.AECSModule;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

public class GuiceServletConfig extends GuiceServletContextListener {
	@Override
	protected Injector getInjector() {
		Injector i = Guice.createInjector(new FSpotCloudServletModule(),
				new FSpotCloudModule(), new AECSModule());
		return i;
	}
}
