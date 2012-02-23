/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fspotcloud.server.inject;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import fspotcloud.botdispatch.model.api.Commands;
import fspotcloud.botdispatch.model.command.CommandManager;
import fspotcloud.model.jpa.ModelModule;
import fspotcloud.taskqueuedispatch.inject.TaskQueueDispatchDirectModule;
import fspotcloud.user.LenientUserService;
import fspotcloud.user.UserService;

/**
 *
 * @author steven
 */
public class J2eeTotalModule extends AbstractModule {

    @Override
    protected void configure() {
        (new PropertiesLoader()).loadProperties();
        install(new ServerTotalModule());
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