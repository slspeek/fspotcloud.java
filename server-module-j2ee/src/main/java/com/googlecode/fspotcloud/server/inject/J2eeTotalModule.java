/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.fspotcloud.server.inject;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import com.googlecode.botdispatch.model.api.Commands;
import com.googlecode.botdispatch.model.command.CommandManager;
import com.googlecode.fspotcloud.model.jpa.ModelModule;
import com.googlecode.fspotcloud.user.LenientUserService;
import com.googlecode.fspotcloud.user.UserService;
import com.googlecode.taskqueuedispatch.inject.TaskQueueDispatchDirectModule;

/**
 *
 * @author steven
 */
public class J2eeTotalModule extends AbstractModule {

    @Override
    protected void configure() {
        (new PropertiesLoader()).loadProperties();
        install(new ServerTotalModule(100));
        install(new ModelModule());
        install(new TaskQueueDispatchDirectModule());
        install(new LenientUserServiceModule());
        bind(Commands.class).to(CommandManager.class).in(Singleton.class);
        bind(Integer.class).annotatedWith(Names.named("maxCommandDelete")).toInstance(new Integer(300));
    }
}

class LenientUserServiceModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(UserService.class).to(LenientUserService.class);
    }
}