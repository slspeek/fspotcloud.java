package fspotcloud.client.admin.gin;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

import fspotcloud.client.admin.MVPSetup;

@GinModules(AdminModule.class)
public interface AdminGinjector extends Ginjector {

	MVPSetup getMVPSetup();
	
}
