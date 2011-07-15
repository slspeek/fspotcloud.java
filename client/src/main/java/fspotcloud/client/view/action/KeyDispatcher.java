package fspotcloud.client.view.action;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import fspotcloud.client.view.action.api.UserAction;

public class KeyDispatcher implements ShortcutHandler {

	private static final Logger log = Logger.getLogger(KeyDispatcher.class
			.getName());

	Map<Integer, Runnable> registeredActions = new HashMap<Integer, Runnable>();

	public void register(UserAction shortcut) {
		int[] keys;
		if (shortcut.getAlternateKey() != null) {
			keys = new int[2];
			keys[1] = shortcut.getAlternateKey().getKeyCode();
		} else {
			keys = new int[1];
		}
		keys[0] = shortcut.getKey().getKeyCode();
		for (int key : keys) {
			log.info("Putting: " + key + " " + shortcut.getCaption());
			registeredActions.put(key, shortcut);
		}
	}

	@Override
	public boolean handle(int keycode) {
		log.info("Checking code: " + keycode);
		Runnable action = registeredActions.get(keycode);
		if (action != null) {
			log.info("Taking action on code: " + keycode);
			action.run();
			return true;
		}
		return false;
	}

}
