package fspotcloud.client.main.view;

import java.util.logging.Logger;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import fspotcloud.client.main.view.api.ImageRasterView;
import fspotcloud.client.main.view.api.SingleImageView;
import fspotcloud.client.place.BasePlace;

public class SingleImageActivity extends AbstractActivity implements
		SingleImageView.SingleImagePresenter {
	private static final Logger log = Logger.getLogger(SingleImageActivity.class
			.getName());
	
	final private BasePlace place;
	final private SingleImageView singleImageView;
	final private ImageRasterView.ImageRasterPresenter imageRasterPresenter;
	
	
	public SingleImageActivity(BasePlace place,
			SingleImageView imageView,
			ImageRasterView.ImageRasterPresenter imageRasterPresenter){
		this.singleImageView = imageView;
		this.place = place;
		this.imageRasterPresenter = imageRasterPresenter;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		//adjustSize();
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

	private void adjustSize() {
		int width = Window.getClientWidth();
		int height = Window.getClientHeight();
		singleImageView.asWidget().setPixelSize(width, height);
		log.info("set size: " + width + " , " + height);
	}
}
