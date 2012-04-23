/*
 * Copyright 2010-2012 Steven L. Speek.
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 *
 */
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

import com.googlecode.taskqueuedispatch.inject.TaskQueueDispatchDirectModule;


/**
 * DOCUMENT ME!
 *
 * @author steven
*/
public class J2eeNoAuthTotalModule extends AbstractModule {
    public static final Integer MAX_COMMAND_DELETE = new Integer(300);
    private final int maxTicks;
    private String botSecret;

    public J2eeNoAuthTotalModule(int maxTicks, String botSecret) {
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
