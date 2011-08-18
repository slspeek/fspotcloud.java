package fspotcloud.client.main.event.navigation;

import com.google.gwt.resources.client.ImageResource;
import com.google.inject.Inject;

import fspotcloud.client.main.event.AbstractActionMap;
import fspotcloud.client.main.ui.Resources;
import fspotcloud.client.view.action.api.ActionDef;
import fspotcloud.client.view.action.api.UserActionFactory;

public class NavigationMapBuilder extends AbstractActionMap {

	private Resources resources;

	@Inject
	public NavigationMapBuilder(UserActionFactory userActionFactory,
			Resources resources) {
		super(userActionFactory, "Navigation");
		this.resources = resources;
	}

	
	public void buildMap() {
		put(NavigationType.HOME, resources.homeIcon());
		put(NavigationType.END, resources.endIcon());
		put(NavigationType.BACK, resources.backIcon());
		put(NavigationType.NEXT,resources.nextIcon());
	}
	
	private void put(ActionDef actionDef, ImageResource icon) {
		put(actionDef, icon,
				new NavigationEventProvider(actionDef));
	}
}
