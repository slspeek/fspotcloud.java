package fspotcloud.client.main.view;

import java.util.logging.Logger;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import fspotcloud.client.main.Navigator;
import fspotcloud.client.main.Slideshow;
import fspotcloud.client.main.view.api.ImagePanelView;
import fspotcloud.client.main.view.api.ImagePanelView.ImagePanelPresenter;
import fspotcloud.client.main.view.api.ImageRasterView;
import fspotcloud.client.place.BasePlace;

public class ImagePanelActivity extends AbstractActivity implements
		ImagePanelPresenter {
	private static final Logger log = Logger.getLogger(ImagePanelActivity.class
			.getName());
	final protected ImagePanelView imagePanelView;
	final private ImageRasterView.ImageRasterPresenter imageRasterPresenter;
	final private SlideShowPresenterImpl slideshowPresenter;
	
	public ImagePanelActivity(BasePlace place, ImagePanelView imagePanelView,
			Navigator navigator, Slideshow slideshow, EventBus eventBus, Navigator pager) {
		this.imagePanelView = imagePanelView;
		new PagerPresenterImpl(place,
				imagePanelView.getPagerView(), navigator);
		this.imageRasterPresenter = new ImageRasterPresenterImpl(place,
				imagePanelView.getImageRasterView(), pager, eventBus);
		this.slideshowPresenter = new SlideShowPresenterImpl(
				imagePanelView.getSlideshowView(), slideshow, eventBus);
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
		log.info(imageRasterPresenter.getWidth() + ", "+ imageRasterPresenter.getHeight());
	}
}
