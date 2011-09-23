package fspotcloud.client.view.action;

import java.util.logging.Logger;

import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;

import fspotcloud.client.main.api.Initializable;
import fspotcloud.client.main.event.UserEvent;
import fspotcloud.client.main.event.UserEventHandler;
import fspotcloud.client.main.event.about.AboutEvent;
import fspotcloud.client.main.event.about.AboutType;
import fspotcloud.client.view.action.api.LoadNewLocationActionFactory;

public class AboutEventHandler implements AboutEvent.Handler,
		Initializable {
	@SuppressWarnings("unused")
	private static final Logger log = Logger
			.getLogger(AboutEventHandler.class.getName());
	final private LoadNewLocationActionFactory locationFactory;
	private Runnable projectHostingAction, fspotAction, mavenAction, 
			protonAction, licenseAction, stevenAction;
	final private EventBus eventBus;

	@Inject
	public AboutEventHandler(LoadNewLocationActionFactory locationFactory, EventBus eventBus) {
		super();
		this.locationFactory = locationFactory;
		this.eventBus = eventBus;
	}

	@Override
	public void onEvent(UserEvent<? extends UserEventHandler> e) {
		log.info("On application event of type " + e.getActionDef());
		switch ((AboutType) e.getActionDef()) {
		case PROJECT_HOSTING:
			projectHostingAction.run();
			break;
		case F_SPOT:
			fspotAction.run();
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
		eventBus.addHandler(AboutEvent.TYPE, this);
	}

	private void initLocationActions() {
		projectHostingAction = locationFactory
				.get("http://code.google.com/p/fspotcloud");
		fspotAction = locationFactory.get("http://f-spot.org/");
		mavenAction = locationFactory
				.get("http://slspeek.github.com/FSpotCloudSite/");
		licenseAction = locationFactory
				.get("http://slspeek.github.com/FSpotCloudSite/license.html");
		protonAction = locationFactory.get("http://protonradio.com");
		stevenAction = locationFactory
				.get("http://profiles.google.com/slspeek");
	}

}
