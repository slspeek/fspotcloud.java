package com.googlecode.fspotcloud.client.main;

import java.util.Map;
import java.util.logging.Logger;

import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Event.NativePreviewEvent;
import com.google.gwt.user.client.Event.NativePreviewHandler;

import com.googlecode.fspotcloud.client.main.api.Initializable;
import com.googlecode.fspotcloud.client.view.action.api.ShortcutHandler;

public class GlobalShortcutController implements Initializable,
		IGlobalShortcutController {

	@SuppressWarnings("unused")
	private static final Logger log = Logger
			.getLogger(GlobalShortcutController.class.getName());

	private final Map<Mode, ShortcutHandler> handler;

	private Mode mode;

	public GlobalShortcutController(Map<Mode, ShortcutHandler> handler) {
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
			if (mode != null) {
				if (handler.get(mode).handle(keycode)) {
					preview.consume();
				}
			}
		}
	}

	public void init() {
		Event.addNativePreviewHandler(new Preview());
	}

	@Override
	public void setMode(Mode mode) {
		this.mode = mode;
		log.info("Set mode: "+mode);
	}

}