package fspotcloud.client.view.action;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;
import com.google.inject.Provider;

import fspotcloud.client.main.shared.NavigationEvent;
import fspotcloud.client.main.shared.SlideshowEvent;

public class KeyDispatcherProvider implements Provider<KeyDispatcher> {

	final private KeyDispatcher keyDispatcher = new KeyDispatcher();
	private EventBus eventBus;

	@Inject
	public KeyDispatcherProvider(ToggleFullscreenAction toggleFullscreen,EventBus eventBus) {
		this.eventBus = eventBus;
		List<Character> list = new ArrayList<Character>();
		list.add('f');
		list.add('1');
		list.add('`');
		keyDispatcher.register(toggleFullscreen, list);
		list = new ArrayList<Character>();
		list.add('n');
		list.add('.');
		keyDispatcher.register(getNavigationAction(NavigationEvent.FORWARD), list);
		list = new ArrayList<Character>();
		list.add('p');
		list.add(',');
		keyDispatcher.register(getNavigationAction(NavigationEvent.BACK), list);
		list = new ArrayList<Character>();
		list.add('b');
		list.add('h');
		keyDispatcher.register(getNavigationAction(NavigationEvent.BEGIN), list);
		list = new ArrayList<Character>();
		list.add('e');
		list.add('z');
		list.add('9');
		keyDispatcher.register(getNavigationAction(NavigationEvent.END), list);
		list = new ArrayList<Character>();
		list.add('s');
		list.add('g');
		list.add('0');
		keyDispatcher.register(getSlideshowActio(SlideshowEvent.ACTION_START), list);
		list = new ArrayList<Character>();
		list.add('x');
		list.add('q');
		keyDispatcher.register(getSlideshowActio(SlideshowEvent.ACTION_STOP), list);
		list = new ArrayList<Character>();
		list.add('d');
		list.add('-');
		keyDispatcher.register(getSlideshowActio(SlideshowEvent.ACTION_SLOWER), list);
		list = new ArrayList<Character>();
		list.add('i');
		list.add('=');
		list.add('+');
		keyDispatcher.register(getSlideshowActio(SlideshowEvent.ACTION_FASTER), list);
	}
	
	NavigationAction getNavigationAction(int actionType) {
		return new NavigationAction(actionType, eventBus);
	}
	
	SlideshowAction getSlideshowActio(int actionType) {
		return new SlideshowAction(actionType, eventBus);
	}

	@Override
	public KeyDispatcher get() {
		return keyDispatcher;
	}

}
