package fspotcloud.client.main.gin;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

import fspotcloud.client.main.MVPSetup;
import fspotcloud.client.main.event.EventModule;
import fspotcloud.client.main.view.factory.ButtonPanelPresenterProvider;
import net.customware.gwt.dispatch.client.gin.StandardDispatchModule;

@GinModules({AppModule.class,  EventModule.class, StandardDispatchModule.class })
public interface AppGinjector extends Ginjector {

	MVPSetup getMVPSetup();
	
	ButtonPanelPresenterProvider getButtonPanelPresenterProvider();
}
