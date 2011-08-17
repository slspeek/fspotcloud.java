package fspotcloud.client.view.action;

import java.util.Arrays;
import java.util.List;

import com.google.gwt.resources.client.ImageResource;
import com.google.inject.Inject;

import fspotcloud.client.main.shared.ApplicationEvent;
import fspotcloud.client.main.shared.ApplicationEventProviderFactory;
import fspotcloud.client.main.ui.Resources;
import fspotcloud.client.view.action.api.AboutActions;
import fspotcloud.client.view.action.api.UserActionFactory;
import fspotcloud.client.view.action.api.UserAction;

public class AboutActionsImpl extends ActionsFactory implements AboutActions {

	public UserAction MAVEN, PROJECT_HOSTING, STEVEN, PROTON, LICENSE;
	private List<UserAction> all;

	final private ApplicationEventProviderFactory application;

	@Inject
	public AboutActionsImpl(UserActionFactory shortcutFactory,
			ApplicationEventProviderFactory application, Resources resources) {
		super(shortcutFactory, resources);
		this.application = application;
		init();
	}

	private void init() {
		PROJECT_HOSTING = createApplication("project-hosting", "Project site",
				"Go to the site on Google Project Hosting", 'J', null,
				resources.projectSiteIcon(),
				ApplicationEvent.ActionType.PROJECT_HOSTING);
		MAVEN = createApplication("maven", "Maven site",
				"Go to the Maven generated site", 'M', null,
				resources.mavenIcon(), ApplicationEvent.ActionType.MAVEN);
		LICENSE = createApplication("license", "License", "View the license",
				'L', null, resources.licenceIcon(),
				ApplicationEvent.ActionType.LICENSE);
		PROTON = createApplication("proton", "Proton radio",
				"Go to the Proton site", 'P', null, resources.protonIcon(),
				ApplicationEvent.ActionType.PROTON);

		STEVEN = createApplication("steven", "Authors website",
				"Go to the authors website", 'Z', null, resources.authorIcon(),
				ApplicationEvent.ActionType.STEVEN);

		all = Arrays.asList(LICENSE, PROJECT_HOSTING, MAVEN, PROTON, STEVEN);
	}

	public UserAction createApplication(String id, String caption,
			String description, int key, Integer altKey, ImageResource icon,
			ApplicationEvent.ActionType actionType) {
		return create(id, caption, key, altKey, description, icon,
				application.get(actionType));
	}

	@Override
	public List<UserAction> allActions() {
		return all;
	}

	@Override
	public String getDescription() {
		return "About";
	}

}
