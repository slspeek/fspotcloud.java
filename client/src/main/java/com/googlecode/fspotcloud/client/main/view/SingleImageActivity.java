package com.googlecode.fspotcloud.client.main.view;

import java.util.logging.Logger;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import com.googlecode.fspotcloud.client.main.event.application.ApplicationEvent;
import com.googlecode.fspotcloud.client.main.event.application.ApplicationType;
import com.googlecode.fspotcloud.client.main.view.api.ImageRasterView;
import com.googlecode.fspotcloud.client.main.view.api.SingleImageView;
import com.googlecode.fspotcloud.client.view.action.KeyStroke;

public class SingleImageActivity extends AbstractActivity implements
		SingleImageView.SingleImagePresenter {
	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(SingleImageActivity.class
			.getName());
	
	final private SingleImageView singleImageView;
	final private ImageRasterView.ImageRasterPresenter imageRasterPresenter;
	private EventBus eventBus;
	
	public SingleImageActivity(SingleImageView imageView,
			ImageRasterView.ImageRasterPresenter imageRasterPresenter){
		this.singleImageView = imageView;
		this.imageRasterPresenter = imageRasterPresenter;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		singleImageView.setPresenter(this);
		this.eventBus = eventBus;
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
}
