package fspotcloud.client.view.action;

import java.util.logging.Logger;

import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;

import fspotcloud.client.demo.DemoAction;
import fspotcloud.client.main.ToggleButtonsAction;
import fspotcloud.client.main.ToggleFullscreenAction;
import fspotcloud.client.main.TreeFocusAction;
import fspotcloud.client.main.api.Initializable;
import fspotcloud.client.main.event.UserEvent;
import fspotcloud.client.main.event.application.ApplicationEvent;
import fspotcloud.client.main.event.application.ApplicationType;
import fspotcloud.client.main.help.AboutAction;
import fspotcloud.client.main.help.HelpAction;
import fspotcloud.client.view.action.api.LoadNewLocationActionFactory;

public class ApplicationEventHandler implements ApplicationEvent.Handler,
		Initializable {
	@SuppressWarnings("unused")
	private static final Logger log = Logger
			.getLogger(ApplicationEventHandler.class.getName());
	final private DemoAction demoAction;
	final private AboutAction aboutAction;
	final private HelpAction helpAction;
	final private TreeFocusAction treeFocusAction;
	final private ToggleFullscreenAction toggleFullscreenAction;
	final private ToggleButtonsAction toggleButtonsAction;
	final private LoadNewLocationActionFactory locationFactory;
	private Runnable dashboardAction;
	final private EventBus eventBus;

	@Inject
	public ApplicationEventHandler(AboutAction aboutAction,
			DemoAction demoAction, HelpAction helpAction,
			TreeFocusAction treeFocusAction,
			ToggleFullscreenAction toggleFullscreenAction,
			ToggleButtonsAction toggleButtonsAction,
			LoadNewLocationActionFactory locationFactory, EventBus eventBus) {
		super();
		this.locationFactory = locationFactory;
		this.toggleButtonsAction = toggleButtonsAction;
		this.demoAction = demoAction;
		this.helpAction = helpAction;
		this.treeFocusAction = treeFocusAction;
		this.toggleFullscreenAction = toggleFullscreenAction;
		this.aboutAction = aboutAction;
		this.eventBus = eventBus;
	}

	@Override
	public void onEvent(UserEvent e) {
		log.info("On application event of type " + e.getActionDef());
		switch ((ApplicationType) e.getActionDef()) {
		case START_DEMO:
			demoAction.run();
			break;
		case TOGGLE_HELP:
			helpAction.run();
			break;
		case TREE_FOCUS:
			treeFocusAction.run();
			break;
		case TOGGLE_FULLSCREEN:
			toggleFullscreenAction.run();
			break;
		case TOGGLE_BUTTONS_VISIBLE:
			toggleButtonsAction.run();
			break;
		case DASHBOARD:
			dashboardAction.run();
			break;
		case ABOUT:
			aboutAction.run();
			break;
		default:
			break;
		}
	}

	public void init() {
		initLocationActions();
		eventBus.addHandler(ApplicationEvent.TYPE, this);
	}

	private void initLocationActions() {
		dashboardAction = locationFactory.get("/Dashboard.html");
	}

}
