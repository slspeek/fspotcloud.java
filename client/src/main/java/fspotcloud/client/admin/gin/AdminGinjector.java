package fspotcloud.client.admin.gin;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

import fspotcloud.client.admin.view.DashboardPresenter;

@GinModules(AdminModule.class)
public interface AdminGinjector extends Ginjector {

	DashboardPresenter getDashboardPresenter();
	
}
