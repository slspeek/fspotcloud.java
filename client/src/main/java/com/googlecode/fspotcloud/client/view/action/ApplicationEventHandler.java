/*
 * Copyright 2010-2012 Steven L. Speek.
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 *
 */
package com.googlecode.fspotcloud.client.view.action;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

import com.google.inject.Inject;

import com.googlecode.fspotcloud.client.demo.DemoAction;
import com.googlecode.fspotcloud.client.main.HideControlsAction;
import com.googlecode.fspotcloud.client.main.TreeFocusAction;
import com.googlecode.fspotcloud.client.main.UserInformation;
import com.googlecode.fspotcloud.client.main.api.Initializable;
import com.googlecode.fspotcloud.client.main.event.UserEvent;
import com.googlecode.fspotcloud.client.main.event.application.ApplicationEvent;
import com.googlecode.fspotcloud.client.main.event.application.ApplicationType;
import com.googlecode.fspotcloud.client.main.help.AboutAction;
import com.googlecode.fspotcloud.client.main.help.HelpAction;
import com.googlecode.fspotcloud.client.place.api.Navigator;
import com.googlecode.fspotcloud.client.place.api.Navigator.Zoom;
import com.googlecode.fspotcloud.client.view.action.api.LoadNewLocationActionFactory;
import com.googlecode.fspotcloud.shared.main.actions.GetUserInfo;
import com.googlecode.fspotcloud.shared.main.actions.UserInfo;

import java.util.logging.Level;
import java.util.logging.Logger;


public class ApplicationEventHandler implements ApplicationEvent.Handler,
    Initializable {
    private static final Logger log = Logger.getLogger(
            ApplicationEventHandler.class.getName());
    private final DemoAction demoAction;
    private final AboutAction aboutAction;
    private final HelpAction helpAction;
    private final TreeFocusAction treeFocusAction;
    private final LoadNewLocationActionFactory locationFactory;
    private Runnable dashboardAction;
    private final EventBus eventBus;
    private final Navigator navigator;
    private final HideControlsAction hideControlsAction;
    private final UserInformation userInformation;

    @Inject
    public ApplicationEventHandler(
        AboutAction aboutAction, DemoAction demoAction, HelpAction helpAction,
        TreeFocusAction treeFocusAction, HideControlsAction hideControlsAction,
        LoadNewLocationActionFactory locationFactory, Navigator navigator,
        EventBus eventBus, UserInformation userInformation) {
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

        switch ((ApplicationType)e.getActionDef()) {
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
                userInformation.getUserInfoAsync(
                    new GetUserInfo("FSpotCloud.html"),
                    new AsyncCallback<UserInfo>() {
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
                userInformation.getUserInfoAsync(
                    new GetUserInfo("FSpotCloud.html"),
                    new AsyncCallback<UserInfo>() {
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
