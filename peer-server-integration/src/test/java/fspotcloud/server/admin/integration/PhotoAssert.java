package fspotcloud.server.admin.integration;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import com.google.inject.Inject;
import fspotcloud.server.model.api.Photos;
import org.testng.Assert;

/**
 *
 * @author steven
 */
public class PhotoAssert {

    @Inject
    Photos photos;

    public void verifyPhotoRemoved(String id) {
        Assert.assertNull(photos.find(id));
    }

    public void assertPhotoLoaded(String id) {
        Assert.assertNotNull(photos.find(id));
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
