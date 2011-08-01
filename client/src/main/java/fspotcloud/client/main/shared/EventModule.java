package fspotcloud.client.main.shared;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;

public class EventModule extends AbstractGinModule {

	@Override
	protected void configure() {
		
		install(new GinFactoryModuleBuilder()
				.build(NavigationEventProviderFactory.class));
		install(new GinFactoryModuleBuilder()
				.build(SlideshowEventProviderFactory.class));
		install(new GinFactoryModuleBuilder()
				.build(RasterEventProviderFactory.class));
		install(new GinFactoryModuleBuilder()
				.build(ApplicationEventProviderFactory.class));

		install(new GinFactoryModuleBuilder()
				.build(NavigationEventFactory.class));
		install(new GinFactoryModuleBuilder()
				.build(SlideshowEventFactory.class));
		install(new GinFactoryModuleBuilder()
				.build(ApplicationEventFactory.class));
		install(new GinFactoryModuleBuilder().build(RasterEventFactory.class));

	}
}
