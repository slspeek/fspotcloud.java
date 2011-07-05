package fspotcloud.client.view.action;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;
import com.google.inject.Provider;

import fspotcloud.client.main.shared.NavigationEvent;
import fspotcloud.client.main.shared.SlideshowEvent;

public class KeyDispatcherProvider implements Provider<KeyDispatcher> {

	final private KeyDispatcher keyDispatcher = new KeyDispatcher();
	private EventBus eventBus;

	@Inject
	public KeyDispatcherProvider(ToggleFullscreenAction toggleFullscreen,
			EventBus eventBus, TreeFocusAction treeFocusAction) {
		this.eventBus = eventBus;
		List<Integer> list = new ArrayList<Integer>();
		list.add((int) 'F');
		list.add((int) '`');
		keyDispatcher.register(toggleFullscreen, list);
		list = new ArrayList<Integer>();
		list.add((int) 'T');
		list.add((int) '1');
		keyDispatcher.register(
				getNavigationAction(NavigationEvent.TOGGLE_RASTER_VIEW), list);
		list = new ArrayList<Integer>();
		list.add((int) '0');
		keyDispatcher.register(
				getNavigationAction(NavigationEvent.SET_DEFAULT_RASTER), list);
		list = new ArrayList<Integer>();
		list.add((int) 'N');
		list.add((int) '.');
		list.add(KeyCodes.KEY_RIGHT);
		keyDispatcher.register(getNavigationAction(NavigationEvent.FORWARD),
				list);
		list = new ArrayList<Integer>();
		list.add((int) 'P');
		list.add((int) ',');
		list.add(KeyCodes.KEY_LEFT);
		keyDispatcher.register(getNavigationAction(NavigationEvent.BACK), list);
		list = new ArrayList<Integer>();
		list.add((int) 'B');
		list.add((int) '0');
		list.add(KeyCodes.KEY_HOME);
		keyDispatcher
				.register(getNavigationAction(NavigationEvent.BEGIN), list);
		list = new ArrayList<Integer>();
		list.add((int) 'Z');
		list.add(KeyCodes.KEY_END);
		keyDispatcher.register(getNavigationAction(NavigationEvent.END), list);
		list = new ArrayList<Integer>();
		list.add((int) 'C');
		keyDispatcher.register(
				getNavigationAction(NavigationEvent.INCREASE_RASTER_WIDTH),
				list);
		list = new ArrayList<Integer>();
		list.add((int) 'X');
		keyDispatcher.register(
				getNavigationAction(NavigationEvent.DECREASE_RASTER_WIDTH),
				list);
		list = new ArrayList<Integer>();
		list.add((int) 'R');
		keyDispatcher.register(
				getNavigationAction(NavigationEvent.INCREASE_RASTER_HEIGHT),
				list);
		list = new ArrayList<Integer>();
		list.add((int) 'E');
		keyDispatcher.register(
				getNavigationAction(NavigationEvent.DECREASE_RASTER_HEIGHT),
				list);
		list = new ArrayList<Integer>();
		list.add((int) '2');
		keyDispatcher.register(
				getNavigationAction(NavigationEvent.SET_RASTER_2x2), list);
		list = new ArrayList<Integer>();
		list.add((int) '3');
		keyDispatcher.register(
				getNavigationAction(NavigationEvent.SET_RASTER_3x3), list);
		list = new ArrayList<Integer>();
		list.add((int) '4');
		keyDispatcher.register(
				getNavigationAction(NavigationEvent.SET_RASTER_4x4), list);
		list = new ArrayList<Integer>();
		list.add((int) '5');
		keyDispatcher.register(
				getNavigationAction(NavigationEvent.SET_RASTER_5x5), list);
		list = new ArrayList<Integer>();
		list.add((int) 'S');
		list.add((int) 'G');
		keyDispatcher.register(getSlideshowAction(SlideshowEvent.ACTION_START),
				list);
		list = new ArrayList<Integer>();
		list.add((int) 'Q');
		keyDispatcher.register(getSlideshowAction(SlideshowEvent.ACTION_STOP),
				list);
		list = new ArrayList<Integer>();
		list.add((int) 'D');
		keyDispatcher.register(
				getSlideshowAction(SlideshowEvent.ACTION_SLOWER), list);
		list = new ArrayList<Integer>();
		list.add((int) 'I');
		keyDispatcher.register(
				getSlideshowAction(SlideshowEvent.ACTION_FASTER), list);
		list = new ArrayList<Integer>();
		list.add((int) 'H');
		list.add(KeyCodes.KEY_ESCAPE);
		keyDispatcher.register(new HelpAction(), list);
		list = new ArrayList<Integer>();
		list.add(KeyCodes.KEY_ENTER);
		keyDispatcher.register(treeFocusAction, list);
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
