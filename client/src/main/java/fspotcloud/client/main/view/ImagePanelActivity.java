package fspotcloud.client.main.view;

import java.util.logging.Logger;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

import fspotcloud.client.main.view.api.ImagePanelView;
import fspotcloud.client.main.view.api.ImagePanelView.ImagePanelPresenter;
import fspotcloud.client.main.view.api.ImageRasterPresenterFactory;
import fspotcloud.client.main.view.api.ImageRasterView;
import fspotcloud.client.main.view.api.PagerPresenterFactory;
import fspotcloud.client.main.view.api.SlideshowPresenterFactory;
import fspotcloud.client.main.view.api.SlideshowView;
import fspotcloud.client.place.BasePlace;

public class ImagePanelActivity extends AbstractActivity implements
		ImagePanelPresenter {
	private static final Logger log = Logger.getLogger(ImagePanelActivity.class
			.getName());
	final protected ImagePanelView imagePanelView;
	final private ImageRasterView.ImageRasterPresenter imageRasterPresenter;
	final private SlideshowView.SlideshowPresenter slideshowPresenter;
	final private ImageRasterPresenterFactory imageRasterFactory;
	final private PagerPresenterFactory pagerPresenterFactory;
	final private SlideshowPresenterFactory slideshowPresenterFactory;

	@Inject
	public ImagePanelActivity(@Assisted BasePlace place,
			ImagePanelView imagePanelView,
			ImageRasterPresenterFactory imageRasterFactory,
			PagerPresenterFactory pagerPresenterFactory,
			SlideshowPresenterFactory slideshowPresenterFactory) {
		this.imagePanelView = imagePanelView;
		this.slideshowPresenterFactory = slideshowPresenterFactory;
		this.pagerPresenterFactory = pagerPresenterFactory;
		this.imageRasterFactory = imageRasterFactory;
		pagerPresenterFactory.get(place, imagePanelView.getPagerView());
		this.imageRasterPresenter = imageRasterFactory.get(place,
				imagePanelView.getImageRasterView());
		this.slideshowPresenter = slideshowPresenterFactory.get(imagePanelView
				.getSlideshowView());
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(imagePanelView);
	}

	@Override
	public void init() {
		log.info("init");
		imageRasterPresenter.init();
		slideshowPresenter.init();
		log.info(imageRasterPresenter.getWidth() + ", "
				+ imageRasterPresenter.getHeight());
	}
}
