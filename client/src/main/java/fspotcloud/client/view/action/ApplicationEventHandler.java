package fspotcloud.client.view.action;

import java.util.logging.Logger;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

import fspotcloud.client.demo.DemoAction;
import fspotcloud.client.main.HideControlsAction;
import fspotcloud.client.main.TreeFocusAction;
import fspotcloud.client.main.UserInformation;
import fspotcloud.client.main.api.Initializable;
import fspotcloud.client.main.event.UserEvent;
import fspotcloud.client.main.event.application.ApplicationEvent;
import fspotcloud.client.main.event.application.ApplicationType;
import fspotcloud.client.main.help.AboutAction;
import fspotcloud.client.main.help.HelpAction;
import fspotcloud.client.place.api.Navigator;
import fspotcloud.client.place.api.Navigator.Zoom;
import fspotcloud.client.view.action.api.LoadNewLocationActionFactory;
import fspotcloud.shared.main.actions.UserInfo;
import java.util.logging.Level;

public class ApplicationEventHandler implements ApplicationEvent.Handler,
        Initializable {

    private static final Logger log = Logger.getLogger(ApplicationEventHandler.class.getName());
    final private DemoAction demoAction;
    final private AboutAction aboutAction;
    final private HelpAction helpAction;
    final private TreeFocusAction treeFocusAction;
    final private LoadNewLocationActionFactory locationFactory;
    private Runnable dashboardAction;
    final private EventBus eventBus;
    final private Navigator navigator;
    final private HideControlsAction hideControlsAction;
    private final UserInformation userInformation;

    @Inject
    public ApplicationEventHandler(AboutAction aboutAction,
            DemoAction demoAction, HelpAction helpAction,
            TreeFocusAction treeFocusAction,
            HideControlsAction hideControlsAction,
            LoadNewLocationActionFactory locationFactory,
            Navigator navigator, EventBus eventBus, UserInformation userInformation) {
        super();
        this.navigator = navigator;
        this.hideControlsAction = hideControlsAction;
        this.locationFactory = locationFactory;
        this.demoAction = demoAction;
        this.helpAction = helpAction;
        this.treeFocusAction = treeFocusAction;
        this.aboutAction = aboutAction;
        this.eventBus = eventBus;
        this.userInformation = userInformation;
    }

    @Override
    public void onEvent(UserEvent<?> e) {
        log.info("On application event of type " + e.getActionDef());
        switch ((ApplicationType) e.getActionDef()) {
            case START_DEMO:
                demoAction.run();
                break;
            case HIDE_CONTROLS:
                hideControlsAction.run();
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
            case LOGIN:
                userInformation.getUserInfoAsync(new AsyncCallback<UserInfo>() {

                    @Override
                    public void onFailure(Throwable caught) {
                        log.log(Level.SEVERE, "No user info ", caught);
                    }

                    @Override
                    public void onSuccess(UserInfo result) {
                        final String createLoginUrl = result.createLoginUrl();
                        log.info("login url:" + createLoginUrl);
                        Window.Location.replace(createLoginUrl);
                    }
                });
                break;
            case LOGOUT:
                userInformation.getUserInfoAsync(new AsyncCallback<UserInfo>() {

                    @Override
                    public void onFailure(Throwable caught) {
                        log.log(Level.SEVERE, "No user info ", caught);
                    }

                    @Override
                    public void onSuccess(UserInfo result) {
                        Window.Location.replace(result.createLogoutUrl());
                    }
                });
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
        dashboardAction = locationFactory.get("Dashboard.html");

    }
}
