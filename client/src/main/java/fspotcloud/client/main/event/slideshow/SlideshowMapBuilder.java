package fspotcloud.client.main.event.slideshow;

import com.google.gwt.resources.client.ImageResource;
import com.google.inject.Inject;

import fspotcloud.client.main.event.AbstractActionMap;
import fspotcloud.client.main.ui.Resources;
import fspotcloud.client.view.action.api.ActionDef;
import fspotcloud.client.view.action.api.UserActionFactory;

public class SlideshowMapBuilder extends AbstractActionMap {

	private Resources resources;

	@Inject
	public SlideshowMapBuilder(UserActionFactory userActionFactory,
			Resources resources) {
		super(userActionFactory, "Slideshow");
		this.resources = resources;
	}

	
	public void buildMap() {
		put(SlideshowType.SLIDESHOW_START , resources.playIcon());
		put(SlideshowType.SLIDESHOW__END , resources.stopIcon());
		put(SlideshowType.SLIDESHOW_SLOWER , resources.slowerIcon());
		put(SlideshowType.SLIDESHOW_FASTER, resources.fasterIcon());
	}
	
	private void put(ActionDef actionDef, ImageResource icon) {
		put(actionDef, icon,
				new SlideshowEventProvider(actionDef));
	}
}
