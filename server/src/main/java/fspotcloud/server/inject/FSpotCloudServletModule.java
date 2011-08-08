package fspotcloud.server.inject;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import com.google.inject.servlet.ServletModule;

import fspotcloud.server.admin.AdminServiceImpl;
import fspotcloud.server.control.GuiceXmlRpcServlet;
import fspotcloud.server.control.task.DataTaskServlet;
import fspotcloud.server.main.ImageServlet;
import fspotcloud.server.main.TagServiceImpl;
import fspotcloud.server.mapreduce.CounterCompletedServlet;

public class FSpotCloudServletModule extends ServletModule {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(FSpotCloudServletModule.class
			.getName());
	@Override
	protected void configureServlets() {
		serve("/control/task/data").with(DataTaskServlet.class);
		serve("/fspotcloud/tag").with(TagServiceImpl.class);
		serve("/fspotcloud.dashboard/tag").with(TagServiceImpl.class);
		serve("/fspotcloud.dashboard/admin").with(AdminServiceImpl.class);
		serve("/callbacks/counter_completed").with(CounterCompletedServlet.class);
		serve("/image").with(ImageServlet.class);

		Map<String, String> params = new HashMap<String, String>();
		params.put("enabledForExtensions", "true");
		String botSecret = System.getProperty("bot.secret");
		serve("/xmlrpc/" + botSecret).with(GuiceXmlRpcServlet.class, params);
	}

}
