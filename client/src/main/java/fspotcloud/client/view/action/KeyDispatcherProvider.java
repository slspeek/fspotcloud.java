package fspotcloud.client.view.action;

import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;
import com.google.inject.Provider;

import fspotcloud.client.demo.DemoStepFactoryImpl;
import fspotcloud.client.main.shared.NavigationEvent;
import fspotcloud.client.main.shared.SlideshowEvent;

public class KeyDispatcherProvider implements Provider<KeyDispatcher> {

	final private KeyDispatcher keyDispatcher = new KeyDispatcher();
	final private EventBus eventBus;
	final private ToggleFullscreenAction toggleFullscreen;
	final private TreeFocusAction treeFocusAction;

	@Inject
	public KeyDispatcherProvider(ToggleFullscreenAction toggleFullscreen,
			EventBus eventBus, TreeFocusAction treeFocusAction) {
		this.eventBus = eventBus;
		this.toggleFullscreen = toggleFullscreen;
		this.treeFocusAction = treeFocusAction;
		registerShortcuts();
	}

	private void registerShortcuts() {
		keyDispatcher
				.register(toggleFullscreen, AllShortcuts.TOGGLE_FULLSCREEN);
		keyDispatcher.register(
				getNavigationAction(NavigationEvent.TOGGLE_RASTER_VIEW),
				AllShortcuts.TOGGLE_TABULAR_VIEW);
		keyDispatcher.register(
				getNavigationAction(NavigationEvent.SET_DEFAULT_RASTER),
				AllShortcuts.SET_DEFAULT_RASTER);
		keyDispatcher.register(getNavigationAction(NavigationEvent.FORWARD),
				AllShortcuts.GO_FORWARD);
		keyDispatcher.register(getNavigationAction(NavigationEvent.BACK),
				AllShortcuts.GO_BACK);
		keyDispatcher.register(getNavigationAction(NavigationEvent.BEGIN),
				AllShortcuts.GOTO_START);
		keyDispatcher.register(getNavigationAction(NavigationEvent.END),
				AllShortcuts.GOTO_END);
		keyDispatcher.register(
				getNavigationAction(NavigationEvent.INCREASE_RASTER_WIDTH),
				AllShortcuts.ADD_COLUMN);
		keyDispatcher.register(
				getNavigationAction(NavigationEvent.DECREASE_RASTER_WIDTH),
				AllShortcuts.REMOVE_COLUMN);
		keyDispatcher.register(
				getNavigationAction(NavigationEvent.INCREASE_RASTER_HEIGHT),
				AllShortcuts.ADD_ROW);
		keyDispatcher.register(
				getNavigationAction(NavigationEvent.DECREASE_RASTER_HEIGHT),
				AllShortcuts.REMOVE_ROW);
		keyDispatcher.register(
				getNavigationAction(NavigationEvent.SET_RASTER_2x2),
				AllShortcuts.SET_RASTER_2x2);
		keyDispatcher.register(
				getNavigationAction(NavigationEvent.SET_RASTER_3x3),
				AllShortcuts.SET_RASTER_3x3);
		keyDispatcher.register(
				getNavigationAction(NavigationEvent.SET_RASTER_4x4),
				AllShortcuts.SET_RASTER_4x4);
		keyDispatcher.register(
				getNavigationAction(NavigationEvent.SET_RASTER_5x5),
				AllShortcuts.SET_RASTER_5x5);
		keyDispatcher.register(getSlideshowAction(SlideshowEvent.ACTION_START),
				AllShortcuts.SLIDESHOW_START);
		keyDispatcher.register(getSlideshowAction(SlideshowEvent.ACTION_STOP),
				AllShortcuts.SLIDESHOW__END);
		keyDispatcher.register(
				getSlideshowAction(SlideshowEvent.ACTION_SLOWER),
				AllShortcuts.SLIDESHOW_SLOWER);
		keyDispatcher.register(
				getSlideshowAction(SlideshowEvent.ACTION_FASTER),
				AllShortcuts.SLIDESHOW_FASTER);
		keyDispatcher.register(new HelpAction(), AllShortcuts.TOGGLE_HELP);
		keyDispatcher.register(treeFocusAction, AllShortcuts.TREE_FOCUS);
		keyDispatcher.register(new DemoAction(new DemoStepFactoryImpl(keyDispatcher)), AllShortcuts.START_DEMO);

	}

	NavigationAction getNavigationAction(int actionType) {
		return new NavigationAction(actionType, eventBus);
	}

	SlideshowAction getSlideshowAction(int actionType) {
		return new SlideshowAction(actionType, eventBus);
	}

	@Override
	public KeyDispatcher get() {
		return keyDispatcher;
	}

}
