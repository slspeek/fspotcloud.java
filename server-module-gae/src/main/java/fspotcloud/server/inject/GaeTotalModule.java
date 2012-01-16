/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fspotcloud.server.inject;

import com.google.inject.AbstractModule;
import fspotcloud.botdispatch.model.MinimalCommandModelModule;
import fspotcloud.server.model.ModelModule;
import fspotcloud.taskqueuedispatch.inject.TaskQueueDispatchModule;
import fspotcloud.taskqueuedispatch.inject.TaskQueueDispatchServletModule;

/**
 *
 * @author steven
 */
public class GaeTotalModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new ServerTotalModule());
        install(new ModelModule());
        install(new MinimalCommandModelModule());
        install(new TaskQueueDispatchModule());
        install(new TaskQueueDispatchServletModule());
    }
}
