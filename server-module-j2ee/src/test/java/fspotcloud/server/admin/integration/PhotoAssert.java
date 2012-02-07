/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fspotcloud.server.admin.integration;

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
        Assert.assertNull(photos.getById(id));
    }

    public void assertPhotoLoaded(String id) {
        Assert.assertNotNull(photos.getById(id));
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
