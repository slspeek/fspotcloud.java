package com.googlecode.fspotcloud.client.main.view;

import java.util.logging.Logger;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import com.googlecode.fspotcloud.client.main.view.api.ImageRasterView;
import com.googlecode.fspotcloud.client.main.view.api.TagView;

public class TagActivity extends AbstractActivity implements
		TagView.TagPresenter {
	
	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(TagActivity.class
			.getName());

	final private TagView tagView;
	final private ImageRasterView.ImageRasterPresenter imageRasterPresenter;
	
	public TagActivity(TagView tagView, ImageRasterView.ImageRasterPresenter imageRasterPresenter) {
		this.tagView = tagView;
		this.imageRasterPresenter = imageRasterPresenter;
	}

	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
		containerWidget.setWidget(tagView);
		Scheduler scheduler = Scheduler.get();
		scheduler.scheduleDeferred(new ScheduledCommand() {
			@Override
			public void execute() {
				imageRasterPresenter.init();
			}
		});
		tagView.hideLabelLater(10000);
	}
}
