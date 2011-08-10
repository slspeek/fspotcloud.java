package fspotcloud.client.main.view;

import java.util.logging.Logger;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

import fspotcloud.client.main.view.api.ImagePresenterFactory;
import fspotcloud.client.main.view.api.ImageRasterView;
import fspotcloud.client.place.BasePlace;
import fspotcloud.client.place.api.Navigator;

public class ImageRasterActivity extends ImageRasterPresenterImpl implements
		ImageRasterView.ImageRasterPresenter {
	private static final Logger log = Logger
			.getLogger(ImageRasterActivity.class.getName());

	@Inject
	public ImageRasterActivity(@Assisted BasePlace place,
			ImageRasterView imageRasterView, Navigator pager,
			ImagePresenterFactory imagePresenterFactory) {
		super(place, imageRasterView, pager, imagePresenterFactory);
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(imageRasterView);
		adjustSize();
		init();
	}

	private void adjustSize() {
		int width = Window.getClientWidth();
		int height = Window.getClientHeight();
		imageRasterView.asWidget().setPixelSize(width, height);
		log.info("set size: " + width + " , " + height);
	}

}
