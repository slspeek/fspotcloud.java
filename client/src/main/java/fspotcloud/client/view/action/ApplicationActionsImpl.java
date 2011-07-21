package fspotcloud.client.view.action;

import java.util.Arrays;
import java.util.List;

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.resources.client.ImageResource;
import com.google.inject.Inject;

import fspotcloud.client.main.shared.ApplicationEvent;
import fspotcloud.client.main.shared.ApplicationEventProviderFactory;
import fspotcloud.client.view.action.api.ApplicationActions;
import fspotcloud.client.view.action.api.ShortcutAssistedFactory;
import fspotcloud.client.view.action.api.UserAction;

public class ApplicationActionsImpl extends ActionsFactory implements
		ApplicationActions {

	public UserAction TOGGLE_FULLSCREEN;
	public UserAction TREE_FOCUS;
	public UserAction TOGGLE_HELP;
	public UserAction START_DEMO; 
	private List<UserAction> all;

	final private ApplicationEventProviderFactory application;

	@Inject
	public ApplicationActionsImpl(ShortcutAssistedFactory shortcutFactory,
			ApplicationEventProviderFactory application) {
		super(shortcutFactory);
		this.application = application;
		init();
	}

	private void init() {
		TOGGLE_HELP = createApplication("help", "Help",
				"Display a popup with the keyboard shortcuts", 'H',
				KeyCodes.KEY_ESCAPE, null, ApplicationEvent.ActionType.HELP);
		START_DEMO = createApplication("demo", "Demo", "Play a demo", '7', null, null,
				ApplicationEvent.ActionType.DEMO);
		TREE_FOCUS = createApplication("tree", "Focus tree",
				"Puts keyboard focus on the category tree", KeyCodes.KEY_ENTER,
				null, null, ApplicationEvent.ActionType.TREE_FOCUS);
		TOGGLE_FULLSCREEN = createApplication("fullscreen", "Show/Hide tree",
				"Toggles fullscreen (hides/shows the tree view)", 'F', null,
				null, ApplicationEvent.ActionType.TOGGLE_TREE_VISIBLE);
		all = Arrays.asList(TOGGLE_HELP, TOGGLE_FULLSCREEN, START_DEMO,
				TREE_FOCUS);
	}


	public UserAction createApplication(String id, String caption, String description,
			int key, Integer altKey, ImageResource icon, ApplicationEvent.ActionType actionType) {
		return create(id, caption, key, altKey, description, icon, application.get(actionType));
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

}
