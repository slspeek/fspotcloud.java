package fspotcloud.client.main.gin;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

import fspotcloud.client.main.shared.ApplicationEventProviderFactory;
import fspotcloud.client.main.shared.EventModule;

@GinModules({ EventModule.class })
public interface EventGinjector extends Ginjector {
	ApplicationEventProviderFactory getApplicationEventFactory();
}
