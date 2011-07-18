package fspotcloud.client.main.gin;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

import fspotcloud.client.main.view.api.ImageView;

@GinModules({ActionModule.class, AppModule.class})
public interface ActionGinjector extends Ginjector {

	ImageView get();
}
