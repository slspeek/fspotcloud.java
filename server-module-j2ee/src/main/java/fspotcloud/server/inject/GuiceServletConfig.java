package fspotcloud.server.inject;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;


public class GuiceServletConfig extends GuiceServletContextListener {
	@Override
	protected Injector getInjector() {
		Injector i = Guice.createInjector(new J2eeTotalModule());
		return i;
	}
}
