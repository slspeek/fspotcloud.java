package fspotcloud.client.main.view.factory;

import com.google.gwt.resources.client.ImageResource;

import fspotcloud.client.main.ui.UserButtonViewImpl;
import fspotcloud.client.main.view.api.UserButtonView;
import fspotcloud.client.main.view.api.UserButtonViewFactory;
import fspotcloud.client.view.action.api.UserAction;

public class UserButtonViewFactoryImpl implements UserButtonViewFactory{

	@Override
	public UserButtonView get(UserAction action) {
		ImageResource img = action.getIcon();
		UserButtonView result;
		if (img != null) {
			result = new UserButtonViewImpl(img);
		} else {
			result = new UserButtonViewImpl();
		}
		return result;
	}
	
}
