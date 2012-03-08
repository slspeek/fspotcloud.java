/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.fspotcloud.server.inject;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import com.googlecode.botdispatch.controller.inject.ControllerServletModule;
import com.googlecode.fspotcloud.server.control.task.TaskActionsModule;
import com.googlecode.fspotcloud.server.control.task.TaskModule;

/**
 *
 * @author steven
 */
public class ServerTotalModule extends AbstractModule {

    private final int maxTicks;

    public ServerTotalModule(int maxTicks) {
        this.maxTicks = maxTicks;
    }

    @Override
    protected void configure() {
        install(new AdminActionsModule());
        bind(Integer.class).annotatedWith(Names.named("maxTicks")).toInstance(
                new Integer(maxTicks));
        install(new ServerServletModule());
        install(new ControllerServletModule());
        install(new ServerControllerModule());
        install(new TaskActionsModule());
        install(new TaskModule());
        install(new UserActionModule());
    }
}
