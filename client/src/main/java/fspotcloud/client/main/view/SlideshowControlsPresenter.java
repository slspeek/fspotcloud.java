package fspotcloud.client.main.view;

import java.util.logging.Logger;

import fspotcloud.client.main.view.api.ButtonPanelView;
import fspotcloud.client.main.view.api.SlideshowView;

public class SlideshowControlsPresenter implements ButtonPanelView.ButtonPanelPresenter  {
	
	private static final Logger log = Logger
			.getLogger(SlideshowControlsPresenter.class.getName());

	@SuppressWarnings("unused")
	private final SlideshowView.SlideshowPresenter slideshowPresenter;
	private final ButtonPanelView view;
	
	public SlideshowControlsPresenter(
			SlideshowView.SlideshowPresenter slideshowPresenter, ButtonPanelView view) {
		super();
		this.slideshowPresenter = slideshowPresenter;
		this.view = view;
		log.info("created");
	}

	
}
