/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.fspotcloud.server.inject;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import com.googlecode.botdispatch.model.api.Commands;
import com.googlecode.botdispatch.model.jpa.gae.command.CommandManager;
import com.googlecode.fspotcloud.model.jpa.CachedModelModule;
import com.googlecode.fspotcloud.user.gae.UserModuleGae;
import com.googlecode.taskqueuedispatch.inject.TaskQueueDispatchModule;
import com.googlecode.taskqueuedispatch.inject.TaskQueueDispatchServletModule;

/**
 *
 * @author steven
 */
public class GaeTotalModule extends AbstractModule {

    public static final Integer MAX_COMMAND_DELETE = new Integer(300);
    private String botSecret;
    private final int maxTicks;

    public GaeTotalModule(int maxTicks, String botSecret) {
        this.maxTicks = maxTicks;
        this.botSecret = botSecret;
    }

    @Override
    protected void configure() {
        install(new ServerTotalModule(maxTicks, botSecret));
        install(new CachedModelModule(maxTicks));
        install(new TaskQueueDispatchModule());
        install(new TaskQueueDispatchServletModule());
        install(new UserModuleGae());
        bind(Commands.class).to(CommandManager.class).in(Singleton.class);
        bind(Integer.class).annotatedWith(Names.named("maxCommandDelete"))
                .toInstance(MAX_COMMAND_DELETE);
    }
}
