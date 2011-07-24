package fspotcloud.client.view.action;

import java.util.Arrays;
import java.util.List;

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.resources.client.ImageResource;
import com.google.inject.Inject;

import fspotcloud.client.main.shared.NavigationEvent;
import fspotcloud.client.main.shared.NavigationEventProviderFactory;
import fspotcloud.client.view.action.api.NavigationActions;
import fspotcloud.client.view.action.api.ShortcutAssistedFactory;
import fspotcloud.client.view.action.api.UserAction;

public class NavigationActionsImpl extends ActionsFactory  implements NavigationActions{

	public UserAction HOME;
	public UserAction END;
	public UserAction BACK;
	public UserAction NEXT;
	
	private List<UserAction> all;
	
	
	final private NavigationEventProviderFactory navigation;
	
	@Inject
	public NavigationActionsImpl(ShortcutAssistedFactory shortcutFactory, NavigationEventProviderFactory navigation) {
		super(shortcutFactory);
		this.navigation = navigation;
		init();
	}

	private void init() {
		HOME = createNavigation("home", "Home", 'B', KeyCodes.KEY_HOME,
				"Go to the first image of the category", null,
				NavigationEvent.ActionType.HOME);
		END = createNavigation("end", "End", KeyCodes.KEY_END, null,
				"Go to the last image of the category", null,
				NavigationEvent.ActionType.END);
		BACK = createNavigation("back", "Back",  KeyCodes.KEY_LEFT, null,
				"Previous image in this category", null, NavigationEvent.ActionType.BACK);
		NEXT = createNavigation("next", "Next", KeyCodes.KEY_RIGHT, null,
				"Next image in this category", null, NavigationEvent.ActionType.NEXT);
		all = Arrays.asList(HOME, END, BACK, NEXT);
	}

	public UserAction createNavigation(String id, String caption, int key, Integer altKey,
			String description, ImageResource icon, NavigationEvent.ActionType actionType) {
		return create(id, caption, key, altKey, description, icon, navigation.get(actionType));
	}



	@Override
	public List<UserAction> allActions() {
		return all;
	}

	@Override
	public UserAction back() {
		return BACK;
	}

	@Override
	public UserAction next() {
		return NEXT;
	}

	@Override
	public UserAction home() {
		return HOME;
	}

	@Override
	public UserAction end() {
		return END;
	}

	
}
