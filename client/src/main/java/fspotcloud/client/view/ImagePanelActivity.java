package fspotcloud.client.view;

import java.util.logging.Logger;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import fspotcloud.client.main.Navigator;
import fspotcloud.client.view.ImagePanelView.ImagePanelPresenter;
import fspotcloud.client.view.ImageView.ImagePresenter;
import fspotcloud.client.view.PagerView.PagerPresenter;

public class ImagePanelActivity extends AbstractActivity implements
		ImagePanelPresenter {
	private static final Logger log = Logger.getLogger(ImagePanelActivity.class
			.getName());
	final private ImagePanelView imagePanelView;
	final private BasePlace place;
	final private PagerPresenter pagerPresenter;
	final private ImagePresenter imagePresenter;
	final private EventBus eventBus;
	final private SlideShowPresenterImpl slideshowPresenter;

	public ImagePanelActivity(BasePlace place, ImagePanelView imagePanelView,
			EventBus eventBus, Navigator navigator) {
		this.imagePanelView = imagePanelView;
		this.place = place;
		this.eventBus = eventBus;
		this.pagerPresenter = new PagerPresenterImpl(place,
				imagePanelView.getPagerView(), navigator);
		this.imagePresenter = new ImagePresenterImpl(place,
				imagePanelView.getImageView());
		this.slideshowPresenter = new SlideShowPresenterImpl(
				imagePanelView.getSlideshowView(), eventBus);
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(imagePanelView);
	}

	@Override
	public void init() {
		log.info("init");
		imagePresenter.init();

	}

}
