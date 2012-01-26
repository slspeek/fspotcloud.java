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
import fspotcloud.model.jpa.ModelServletModule;
import fspotcloud.taskqueuedispatch.inject.TaskQueueDispatchDirectModule;
import fspotcloud.user.openid.OpenIdUserModule;

/**
 *
 * @author steven
 */
public class J2eeTotalModule extends AbstractModule {

    @Override
    protected void configure() {
        (new PropertiesLoader()).loadProperties();
        install(new ServerTotalModule());
        install(new ModelServletModule());
        install(new ModelModule());
        install(new TaskQueueDispatchDirectModule());
        install(new OpenIdUserModule());
        bind(Commands.class).to(CommandManager.class).in(Singleton.class);
        bind(Integer.class).annotatedWith(Names.named("maxCommandDelete")).toInstance(new Integer(300));
       
    }
}
