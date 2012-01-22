/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fspotcloud.server.admin.integration;

import com.google.inject.Inject;
import fspotcloud.server.model.api.Photos;
import javax.jdo.JDOObjectNotFoundException;
import org.testng.Assert;

/**
 *
 * @author steven
 */
public class PhotoAssert {

    @Inject
    Photos photos;

    public void verifyPhotoRemoved(String id) {
        try {
            photos.getById(id);
            Assert.fail("Photo with id: " + id + " was present");
        } catch (JDOObjectNotFoundException e) {
        }
    }

    public void assertPhotoLoaded(String id) {
        try {
            photos.getById(id);
        } catch (JDOObjectNotFoundException e) {
            Assert.fail("Photo with id: " + id + " was absent");
        }
    }

    public void assertPhotosRemoved(String... allIds) {
        for (String id : allIds) {
            verifyPhotoRemoved(id);
        }

    }

    public void assertPhotosLoaded(String... allIds) {
        for (String id : allIds) {
            assertPhotoLoaded(id);
        }
    }
}
