package fspotcloud.client.view.action;

import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;

import fspotcloud.client.main.api.Initializable;
import fspotcloud.client.main.shared.RasterEvent;
import fspotcloud.client.place.api.Navigator;

public class RasterEventHandler implements RasterEvent.Handler, Initializable {

	final private Navigator navigator;
	final private EventBus eventBus;

	@Inject
	public RasterEventHandler(Navigator navigator, EventBus eventBus) {
		this.navigator = navigator;
		this.eventBus = eventBus;
	}

	@Override
	public void onEvent(RasterEvent e) {
		switch (e.getActionType()) {
		case INCREASE_RASTER_WIDTH:
			navigator.increaseRasterWidth(1);
			break;
		case DECREASE_RASTER_WIDTH:
			navigator.increaseRasterWidth(-1);
			break;
		case INCREASE_RASTER_HEIGHT:
			navigator.increaseRasterHeight(1);
			break;
		case DECREASE_RASTER_HEIGHT:
			navigator.increaseRasterHeight(-1);
			break;
		case SET_RASTER_2x2:
			navigator.setRasterDimension(2,2);
			break;
		case SET_RASTER_3x3:
			navigator.setRasterDimension(3,3);
			break;
		case SET_RASTER_4x4:
			navigator.setRasterDimension(4,4);
			break;
		case SET_RASTER_5x5:
			navigator.setRasterDimension(5,5);
			break;
		case TOGGLE_RASTER_VIEW:
			navigator.toggleRasterView();
			break;
		case SET_DEFAULT_RASTER:
			navigator.resetRasterSize();
			break;
		default:
		break;
		}

	}

	public void init() {
		eventBus.addHandler(RasterEvent.TYPE, this);
	}
}
