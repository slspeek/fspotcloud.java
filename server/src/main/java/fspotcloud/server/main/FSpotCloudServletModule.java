package fspotcloud.server.main;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import com.google.inject.servlet.ServletModule;

import fspotcloud.server.admin.AdminServiceImpl;
import fspotcloud.server.admin.task.PhotoCountTaskServlet;
import fspotcloud.server.admin.task.PhotoDeleteTaskServlet;
import fspotcloud.server.control.GuiceXmlRpcServlet;
import fspotcloud.server.control.Scheduler;
import fspotcloud.server.control.TagImportServlet;
import fspotcloud.server.control.task.ImageDataTaskServlet;
import fspotcloud.server.control.task.PhotoDataTaskServlet;
import fspotcloud.server.main.task.TagViewTaskServlet;

public class FSpotCloudServletModule extends ServletModule {

	private static final Logger log = Logger.getLogger(FSpotCloudServletModule.class
			.getName());
	@Override
	protected void configureServlets() {
		serve("/cron/import_tags").with(TagImportServlet.class);
		serve("/control/task/photoData").with(PhotoDataTaskServlet.class);
		serve("/main/task/tagView").with(TagViewTaskServlet.class);
		serve("/fspotcloud/tag").with(TagServiceImpl.class);
		serve("/celltree/tag").with(TagServiceImpl.class);
		serve("/fspotcloud.admin/tag").with(TagServiceImpl.class);
		serve("/admin/task/photoCount").with(PhotoCountTaskServlet.class);
		serve("/admin/task/photoDelete").with(PhotoDeleteTaskServlet.class);
		serve("/control/task/imageData").with(ImageDataTaskServlet.class);
		serve("/image").with(ImageServlet.class);
		serve("/fspotcloud.admin/admin").with(AdminServiceImpl.class);

		Map<String, String> params = new HashMap<String, String>();
		params.put("enabledForExtensions", "true");
		String botSecret = System.getProperty("bot.secret");
		serve("/xmlrpc/" + botSecret).with(GuiceXmlRpcServlet.class, params);
		log.info("bot.secret: " + botSecret);
	}

}
