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

public class GlobalShortcutController {

	private static final Logger log = Logger
			.getLogger(GlobalShortcutController.class.getName());

	private final PlaceController placeController;

	@Inject
	public GlobalShortcutController(PlaceController placeController) {
		this.placeController = placeController;
	}

	class Preview implements NativePreviewHandler {
		public void onPreviewNativeEvent(NativePreviewEvent preview) {
			NativeEvent event = preview.getNativeEvent();

			Element elt = event.getEventTarget().cast();
			int keycode = event.getKeyCode();
			boolean ctrl = event.getCtrlKey();
			boolean shift = event.getShiftKey();
			boolean alt = event.getAltKey();
			boolean meta = event.getMetaKey();
			if (!event.getType().equalsIgnoreCase("keypress")) {
				//return;
			}
			if (keycode == 'f') {
				Place place = placeController.getWhere();
				if (place instanceof TagViewingPlace && !(place instanceof ImageViewingPlace)) {
					TagViewingPlace tagPlace = (TagViewingPlace) place;
					ImageViewingPlace imagePlace = new ImageViewingPlace(tagPlace.getTagId(),
							tagPlace.getPhotoId());
					placeController.goTo(imagePlace);
				} else if (place instanceof ImageViewingPlace) {
					ImageViewingPlace imagePlace = (ImageViewingPlace) place;
					TagViewingPlace tagPlace = new TagViewingPlace(imagePlace.getTagId(), imagePlace.getPhotoId());
					placeController.goTo(tagPlace);
				}
	
			}
			log.info("Preview: " + String.valueOf(keycode));
			
			// Tell the event handler that this event has been consumed
			preview.consume();
		}
	}

	public void setup() {
		Event.addNativePreviewHandler(new Preview());
	}

}
