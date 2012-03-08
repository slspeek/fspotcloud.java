package com.googlecode.fspotcloud.client.main.ui;

import java.util.logging.Logger;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.HasOneWidget;

public class ViewFactory {
	private static final Logger log = Logger
	.getLogger(ViewFactory.class.getName());

	final private EventBus eventBus;
	
	public ViewFactory(EventBus eventBus) {
		this.eventBus = eventBus;
	}
	
	public void register(ActivityMapper mapper, HasOneWidget display) {
		ActivityManager manager = new ActivityManager(mapper, eventBus);
		manager.setDisplay(display);
		log.info("registered: " + mapper);
	}
}
