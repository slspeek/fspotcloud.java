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
package com.googlecode.fspotcloud.test;

import com.google.guiceberry.junit4.GuiceBerryRule;
import com.googlecode.fspotcloud.server.model.api.User;
import com.googlecode.fspotcloud.server.model.api.UserDao;
import com.googlecode.fspotcloud.shared.main.SignUpAction;
import com.googlecode.fspotcloud.shared.main.SignUpResult;
import javax.inject.Inject;
import net.customware.gwt.dispatch.server.Dispatch;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Rule;
import org.junit.Test;


public class SimpleTest {
    public static final String SLS_FREEDOM_ORG = "sls@freedom.org";
    public static final String SLS_SLS_ORG = "sls@sls.org";
    @Rule
    public GuiceBerryRule guiceBerry = new GuiceBerryRule(IntegrationGuiceBerryEnv.class);
    @Inject
    UserDao userDao;
    @Inject
    Dispatch dispatch;

    @Test
    public void testInsertUser() throws Exception {
        User newUser = userDao.findOrNew(SLS_FREEDOM_ORG);
        userDao.save(newUser);

        assertNotNull(userDao.find(SLS_FREEDOM_ORG));
    }

    @Test
    public void testSignUp() throws Exception {
        SignUpResult result = dispatch.execute(new SignUpAction(SLS_SLS_ORG,
                    "ihpt", ""));

        User sls = userDao.find(SLS_SLS_ORG);
        assertNotNull(sls);
        assertTrue(sls.hasRegistered());
        Thread.sleep(100000);
    }
}
