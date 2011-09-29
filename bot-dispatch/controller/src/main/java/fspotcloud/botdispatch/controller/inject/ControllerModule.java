package fspotcloud.botdispatch.controller.inject;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.assistedinject.FactoryModuleBuilder;

import fspotcloud.botdispatch.controller.callback.Controller;
import fspotcloud.botdispatch.controller.callback.GuiceRequestProcessorFactoryFactory;
import fspotcloud.botdispatch.controller.callback.ResultHandlerFactory;

public class ControllerModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(GuiceRequestProcessorFactoryFactory.class).in(Singleton.class);
		bind(Controller.class).in(Singleton.class);
		install(new FactoryModuleBuilder()
				.build(ResultHandlerFactory.class));
	}

	
}
