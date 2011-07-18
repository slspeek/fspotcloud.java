package fspotcloud.client.main.view;

import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;

import fspotcloud.client.demo.DemoAction;
import fspotcloud.client.main.ToggleFullscreenAction;
import fspotcloud.client.main.TreeFocusAction;
import fspotcloud.client.main.help.HelpAction;
import fspotcloud.client.main.shared.ApplicationEvent;

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
		case DEMO:
			demoAction.run();
			break;
		case HELP:
			helpAction.run();
			break;
		case TREE_FOCUS:
			treeFocusAction.run();
			break;
		case TOGGLE_TREE_VISIBLE:
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
