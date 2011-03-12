package fspotcloud.client.main;

import java.util.logging.Logger;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Event.NativePreviewEvent;
import com.google.gwt.user.client.Event.NativePreviewHandler;
import com.google.inject.Inject;

import fspotcloud.client.view.ImageViewingPlace;
import fspotcloud.client.view.TagViewingPlace;
import fspotcloud.client.view.action.ShortCutHandler;

public class GlobalShortcutController {

	private static final Logger log = Logger
			.getLogger(GlobalShortcutController.class.getName());

	private final ShortCutHandler handler;

	@Inject
	public GlobalShortcutController(ShortCutHandler handler) {
		this.handler = handler;
	}

	class Preview implements NativePreviewHandler {
		public void onPreviewNativeEvent(NativePreviewEvent preview) {
			NativeEvent event = preview.getNativeEvent();
			int keycode = event.getKeyCode();
			if (!event.getType().equalsIgnoreCase("keypress")) {
				return;
			}
			if (handler.handle(keycode)) {
				preview.consume();
			}
		}
	}

	public void setup() {
		Event.addNativePreviewHandler(new Preview());
	}

}
