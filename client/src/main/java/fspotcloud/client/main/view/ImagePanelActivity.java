package fspotcloud.client.main.view;

import java.util.logging.Logger;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
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
import fspotcloud.client.place.BasePlace;

public class ImagePanelActivity extends AbstractActivity implements
		ImagePanelPresenter {
	private static final Logger log = Logger.getLogger(ImagePanelActivity.class
			.getName());
	final protected ImagePanelView imagePanelView;
	final private ImageRasterView.ImageRasterPresenter imageRasterPresenter;
	
	final private ButtonPanelView.ButtonPanelPresenter buttonPanelPresenter;
	@Inject
	public ImagePanelActivity(@Assisted BasePlace place,
			ImagePanelView imagePanelView,
			ImageRasterPresenterFactory imageRasterFactory,
			ButtonPanelPresenterFactory buttonPanelPresenterFactory) {
		this.imagePanelView = imagePanelView;
		this.imageRasterPresenter = imageRasterFactory.get(place,
				imagePanelView.getImageRasterView());
		this.buttonPanelPresenter = buttonPanelPresenterFactory.get(imagePanelView.getButtonPanelView());
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(imagePanelView);
		Scheduler scheduler = Scheduler.get();
		scheduler.scheduleDeferred(new ScheduledCommand() {
			@Override
			public void execute() {
				imageRasterPresenter.init();
			}
			
		});
	}

	@Override
	public void init() {
		log.info("init");
		buttonPanelPresenter.init();
	}
}
