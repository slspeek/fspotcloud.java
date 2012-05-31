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
package com.googlecode.fspotcloud.model.api.test.user;

import com.google.guiceberry.junit4.GuiceBerryRule;
import com.google.inject.Inject;
import com.googlecode.fspotcloud.model.api.test.EmptyGuiceBerryEnv;
import com.googlecode.fspotcloud.server.model.api.Tag;
import com.googlecode.fspotcloud.server.model.api.TagDao;
import com.googlecode.fspotcloud.server.model.api.User;
import com.googlecode.fspotcloud.server.model.api.UserDao;
import com.googlecode.fspotcloud.shared.main.PhotoInfo;
import com.googlecode.fspotcloud.shared.main.TagNode;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;

import java.util.Date;
import java.util.List;
import java.util.TreeSet;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class UserManagerTest {
    private static final Logger log = Logger.getLogger(UserManagerTest.class.getName());
    public static final String EMAIL = "douglas@yahoo.com";
    public static final String JSLINT = "jslint";
    @Rule
    public GuiceBerryRule guiceBerry = new GuiceBerryRule(EmptyGuiceBerryEnv.class);
    @Inject
    private UserDao userManager;

    @After
    public void cleanUp() throws Exception {
        userManager.deleteBulk(100);
    }

    @Test
    public void testLogin() throws Exception {
       // userManager.newPeristentEntity();
         User user = userManager.newEntity();
         user.setEmail(EMAIL);
        user.setCredentials(JSLINT);
        userManager.save(user);
        
        user = null;
        user = userManager.tryToLogin(EMAIL, JSLINT);
        assertNotNull(user);
                
                 
    }
}
