package com.googlecode.fspotcloud.server.admin.integration;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import com.google.inject.Inject;
import com.googlecode.fspotcloud.server.model.api.Tags;
import org.testng.Assert;

/**
 *
 * @author steven
 */
public class TagAssert {

    @Inject
    Tags tags;

    public void assertTagLoaded(String id) {
        Assert.assertNotNull(tags.find(id));
    }

    public void assertTagsLoaded(String... allIds) {
        for (String id : allIds) {
            assertTagLoaded(id);
        }
    }

    public void assertTagsRemoved(String... allIds) {
        for (String id : allIds) {
            verifyTagRemoved(id);
        }

    }

    public void verifyTagRemoved(String id) {
        Assert.assertNull(tags.find(id));
    }
}
