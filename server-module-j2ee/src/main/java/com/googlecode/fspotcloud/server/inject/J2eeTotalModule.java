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

    public static final Integer MAX_COMMAND_DELETE = new Integer(300);
    private final int maxTicks;
    private String botSecret;

    public J2eeTotalModule(int maxTicks, String botSecret) {
        this.maxTicks = maxTicks;
        this.botSecret = botSecret;
    }

    @Override
    protected void configure() {
        install(new ServerTotalModule(maxTicks, botSecret));
        install(new ModelModule(maxTicks));
        install(new TaskQueueDispatchDirectModule());
        install(new LenientUserServiceModule());
        bind(Commands.class).to(CommandManager.class).in(Singleton.class);
        bind(Integer.class).annotatedWith(Names.named("maxCommandDelete"))
                .toInstance(MAX_COMMAND_DELETE);
    }
}

class LenientUserServiceModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(UserService.class).to(LenientUserService.class);
    }
}