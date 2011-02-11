package fspotcloud.client.view;

import fspotcloud.client.view.PagerView.PagerPresenter;

public interface Slideshow {
	void toggleSlideshow();

	void stopSlideshow();

	public void setPresenter(PagerPresenter presenter);
}
