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
package com.googlecode.fspotcloud.server.inject;

import com.google.inject.servlet.ServletModule;

import com.googlecode.fspotcloud.server.cron.CronServlet;
import com.googlecode.fspotcloud.server.main.ImageServlet;
import com.googlecode.fspotcloud.server.main.TagServiceImpl;

import net.customware.gwt.dispatch.server.guice.GuiceStandardDispatchServlet;

import java.util.logging.Logger;


public class ServerServletModule extends ServletModule {
    @Override
    protected void configureServlets() {
        serve("/fspotcloud/dispatch").with(GuiceStandardDispatchServlet.class);
        serve("/com.googlecode.fspotcloud.dashboard/dispatch")
            .with(GuiceStandardDispatchServlet.class);
        serve("/fspotcloud/tag").with(TagServiceImpl.class);
        serve("/com.googlecode.fspotcloud.dashboard/tag")
            .with(TagServiceImpl.class);

        serve("/image").with(ImageServlet.class);
        serve("/cron").with(CronServlet.class);
    }
}
