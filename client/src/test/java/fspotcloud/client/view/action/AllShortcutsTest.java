package fspotcloud.client.view.action;

import java.util.logging.Logger;

import junit.framework.TestCase;

import com.google.gwt.inject.rebind.adapter.GinModuleAdapter;
import com.google.inject.Guice;
import com.google.inject.Injector;

import fspotcloud.client.main.gin.ActionModule;
import fspotcloud.client.view.action.api.AllUserActions;

public class AllShortcutsTest extends TestCase {
	//private final Injector injector = Guice.createInjector(new GinModuleAdapter(new ActionModule()));

	private static final Logger log = Logger.getLogger(AllShortcutsTest.class
			.getName());
	
	//private AllUserActions actions = injector.getInstance(AllUserActions.class);
	
	
	public void testOne() {
		//assertNotNull(actions);
	}

}
