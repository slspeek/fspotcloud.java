package fspotcloud.client.view.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class KeyDispatcher implements ShortcutHandler {

	private static final Logger log = Logger.getLogger(KeyDispatcher.class
			.getName());

	Map<Integer, GestureAction> registeredActions = new HashMap<Integer, GestureAction>();

	public void register(GestureAction action, Shortcut shortcut) {
		int[] keys;
		if (shortcut.getAlternateKey() != null) {
			keys = new int[2];
			keys[1] = shortcut.getAlternateKey().getKeyCode();
		} else {
			keys = new int[1];
		}
		keys[0] = shortcut.getKey().getKeyCode();
		for (int key : keys) {
			registeredActions.put(key, action);
		}
	}

	@Override
	public boolean handle(int keycode) {
		log.info("Handling: code " + keycode);
		GestureAction action = registeredActions.get(keycode);
		if (action != null) {
			action.perform();
			return true;
		}
		return false;
	}

}
