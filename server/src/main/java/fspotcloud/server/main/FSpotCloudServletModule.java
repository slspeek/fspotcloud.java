package fspotcloud.server.main;

import com.google.inject.servlet.ServletModule;

import fspotcloud.server.control.GuiceXmlRpcServlet;
import fspotcloud.server.control.TagImportServlet;

public class FSpotCloudServletModule extends ServletModule {

	
	@Override protected void configureServlets() {
		  serve("/guice").with(TagImportServlet.class);
		  serve("/xmlrpc").with(GuiceXmlRpcServlet.class);
	  }
	
}
