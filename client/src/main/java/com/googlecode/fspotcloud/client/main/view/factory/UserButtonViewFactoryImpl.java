package com.googlecode.fspotcloud.client.main.view.factory;

import com.google.gwt.resources.client.ImageResource;
import com.google.inject.Inject;

import com.googlecode.fspotcloud.client.main.ui.Resources;
import com.googlecode.fspotcloud.client.main.ui.UserButtonViewImpl;
import com.googlecode.fspotcloud.client.main.view.api.UserButtonView;
import com.googlecode.fspotcloud.client.main.view.api.UserButtonViewFactory;
import com.googlecode.fspotcloud.client.view.action.api.UserAction;

public class UserButtonViewFactoryImpl implements UserButtonViewFactory{

	final private Resources resources;	
	
	@Inject
	public UserButtonViewFactoryImpl(Resources resources) {
		super();
		this.resources = resources;
	}

	@Override
	public UserButtonView get(UserAction action) {
		ImageResource img = action.getIcon();
		UserButtonView result;
		if (img != null) {
			result = new UserButtonViewImpl(resources,img);
		} else {
			result = new UserButtonViewImpl(resources);
		}
		return result;
	}
	
}
