package fspotcloud.client.view.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KeyDispatcher implements ShortCutHandler {

	Map<Character, GestureAction> registeredActions = new HashMap<Character, GestureAction>();

	public void register(GestureAction action, List<Character> keys) {
		for (char key : keys) {
			registeredActions.put(key, action);
		}
	}

	@Override
	public boolean handle(int keycode) {
		GestureAction action = registeredActions.get((char) keycode);
		if (action != null) {
			action.perform();
			return true;
		}
		return false;
	}

}
