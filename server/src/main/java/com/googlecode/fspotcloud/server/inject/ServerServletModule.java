package com.googlecode.fspotcloud.server.inject;

import java.util.logging.Logger;

import net.customware.gwt.dispatch.server.guice.GuiceStandardDispatchServlet;

import com.google.inject.servlet.ServletModule;

import com.googlecode.fspotcloud.server.cron.CronServlet;
import com.googlecode.fspotcloud.server.main.ImageServlet;
import com.googlecode.fspotcloud.server.main.TagServiceImpl;

public class ServerServletModule extends ServletModule {

	@SuppressWarnings("unused")
	private static final Logger log = Logger
			.getLogger(ServerServletModule.class.getName());

	@Override
	protected void configureServlets() {
		serve("/fspotcloud/dispatch").with(GuiceStandardDispatchServlet.class);
		serve("/com.googlecode.fspotcloud.dashboard/dispatch").with(
				GuiceStandardDispatchServlet.class);
		serve("/fspotcloud/tag").with(TagServiceImpl.class);
		serve("/com.googlecode.fspotcloud.dashboard/tag").with(TagServiceImpl.class);
		
		serve("/image").with(ImageServlet.class);
		serve("/cron").with(CronServlet.class);
	}

}
