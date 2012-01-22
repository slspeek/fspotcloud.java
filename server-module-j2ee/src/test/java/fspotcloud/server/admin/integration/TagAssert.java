/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fspotcloud.server.admin.integration;

import com.google.inject.Inject;
import fspotcloud.server.model.api.Tags;
import javax.jdo.JDOObjectNotFoundException;
import org.testng.Assert;

/**
 *
 * @author steven
 */
public class TagAssert {

    @Inject
    Tags tags;

    public void assertTagLoaded(String id) {
        try {
            tags.getById(id);
        } catch (JDOObjectNotFoundException e) {
            Assert.fail();  
        }
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
        try {
            tags.getById(id);
            Assert.fail();
        } catch (JDOObjectNotFoundException e) {
        }
    }
}
