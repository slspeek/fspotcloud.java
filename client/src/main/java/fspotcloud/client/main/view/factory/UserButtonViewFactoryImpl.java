package fspotcloud.client.main.view.factory;

import com.google.gwt.resources.client.ImageResource;
import com.google.inject.Inject;

import fspotcloud.client.main.ui.Resources;
import fspotcloud.client.main.ui.UserButtonViewImpl;
import fspotcloud.client.main.view.api.UserButtonView;
import fspotcloud.client.main.view.api.UserButtonViewFactory;
import fspotcloud.client.view.action.api.UserAction;

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
