package fspotcloud.client.view.action;

import com.google.inject.Inject;

import fspotcloud.client.view.action.api.NavigationActionFactory;
import fspotcloud.client.view.action.api.SlideshowActionFactory;
import fspotcloud.client.view.action.api.UserAction;

public class ShortcutFactory {

	final private NavigationActionFactory navigationActionFactory;
	final private SlideshowActionFactory slideshowActionFactory;

	@Inject
	public ShortcutFactory(NavigationActionFactory navigationActionFactory,
			SlideshowActionFactory slideshowActionFactory) {
		super();
		this.navigationActionFactory = navigationActionFactory;
		this.slideshowActionFactory = slideshowActionFactory;
	}

	UserAction getSlideshow(String caption, int keyCode, Integer keyCode2,
			String description, int actionType) {
		SlideshowAction action = slideshowActionFactory.get(actionType);
		Shortcut shortcut;
		if (keyCode2 != null) {
			//shortcut// = new Shortcut(caption, description, new KeyStroke(keyCode), new KeyStroke(keyCode2), null, action);
		} else {
			//shortcut// = new Shortcut(caption, description, new KeyStroke(keyCode), null, null, action);
		}
		return null;//shortcut;
	}
	
	UserAction getNavigation(String caption, int keyCode, Integer keyCode2,
			String description, int actionType) {
		NavigationAction action = navigationActionFactory.get(actionType);
		Shortcut shortcut;
		if (keyCode2 != null) {
			//shortcut// = new Shortcut(caption, description, new KeyStroke(keyCode), new KeyStroke(keyCode2), null, action);
		} else {
			//shortcut// = new Shortcut(caption, description, new KeyStroke(keyCode), null, null, action);
		}
		return null;//shortcut;
	}

}
