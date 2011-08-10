package fspotcloud.client.view.action;

import java.util.logging.Logger;

import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;

import fspotcloud.client.demo.DemoAction;
import fspotcloud.client.main.ToggleButtonsAction;
import fspotcloud.client.main.ToggleFullscreenAction;
import fspotcloud.client.main.TreeFocusAction;
import fspotcloud.client.main.api.Initializable;
import fspotcloud.client.main.help.HelpAction;
import fspotcloud.client.main.shared.ApplicationEvent;
import fspotcloud.client.view.action.api.LoadNewLocationActionFactory;

public class ApplicationEventHandler implements ApplicationEvent.Handler,
		Initializable {
	@SuppressWarnings("unused")
	private static final Logger log = Logger
			.getLogger(ApplicationEventHandler.class.getName());
	final private DemoAction demoAction;
	final private HelpAction helpAction;
	final private TreeFocusAction treeFocusAction;
	final private ToggleFullscreenAction toggleFullscreenAction;
	final private ToggleButtonsAction toggleButtonsAction;
	final private LoadNewLocationActionFactory locationFactory;
	private Runnable projectHostingAction, mavenAction, dashboardAction,
			protonAction, licenseAction, stevenAction;
	final private EventBus eventBus;

	@Inject
	public ApplicationEventHandler(DemoAction demoAction,
			HelpAction helpAction, TreeFocusAction treeFocusAction,
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
		this.eventBus = eventBus;
	}

	@Override
	public void onEvent(ApplicationEvent e) {
		log.info("On application event of type " + e.getActionType());
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
		case TOGGLE_BUTTONS_VISIBLE:
			toggleButtonsAction.run();
			break;
		case DASHBOARD:
			dashboardAction.run();
			break;
		case PROJECT_HOSTING:
			projectHostingAction.run();
			break;
		case MAVEN:
			mavenAction.run();
			break;
		case PROTON:
			protonAction.run();
			break;
		case LICENSE:
			licenseAction.run();
			break;
		case STEVEN:
			stevenAction.run();
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
		projectHostingAction = locationFactory
				.get("http://code.google.com/p/fspotcloud");
		mavenAction = locationFactory
				.get("http://slspeek.github.com/FSpotCloudSite/");
		dashboardAction = locationFactory.get("/Dashboard.html");
		licenseAction = locationFactory
				.get("http://slspeek.github.com/FSpotCloudSite/license.html");
		protonAction = locationFactory.get("http://protonradio.com");
		stevenAction = locationFactory
				.get("http://profiles.google.com/slspeek");

	}
}
