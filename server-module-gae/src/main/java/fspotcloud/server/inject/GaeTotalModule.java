/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fspotcloud.server.inject;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import fspotcloud.botdispatch.model.api.Commands;
import fspotcloud.botdispatch.model.jpa.gae.command.CommandManager;
import fspotcloud.model.jpa.CachedModelModule;
import fspotcloud.taskqueuedispatch.inject.TaskQueueDispatchModule;
import fspotcloud.taskqueuedispatch.inject.TaskQueueDispatchServletModule;
import fspotcloud.user.gae.UserModuleGae;

/**
 *
 * @author steven
 */
public class GaeTotalModule extends AbstractModule {

    @Override
    protected void configure() {
        (new PropertiesLoader()).loadProperties();
        install(new ServerTotalModule());
        install(new CachedModelModule());
        install(new TaskQueueDispatchModule());
        install(new TaskQueueDispatchServletModule());
        install(new UserModuleGae());
                bind(Commands.class).to(CommandManager.class).in(Singleton.class);
        bind(Integer.class).annotatedWith(Names.named("maxCommandDelete")).toInstance(new Integer(300));
    }
}
