package fspotcloud.client.main.event.about;

import com.google.gwt.resources.client.ImageResource;
import com.google.inject.Inject;

import fspotcloud.client.main.event.AbstractActionMap;
import fspotcloud.client.main.ui.Resources;
import fspotcloud.client.view.action.api.ActionDef;
import fspotcloud.client.view.action.api.UserActionFactory;

public class AboutMapBuilder extends AbstractActionMap {

	private Resources resources;

	@Inject
	public AboutMapBuilder(UserActionFactory userActionFactory,
			Resources resources) {
		super(userActionFactory, "About");
		this.resources = resources;
	}

	
	public void buildMap() {
		put(AboutType.PROJECT_HOSTING, resources.projectSiteIcon());
		put(AboutType.F_SPOT, resources.fspotIcon());
		put(AboutType.MAVEN, resources.mavenIcon());
		put(AboutType.LICENSE, resources.licenceIcon());
		put(AboutType.PROTON, resources.protonIcon());
		put(AboutType.STEVEN, resources.authorIcon());
	}
	
	private void put(ActionDef actionDef, ImageResource icon) {
		put(actionDef, icon,
				new AboutEventProvider(actionDef));
	}
}
