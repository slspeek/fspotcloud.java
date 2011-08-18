package fspotcloud.client.main.gin;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

import fspotcloud.client.main.MVPSetup;
import fspotcloud.client.main.event.EventModule;

@GinModules({AppModule.class,  EventModule.class})
public interface AppGinjector extends Ginjector {

	MVPSetup getMVPSetup();
	
}
