/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fspotcloud.server.inject;

import com.google.inject.AbstractModule;
import fspotcloud.botdispatch.controller.inject.ControllerServletModule;
import fspotcloud.server.control.task.TaskActionsModule;
import fspotcloud.server.control.task.TaskModule;

/**
 *
 * @author steven
 */
public class ServerTotalSpecialModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new AdminActionsModule());
        install(new ServerModule());
        install(new ServerServletModule());
        install(new ControllerServletModule());
        install(new ServerControllerModule());
        install(new TaskActionsModule());
        install(new TaskModule());
        install(new UserActionModule());
    }
}
