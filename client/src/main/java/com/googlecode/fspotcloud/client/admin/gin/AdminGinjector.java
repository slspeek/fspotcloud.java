package com.googlecode.fspotcloud.client.admin.gin;

import net.customware.gwt.dispatch.client.gin.StandardDispatchModule;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

import com.googlecode.fspotcloud.client.admin.MVPSetup;

@GinModules({ AdminModule.class, StandardDispatchModule.class })
public interface AdminGinjector extends Ginjector {

	MVPSetup getMVPSetup();

}
