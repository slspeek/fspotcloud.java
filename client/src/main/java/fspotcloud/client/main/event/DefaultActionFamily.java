package fspotcloud.client.main.event;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import fspotcloud.client.view.action.api.ActionGroup;
import fspotcloud.client.view.action.api.UserAction;

public class DefaultActionFamily extends AbstractActionFamily {

	@SuppressWarnings("unused")
	private static final Logger log = Logger
			.getLogger(DefaultActionFamily.class.getName());

	List<ActionGroup> allGroups;
	List<UserAction> allActions = new ArrayList<UserAction>();

	@Inject
	public DefaultActionFamily(
			@Named("application") AbstractActionMap application,
			@Named("navigation") AbstractActionMap navigation,
			@Named("slideshow") AbstractActionMap slideshow,
			@Named("raster") AbstractActionMap raster,
			@Named("about") AbstractActionMap about) {
		super("All action groups");
		addMap(application);
		addMap(about);
		addMap(raster);
		addMap(navigation);
		addMap(slideshow);
		initAllActions();
	}

	private void initAllActions() {
		for (ActionMap group : allMaps()) {
			allActions.addAll(group.allActions());
		}
	}
}
