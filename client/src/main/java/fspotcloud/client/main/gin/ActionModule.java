package fspotcloud.client.main.gin;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;

import fspotcloud.client.main.shared.ApplicationEventFactory;
import fspotcloud.client.main.shared.ApplicationEventProviderFactory;
import fspotcloud.client.main.shared.NavigationEventFactory;
import fspotcloud.client.main.shared.NavigationEventProviderFactory;
import fspotcloud.client.main.shared.RasterEventFactory;
import fspotcloud.client.main.shared.RasterEventProviderFactory;
import fspotcloud.client.main.shared.SlideshowEventFactory;
import fspotcloud.client.main.shared.SlideshowEventProviderFactory;
import fspotcloud.client.view.action.AllShortcuts;
import fspotcloud.client.view.action.ApplicationActionsImpl;
import fspotcloud.client.view.action.NavigationActionsImpl;
import fspotcloud.client.view.action.RasterActionsImpl;
import fspotcloud.client.view.action.Shortcut;
import fspotcloud.client.view.action.SlideshowActionsImpl;
import fspotcloud.client.view.action.api.AllUserActions;
import fspotcloud.client.view.action.api.ApplicationActions;
import fspotcloud.client.view.action.api.NavigationActions;
import fspotcloud.client.view.action.api.RasterActions;
import fspotcloud.client.view.action.api.ShortcutAssistedFactory;
import fspotcloud.client.view.action.api.SlideshowActions;
import fspotcloud.client.view.action.api.UserAction;

public class ActionModule extends AbstractGinModule {

	@Override
	protected void configure() {
		
		bind(AllUserActions.class).to(AllShortcuts.class);
		
		bind(NavigationActions.class).to(NavigationActionsImpl.class);
		bind(RasterActions.class).to(RasterActionsImpl.class);
		bind(SlideshowActions.class).to(SlideshowActionsImpl.class);
		bind(ApplicationActions.class).to(ApplicationActionsImpl.class);
		
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

		install(new GinFactoryModuleBuilder().implement(UserAction.class,
				Shortcut.class).build(ShortcutAssistedFactory.class));
		

	}
}