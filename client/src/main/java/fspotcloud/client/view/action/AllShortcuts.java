package fspotcloud.client.view.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import com.google.inject.Inject;

import fspotcloud.client.view.action.api.AboutActions;
import fspotcloud.client.view.action.api.ActionGroup;
import fspotcloud.client.view.action.api.AllUserActions;
import fspotcloud.client.view.action.api.ApplicationActions;
import fspotcloud.client.view.action.api.NavigationActions;
import fspotcloud.client.view.action.api.RasterActions;
import fspotcloud.client.view.action.api.SlideshowActions;
import fspotcloud.client.view.action.api.UserAction;

public class AllShortcuts implements AllUserActions {
	private static final Logger log = Logger.getLogger(AllShortcuts.class
			.getName());

	final private ApplicationActions application;
	final private NavigationActions navigation;
	final private SlideshowActions slideshow;
	final private RasterActions raster;
	final private AboutActions about;

	List<ActionGroup> allGroups;
	List<UserAction> allActions = new ArrayList<UserAction>();

	@Inject
	public AllShortcuts(ApplicationActions application,
			NavigationActions navigation, SlideshowActions slideshow,
			RasterActions raster, AboutActions about) {
		super();
		this.about = about;
		this.application = application;
		this.navigation = navigation;
		this.slideshow = slideshow;
		this.raster = raster;
		allGroups = Arrays.asList(navigation, raster, slideshow, application,
				about);
		initAllActions();
	}

	private void initAllActions() {
		for (ActionGroup group : allGroups()) {
			allActions.addAll(group.allActions());
		}
	}

	@Override
	public ApplicationActions application() {
		return application;
	}

	@Override
	public NavigationActions navigation() {
		return navigation;
	}

	@Override
	public SlideshowActions slideshow() {
		return slideshow;
	}

	@Override
	public RasterActions raster() {
		return raster;
	}

	@Override
	public List<ActionGroup> allGroups() {
		return allGroups;
	}

	@Override
	public List<UserAction> allActions() {
		return allActions;
	}

	@Override
	public String getDescription() {
		return "All user actions";
	}

	@Override
	public AboutActions about() {
		return about;
	}

}
