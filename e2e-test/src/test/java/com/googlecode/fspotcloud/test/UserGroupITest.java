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
import javax.inject.Inject;
import org.junit.Rule;
import org.junit.Test;


public class UserGroupITest {
    public static final String RMS_FSF_ORG = "gnu@fsf.org";
    public static final String CREDENTIALS = "ihp";
    @Rule
    public GuiceBerryRule guiceBerry = new GuiceBerryRule(EmptyGuiceBerryEnv.class);
    @Inject
    SignUpPage signUpPage;
    @Inject
    LoginPage loginPage;
    @Inject
    MyUserGroupsPage myUserGroupsPage;
    @Inject
    EditUserGroupPage editUserGroupPage;

    @Test
    public void createAndEditUserGroup() throws Exception {
        signUpPage.open();
        signUpPage.fillForm(RMS_FSF_ORG, CREDENTIALS, "rms");
        signUpPage.signUp();
        signUpPage.verifySuccess();
        loginPage.open();
        loginPage.fillForm(RMS_FSF_ORG, CREDENTIALS);
        loginPage.login();
        loginPage.verifySuccess();

        myUserGroupsPage.open();
        myUserGroupsPage.newUserGroup();
        editUserGroupPage.open(1L);
        editUserGroupPage.fill("GNU Friends",
            "My friends from all over the world");
        editUserGroupPage.save();
        myUserGroupsPage.open();
        myUserGroupsPage.verifyText("My friends from all over the world");
        myUserGroupsPage.selectFirstRow();
        myUserGroupsPage.deleteUserGroup();
        myUserGroupsPage.verifyTextNotPresent(
            "My friends from all over the world");
    }
}
