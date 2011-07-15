package fspotcloud.client.main.view;

import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;

import fspotcloud.client.main.shared.ApplicationEvent;
import fspotcloud.client.view.action.DemoAction;
import fspotcloud.client.view.action.HelpAction;
import fspotcloud.client.view.action.ToggleFullscreenAction;
import fspotcloud.client.view.action.TreeFocusAction;

public class ApplicationEventHandler implements ApplicationEvent.Handler {

	final private DemoAction demoAction;
	final private HelpAction helpAction;
	final private TreeFocusAction treeFocusAction;
	final private ToggleFullscreenAction toggleFullscreenAction;
	final private EventBus eventBus;
	
	
	@Inject
	public ApplicationEventHandler(DemoAction demoAction,
			HelpAction helpAction, TreeFocusAction treeFocusAction,
			ToggleFullscreenAction toggleFullscreenAction, 	EventBus eventBus) {
		super();
		this.demoAction = demoAction;
		this.helpAction = helpAction;
		this.treeFocusAction = treeFocusAction;
		this.toggleFullscreenAction = toggleFullscreenAction;
		this.eventBus = eventBus;
	}

	@Override
	public void onEvent(ApplicationEvent e) {
		switch (e.getActionType()) {
		case ApplicationEvent.ACTION_DEMO:
			demoAction.run();
			break;
		case ApplicationEvent.ACTION_HELP:
			helpAction.run();
			break;
		case ApplicationEvent.ACTION_TREE_FOCUS:
			treeFocusAction.run();
			break;
		case ApplicationEvent.ACTION_TOGGLE_TREE_VISIBLE:
			toggleFullscreenAction.run();
			break;
		default:
		break;
		}

	}
	public void init() {
		eventBus.addHandler(ApplicationEvent.TYPE, this);
	}

}
