package fspotcloud.botdispatch.testserver;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

import fspotcloud.botdispatch.controller.inject.ControllerModule;
import fspotcloud.botdispatch.controller.inject.ControllerServletModule;
import fspotcloud.botdispatch.model.CommandModelModule;

public class GuiceServletConfig extends GuiceServletContextListener {
	@Override
	protected Injector getInjector() {
		Injector i = Guice.createInjector(new ControllerModule(),
				new CommandModelModule(), new TestServletModule(),
				new TestModule());
		return i;
	}

	private class TestServletModule extends ControllerServletModule {
		@Override
		protected void configureServlets() {
			super.configureServlets();
			serve("/test").with(TestServlet.class);
		}
	}

	private class TestModule extends AbstractModule {

		@Override
		protected void configure() {
			bind(List.class).toInstance(new ArrayList<String>());

		}

	}

}