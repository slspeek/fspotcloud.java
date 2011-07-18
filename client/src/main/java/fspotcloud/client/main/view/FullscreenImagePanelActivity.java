package fspotcloud.client.main.view;

import java.util.logging.Logger;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

import fspotcloud.client.main.Navigator;
import fspotcloud.client.main.Slideshow;
import fspotcloud.client.main.view.api.ImagePanelView;
import fspotcloud.client.place.BasePlace;

public class FullscreenImagePanelActivity extends ImagePanelActivity {
	private static final Logger log = Logger
			.getLogger(FullscreenImagePanelActivity.class.getName());

	@Inject
	public FullscreenImagePanelActivity(@Assisted BasePlace place,
			ImagePanelView imagePanelView, Navigator navigator,
			Slideshow slideshow, EventBus eventBus, Navigator pager) {
		super(place, imagePanelView, navigator, slideshow, eventBus, pager);
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		adjustSize();
		super.start(panel, eventBus);
	}

	private void adjustSize() {
		int width = Window.getClientWidth();
		int height = Window.getClientHeight();
		imagePanelView.setSize(width, height);
		log.info("set size: " + width + " , " + height);
	}

}
