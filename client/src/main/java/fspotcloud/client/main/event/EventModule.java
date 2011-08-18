package fspotcloud.client.main.event;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.google.inject.name.Names;

import fspotcloud.client.main.event.about.AboutMapBuilder;
import fspotcloud.client.main.event.application.ApplicationMapBuilder;
import fspotcloud.client.main.event.navigation.NavigationMapBuilder;
import fspotcloud.client.main.event.raster.RasterMapBuilder;
import fspotcloud.client.main.event.slideshow.SlideshowMapBuilder;
import fspotcloud.client.view.action.UserActionImpl;
import fspotcloud.client.view.action.api.UserAction;
import fspotcloud.client.view.action.api.UserActionFactory;

public class EventModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(AbstractActionMap.class)
        .annotatedWith(Names.named("application")).to(ApplicationMapBuilder.class);
		bind(AbstractActionMap.class)
        .annotatedWith(Names.named("about")).to(AboutMapBuilder.class);
		bind(AbstractActionMap.class)
        .annotatedWith(Names.named("raster")).to(RasterMapBuilder.class);
		bind(AbstractActionMap.class)
        .annotatedWith(Names.named("navigation")).to(NavigationMapBuilder.class);
		bind(AbstractActionMap.class)
        .annotatedWith(Names.named("slideshow")).to(SlideshowMapBuilder.class);
		bind(ActionFamily.class).to(DefaultActionFamily.class);
		
		install(new GinFactoryModuleBuilder().implement(UserAction.class,
				UserActionImpl.class).build(UserActionFactory.class));
	}
}
