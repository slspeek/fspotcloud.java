package fspotcloud.client.view;

import java.util.logging.Logger;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import fspotcloud.client.main.Navigator;
import fspotcloud.client.main.Slideshow;

public class FullscreenImagePanelActivity extends ImagePanelActivity {
	private static final Logger log = Logger
			.getLogger(FullscreenImagePanelActivity.class.getName());

	public FullscreenImagePanelActivity(BasePlace place,
			ImagePanelView imagePanelView, Navigator navigator,
			Slideshow slideshow) {
		super(place, imagePanelView, navigator, slideshow);
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
