package fspotcloud.client.view.action;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class KeyDispatcherProvider implements Provider<KeyDispatcher> {

	final private KeyDispatcher keyDispatcher = new KeyDispatcher();
	
	@Inject
	public KeyDispatcherProvider(ToggleFullscreenAction toggleFullscreen, ForwardAction forward, BackAction back) {
		List<Character> list = new ArrayList<Character>();
		list.add('f');
		list.add('0');
		list.add('1');
		list.add('`');
		keyDispatcher.register(toggleFullscreen, list);
		list.clear();
		list.add('n');
		list.add('w');
		list.add('d');
		list.add('l');
		list.add('j');
		list.add('x');
		keyDispatcher.register(forward, list);
		list.clear();
		list.add('b');
		list.add('p');
		list.add('z');
		list.add('s');
		list.add('a');
		list.add('h');
		list.add('k');
		keyDispatcher.register(back, list);
		
	}
	@Override
	public KeyDispatcher get() {
		return keyDispatcher;
	}

}
