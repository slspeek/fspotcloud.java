package fspotcloud.client.main;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

import fspotcloud.client.main.gin.AppGinjector;

public class TagEntryPoint implements EntryPoint {

	private final AppGinjector injector = GWT.create(AppGinjector.class);

	private static final Logger log = Logger.getLogger(TagEntryPoint.class
			.getName());

	@Override
	public void onModuleLoad() {
		log.info("New event handling, more keys coming");
		try {
			MVPSetup setup = injector.getMVPSetup();
			log.info("gin fininshed the constuction of the application graph");
			setup.setup();
		} catch (Throwable e) {
			log.log(Level.SEVERE, "Uncaught exception", e);
		}
	}
}
