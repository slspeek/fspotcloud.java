package fspotcloud.client.view.action.api;

public interface SlideshowActions extends ActionGroup {
	UserAction startSlideshow();
	UserAction stopSlideshow();
	UserAction slower();
	UserAction faster();
}
