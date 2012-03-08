package com.googlecode.fspotcloud.client.admin;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.Widget;

import com.googlecode.fspotcloud.client.admin.gin.AdminGinjector;
import com.googlecode.fspotcloud.client.admin.view.DashboardPresenter;
import com.googlecode.fspotcloud.client.admin.view.api.DashboardView;

public class DashboardEntryPoint implements EntryPoint {

	private static final Logger log = Logger
			.getLogger(DashboardEntryPoint.class.getName());
	private final AdminGinjector injector = GWT.create(AdminGinjector.class);

	@Override
	public void onModuleLoad() {
		log.info("New dashboard");
		try {
			MVPSetup setup = injector.getMVPSetup();
			setup.setup();
			log.info("Setup finished");
		} catch (Throwable e) {
			log.log(Level.SEVERE, "Uncaught exception", e);
		}
	}
}
