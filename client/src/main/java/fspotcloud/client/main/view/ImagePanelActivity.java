package fspotcloud.client.main.view;

import java.util.logging.Logger;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

import fspotcloud.client.main.view.api.ButtonPanelPresenterFactory;
import fspotcloud.client.main.view.api.ButtonPanelView;
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
	final private ButtonPanelView.ButtonPanelPresenter buttonPanelPresenter;
	@Inject
	public ImagePanelActivity(@Assisted BasePlace place,
			ImagePanelView imagePanelView,
			ImageRasterPresenterFactory imageRasterFactory,
			PagerPresenterFactory pagerPresenterFactory,
			SlideshowPresenterFactory slideshowPresenterFactory,
			ButtonPanelPresenterFactory buttonPanelPresenterFactory) {
		this.imagePanelView = imagePanelView;
		pagerPresenterFactory.get(place, imagePanelView.getPagerView());
		this.imageRasterPresenter = imageRasterFactory.get(place,
				imagePanelView.getImageRasterView());
		this.slideshowPresenter = slideshowPresenterFactory.get(imagePanelView
				.getSlideshowView());
		this.buttonPanelPresenter = buttonPanelPresenterFactory.get(imagePanelView.getButtonPanelView());
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		log.info("In start before setWidget panelView: size " + imagePanelView.asWidget().getOffsetWidth() + ", "
				+ imagePanelView.asWidget().getOffsetHeight());
		panel.setWidget(imagePanelView);
		log.info("In start after setWidget panelView: size " + imagePanelView.asWidget().getOffsetWidth() + ", "
				+ imagePanelView.asWidget().getOffsetHeight());
		imageRasterPresenter.init();
		
	}

	@Override
	public void init() {
		log.info("init");
		
		slideshowPresenter.init();
		log.info("panelView: size " + imagePanelView.asWidget().getOffsetWidth() + ", "
				+ imagePanelView.asWidget().getOffsetHeight());
		buttonPanelPresenter.init();
	}
}
