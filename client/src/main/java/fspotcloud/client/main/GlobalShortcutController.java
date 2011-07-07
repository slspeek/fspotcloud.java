package fspotcloud.client.main;

import java.util.logging.Logger;

import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Event.NativePreviewEvent;
import com.google.gwt.user.client.Event.NativePreviewHandler;
import com.google.inject.Inject;

import fspotcloud.client.view.action.ShortcutHandler;

public class GlobalShortcutController {

	@SuppressWarnings("unused")
	private static final Logger log = Logger
			.getLogger(GlobalShortcutController.class.getName());

	private final ShortcutHandler handler;

	@Inject
	public GlobalShortcutController(ShortcutHandler handler) {
		this.handler = handler;
	}

	class Preview implements NativePreviewHandler {
		public void onPreviewNativeEvent(NativePreviewEvent preview) {
			NativeEvent event = preview.getNativeEvent();
			int keycode = event.getKeyCode();

			if (!event.getType().equalsIgnoreCase("keydown")
					|| event.getAltKey() || event.getCtrlKey()
					|| event.getMetaKey() || event.getShiftKey()) {
				return;
			}
			// log.info("Event preview in keypress code: " + keycode);
			if (handler.handle(keycode)) {
				preview.consume();
			}
		}
	}

	public void setup() {
		Event.addNativePreviewHandler(new Preview());
	}

}
