package fspotcloud.client.admin;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

import fspotcloud.client.admin.gin.AdminGinjector;
import fspotcloud.client.admin.view.DashboardPresenter;

public class DashboardEntryPoint implements EntryPoint {

	private final AdminGinjector injector = GWT.create(AdminGinjector.class);

	private static final Logger log = Logger.getLogger(DashboardEntryPoint.class
			.getName());

	@Override
	public void onModuleLoad() {
		log.info("New dashboard");
		try {
			DashboardPresenter dashboard = injector.getDashboardPresenter();
			log.info("gin fininshed the constuction of the application graph");
			} catch (Throwable e) {
			log.log(Level.SEVERE, "Uncaught exception", e);
		}
	}
}
