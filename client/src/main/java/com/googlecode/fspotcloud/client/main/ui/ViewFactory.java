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
package com.googlecode.fspotcloud.client.main.ui;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.HasOneWidget;

import java.util.logging.Logger;


public class ViewFactory {
    private static final Logger log = Logger.getLogger(
            ViewFactory.class.getName());
    private final EventBus eventBus;

    public ViewFactory(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public void register(ActivityMapper mapper, HasOneWidget display) {
        ActivityManager manager = new ActivityManager(mapper, eventBus);
        manager.setDisplay(display);
        log.info("registered: " + mapper);
    }
}
