package fspotcloud.client.view.action.api;

import java.util.List;

public interface AllActionGroups {
	ApplicationActions application();
	AboutActions about();
	NavigationActions navigation();
	SlideshowActions slideshow();
	RasterActions raster();
	List<ActionGroup> allGroups();
}
