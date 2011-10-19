package fspotcloud.client.view.action;

import java.util.logging.Logger;

import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;

import fspotcloud.client.demo.DemoAction;
import fspotcloud.client.main.TreeFocusAction;
import fspotcloud.client.main.api.Initializable;
import fspotcloud.client.main.event.UserEvent;
import fspotcloud.client.main.event.application.ApplicationEvent;
import fspotcloud.client.main.event.application.ApplicationType;
import fspotcloud.client.main.help.AboutAction;
import fspotcloud.client.main.help.HelpAction;
import fspotcloud.client.place.api.Navigator;
import fspotcloud.client.place.api.Navigator.Zoom;
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
	final private LoadNewLocationActionFactory locationFactory;
	private Runnable dashboardAction;
	final private EventBus eventBus;
	final private Navigator navigator;

	@Inject
	public ApplicationEventHandler(AboutAction aboutAction,
			DemoAction demoAction, HelpAction helpAction,
			TreeFocusAction treeFocusAction,
			LoadNewLocationActionFactory locationFactory,
			Navigator navigator, EventBus eventBus) {
		super();
		this.navigator = navigator;
		this.locationFactory = locationFactory;
		this.demoAction = demoAction;
		this.helpAction = helpAction;
		this.treeFocusAction = treeFocusAction;
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
		case DASHBOARD:
			dashboardAction.run();
			break;
		case ABOUT:
			aboutAction.run();
			break;
		case ZOOM_IN:
			navigator.zoom(Zoom.IN);
			break;
		case ZOOM_OUT:
			navigator.zoom(Zoom.OUT);
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
