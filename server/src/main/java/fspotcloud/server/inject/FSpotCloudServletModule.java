package fspotcloud.server.inject;

import java.util.logging.Logger;

import net.customware.gwt.dispatch.server.guice.GuiceStandardDispatchServlet;

import com.google.inject.servlet.ServletModule;

import fspotcloud.server.cron.CronServlet;
import fspotcloud.server.main.ImageServlet;
import fspotcloud.server.main.TagServiceImpl;

public class FSpotCloudServletModule extends ServletModule {

	@SuppressWarnings("unused")
	private static final Logger log = Logger
			.getLogger(FSpotCloudServletModule.class.getName());

	@Override
	protected void configureServlets() {
		serve("/fspotcloud/dispatch").with(GuiceStandardDispatchServlet.class);
		serve("/fspotcloud.dashboard/dispatch").with(
				GuiceStandardDispatchServlet.class);
		serve("/fspotcloud/tag").with(TagServiceImpl.class);
		serve("/fspotcloud.dashboard/tag").with(TagServiceImpl.class);
		
		serve("/image").with(ImageServlet.class);
		serve("/cron").with(CronServlet.class);
	}

}
