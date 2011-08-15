package fspotcloud.client.view.action;

import java.util.Arrays;
import java.util.List;

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.resources.client.ImageResource;
import com.google.inject.Inject;

import fspotcloud.client.main.shared.ApplicationEvent;
import fspotcloud.client.main.shared.ApplicationEventProviderFactory;
import fspotcloud.client.main.ui.Resources;
import fspotcloud.client.view.action.api.ApplicationActions;
import fspotcloud.client.view.action.api.ShortcutAssistedFactory;
import fspotcloud.client.view.action.api.UserAction;

public class ApplicationActionsImpl extends ActionsFactory implements
		ApplicationActions {

	public UserAction TOGGLE_BUTTONS_VISIBLE;
	public UserAction TOGGLE_FULLSCREEN;
	public UserAction TREE_FOCUS;
	public UserAction TOGGLE_HELP;
	public UserAction START_DEMO;
	public UserAction DASHBOARD;
	public UserAction ABOUT;
	private List<UserAction> all;

	final private ApplicationEventProviderFactory application;

	@Inject
	public ApplicationActionsImpl(ShortcutAssistedFactory shortcutFactory,
			ApplicationEventProviderFactory application, Resources resources) {
		super(shortcutFactory, resources);
		this.application = application;
		init();
	}

	private void init() {
		TOGGLE_HELP = createApplication("help", "Help",
				"Display a popup with the keyboard shortcuts", 'H',
				KeyCodes.KEY_ESCAPE, resources.helpIcon(), ApplicationEvent.ActionType.HELP);
		START_DEMO = createApplication("demo", "Demo", "Play a demo", '7',
				null, resources.demoIcon(), ApplicationEvent.ActionType.DEMO);
		TREE_FOCUS = createApplication("tree", "Focus tree",
				"Puts keyboard focus on the category tree", KeyCodes.KEY_ENTER,
				null, resources.treeFocusIcon(), ApplicationEvent.ActionType.TREE_FOCUS);
		TOGGLE_FULLSCREEN = createApplication("fullscreen", "Show/Hide tree",
				"Toggles fullscreen (hides/shows the tree view)", 'F', null,
				resources.fullscreenIcon(), ApplicationEvent.ActionType.TOGGLE_TREE_VISIBLE);
		TOGGLE_BUTTONS_VISIBLE = createApplication("toggle-buttons", "Show/Hide buttons",
				"Toggles visibility of the buttons", 'B', null,
				resources.toggleButtonsIcon(), ApplicationEvent.ActionType.TOGGLE_BUTTONS_VISIBLE);
		DASHBOARD = createApplication("dashboard", "Dashboard",
				"Go to the dashboard (admin only)", 'D', null, resources.dashboardIcon(),
				ApplicationEvent.ActionType.DASHBOARD);
		ABOUT = createApplication("about", "About",
				"About this open source project", 'A', null, resources.aboutIcon(),
				ApplicationEvent.ActionType.ABOUT);
		all = Arrays.asList(TOGGLE_HELP, TOGGLE_FULLSCREEN, TOGGLE_BUTTONS_VISIBLE, START_DEMO,
				TREE_FOCUS, DASHBOARD, ABOUT);
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
	public UserAction toggleTreeVisible() {
		return TOGGLE_FULLSCREEN;
	}

	@Override
	public UserAction treeFocus() {
		return TREE_FOCUS;
	}

	@Override
	public UserAction toggleHelp() {
		return TOGGLE_HELP;
	}

	@Override
	public UserAction demo() {
		return START_DEMO;
	}

	@Override
	public String getDescription() {
		return "Application";
	}

}
