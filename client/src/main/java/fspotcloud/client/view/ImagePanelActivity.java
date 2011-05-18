package fspotcloud.client.view;

import java.util.logging.Logger;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import fspotcloud.client.main.Navigator;
import fspotcloud.client.main.Slideshow;
import fspotcloud.client.view.ImagePanelView.ImagePanelPresenter;
import fspotcloud.client.view.ImageView.ImagePresenter;

public class ImagePanelActivity extends AbstractActivity implements
		ImagePanelPresenter {
	private static final Logger log = Logger.getLogger(ImagePanelActivity.class
			.getName());
	final protected ImagePanelView imagePanelView;
	final private ImagePresenter imagePresenter;
	final private SlideShowPresenterImpl slideshowPresenter;
	@SuppressWarnings("unused")
	final private PagerPresenterImpl pagerPresenter;

	public ImagePanelActivity(BasePlace place, ImagePanelView imagePanelView,
			Navigator navigator, Slideshow slideshow) {
		this.imagePanelView = imagePanelView;
		this.pagerPresenter = new PagerPresenterImpl(place,
				imagePanelView.getPagerView(), navigator);
		this.imagePresenter = new ImagePresenterImpl(place,
				imagePanelView.getImageView());
		this.slideshowPresenter = new SlideShowPresenterImpl(
				imagePanelView.getSlideshowView(), slideshow);
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(imagePanelView);
	}

	@Override
	public void init() {
		log.info("init");
		imagePresenter.init();
		slideshowPresenter.init();
	}
}
