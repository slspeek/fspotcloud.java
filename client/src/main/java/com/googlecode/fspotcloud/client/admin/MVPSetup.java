package com.googlecode.fspotcloud.client.admin;

import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

import com.googlecode.fspotcloud.client.admin.view.DashboardPresenter;
import com.googlecode.fspotcloud.client.main.UserInformation;
import com.googlecode.fspotcloud.client.place.AdminPlaceHistoryMapper;
import com.googlecode.fspotcloud.client.place.TagPlace;

public class MVPSetup {

    private static final Logger log = Logger.getLogger(MVPSetup.class.getName());
    final private Place defaultPlace = new TagPlace("1");
    final private EventBus eventBus;
    final private PlaceController placeController;
    final private DashboardPresenter dashboard;
    private final UserInformation userInformation;

    @Inject
    public MVPSetup(EventBus eventBus, PlaceController placeController,
            DashboardPresenter dashboard, UserInformation userInformation) {
        super();
        this.eventBus = eventBus;
        this.placeController = placeController;
        this.dashboard = dashboard;
        this.userInformation = userInformation;
    }

    public void setup() {
        log.info("Admin setup");
        AdminPlaceHistoryMapper historyMapper = GWT.create(AdminPlaceHistoryMapper.class);
        PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(
                historyMapper);
        historyHandler.register(placeController, eventBus, defaultPlace);

        log.info("Just before handleCurrentHistory()");
        historyHandler.handleCurrentHistory();

        dashboard.init();
        Widget w = dashboard.getView().asWidget();
        RootLayoutPanel.get().add(w);
        log.info("Setup finished");
//        userInformation.getUserInfoAsync(new GetUserInfo("Dashboard.html"), new AsyncCallback<UserInfo>() {
//
//            @Override
//            public void onFailure(Throwable caught) {
//            }
//
//            @Override
//            public void onSuccess(UserInfo result) {
//                if (!result.isAdmin()) {
//                    Window.Location.replace(result.createLoginUrl());
//                }
//            }
//        });
    }
}
