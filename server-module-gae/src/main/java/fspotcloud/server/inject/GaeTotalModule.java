/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fspotcloud.server.inject;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import com.googlecode.botdispatch.model.api.Commands;
import com.googlecode.botdispatch.model.jpa.gae.command.CommandManager;
import fspotcloud.model.jpa.CachedModelModule;
import com.googlecode.taskqueuedispatch.inject.TaskQueueDispatchModule;
import com.googlecode.taskqueuedispatch.inject.TaskQueueDispatchServletModule;
import fspotcloud.user.gae.UserModuleGae;

/**
 *
 * @author steven
 */
public class GaeTotalModule extends AbstractModule {

    @Override
    protected void configure() {
        (new PropertiesLoader()).loadProperties();
        install(new ServerTotalModule(100));
        install(new CachedModelModule());
        install(new TaskQueueDispatchModule());
        install(new TaskQueueDispatchServletModule());
        install(new UserModuleGae());
                bind(Commands.class).to(CommandManager.class).in(Singleton.class);
        bind(Integer.class).annotatedWith(Names.named("maxCommandDelete")).toInstance(new Integer(300));
    }
}
