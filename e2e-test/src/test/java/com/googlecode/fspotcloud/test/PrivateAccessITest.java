package com.googlecode.fspotcloud.test;


import com.google.guiceberry.junit4.GuiceBerryRule;
import com.thoughtworks.selenium.Selenium;
import org.junit.Rule;
import org.junit.Test;

import javax.inject.Inject;

public class PrivateAccessITest {    public static final String JEFF_GOOGLE_COM = "jeff@google.com";
    @Rule
    public GuiceBerryRule guiceBerry = new GuiceBerryRule(EmptyGuiceBerryEnv.class);
    @Inject
    Selenium selenium;
    @Inject
    DashboardPage dashboardPage;
    @Inject
    TagApprovalPage tagApprovalPage;
    @Inject
    MyUserGroupsPage myUserGroupsPage;
    @Inject
    EditUserGroupPage editUserGroupPage;
    @Inject private ManageUsersPage manageUsersPage;
    @Inject private PhotoPage photoPage;
    @Inject private LoginPage loginPage;
    @Inject private SignUpPage signUpPage;
    @Inject private UserAccountPage userAccountPage;

    @Test
    public void testAccess() throws Exception {
        dashboardPage.loginAndOpen();
        dashboardPage.manageUsergroups();
        myUserGroupsPage.open();
        myUserGroupsPage.newUserGroup();
        myUserGroupsPage.newUserGroup();
        myUserGroupsPage.newUserGroup();
        myUserGroupsPage.selectFirstRow();
        myUserGroupsPage.editUserGroup();

        editUserGroupPage.fill("GNU Friends",
                "My friends from all over the world");
        editUserGroupPage.save();
        myUserGroupsPage.open();
        myUserGroupsPage.selectFirstRow();
        myUserGroupsPage.manageUserGroup();
        manageUsersPage.newUser(JEFF_GOOGLE_COM);
        dashboardPage.open();
        dashboardPage.manageApprovalForTag("1");


        tagApprovalPage.selectTopGroupOnTheRight();
        tagApprovalPage.approveUserGroup();
        photoPage.open();
        photoPage.logout();
        userAccountPage.open();
        signUpPage.open();
        signUpPage.fillForm(JEFF_GOOGLE_COM, JEFF_GOOGLE_COM, "Jeff");
        signUpPage.signUp();
        signUpPage.verifySuccess();
        photoPage.open();
        loginPage.open();
        loginPage.fillForm(JEFF_GOOGLE_COM, JEFF_GOOGLE_COM);
        loginPage.login();
        loginPage.verifySuccess();
        userAccountPage.open();
        photoPage.open();
        photoPage.clickImage(0,0);
        photoPage.assertPagingLabelSays(1,9);
        photoPage.logout();
        dashboardPage.loginAndOpen();
        myUserGroupsPage.open();
        myUserGroupsPage.selectFirstRow();
        myUserGroupsPage.editUserGroup();
        editUserGroupPage.togglePublic();
        editUserGroupPage.save();
        photoPage.open();
        photoPage.logout();
        userAccountPage.open();
        photoPage.open();
        photoPage.clickImage(0,0);
        photoPage.assertPagingLabelSays(1,9);




    }
}
