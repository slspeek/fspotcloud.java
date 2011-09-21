package fspotcloud.client.main.view;

import java.util.logging.Logger;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

import fspotcloud.client.main.view.api.ImageRasterPresenterFactory;
import fspotcloud.client.main.view.api.ImageRasterView;
import fspotcloud.client.main.view.api.SingleImageView;
import fspotcloud.client.main.view.api.SlideshowControlsPresenterFactory;
import fspotcloud.client.place.BasePlace;

public class SingleImageActivity extends AbstractActivity implements
		SingleImageView.SingleImagePresenter {
	private static final Logger log = Logger.getLogger(SingleImageActivity.class
			.getName());
	
	private BasePlace place;
	private SlideshowControlsPresenterFactory slideshowControlsFactory;
	private ImageRasterPresenterFactory imageRasterPresenterFactory;
	private SingleImageView singleImageView;
	private ImageRasterView.ImageRasterPresenter imageRasterPresenter;
	
	@Inject
	public SingleImageActivity(@Assisted BasePlace place,
			SingleImageView imageView,
			ImageRasterPresenterFactory imageRasterPresenterFactory,
			SlideshowControlsPresenterFactory slideshowControlsFactory){
		this.singleImageView = imageView;
		this.place = place;
		this.imageRasterPresenterFactory = imageRasterPresenterFactory;
		this.slideshowControlsFactory = slideshowControlsFactory;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		adjustSize();
		init();
		panel.setWidget(singleImageView);
		Scheduler scheduler = Scheduler.get();
		scheduler.scheduleDeferred(new ScheduledCommand() {
			@Override
			public void execute() {
				imageRasterPresenter.init();
			}
			
		});
		singleImageView.hideControlsLater(3000);
	}

	@Override
	public void init() {
		log.info("init");
		SlideshowControlsPresenter presenter = slideshowControlsFactory.get(singleImageView.getButtonPanelView());
		presenter.init();
		imageRasterPresenter = imageRasterPresenterFactory.get(place, singleImageView.getImageRasterView());
	}

	private void adjustSize() {
		int width = Window.getClientWidth();
		int height = Window.getClientHeight();
		singleImageView.asWidget().setPixelSize(width, height);
		log.info("set size: " + width + " , " + height);
	}

}
