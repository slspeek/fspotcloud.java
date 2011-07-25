package fspotcloud.client.main.view;

import java.util.logging.Logger;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

import fspotcloud.client.main.view.api.ButtonPanelPresenterFactory;
import fspotcloud.client.main.view.api.ImagePanelView;
import fspotcloud.client.main.view.api.ImageRasterPresenterFactory;
import fspotcloud.client.main.view.api.SlideshowPresenterFactory;
import fspotcloud.client.place.BasePlace;

public class FullscreenImagePanelActivity extends ImagePanelActivity {
	@SuppressWarnings("unused")
	private static final Logger log = Logger
			.getLogger(FullscreenImagePanelActivity.class.getName());

	@Inject
	public FullscreenImagePanelActivity(@Assisted BasePlace place,
			ImagePanelView imagePanelView,
			ImageRasterPresenterFactory imageRasterFactory,
			SlideshowPresenterFactory slideshowPresenterFactory,
			ButtonPanelPresenterFactory buttonPanelPresenterFactory) {
		super(place, imagePanelView, imageRasterFactory,
				buttonPanelPresenterFactory);
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		super.start(panel, eventBus);
	}
}
