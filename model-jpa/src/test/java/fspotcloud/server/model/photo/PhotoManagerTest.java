package fspotcloud.server.model.photo;

import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;
import com.google.guiceberry.junit4.GuiceBerryRule;
import com.google.inject.Inject;
import fspotcloud.model.jpa.photo.PhotoEntity;
import java.util.Calendar;
import java.util.Date;


import junit.framework.TestSuite;

import fspotcloud.server.model.GaeModelGuiceberryEnv;

import fspotcloud.server.model.api.Photo;
import fspotcloud.server.model.api.Photos;
import java.util.*;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.Test;

public class PhotoManagerTest {
    public static final String TEST_ID = "1";

    @Rule
    public GuiceBerryRule guiceBerry = new GuiceBerryRule(GaeModelGuiceberryEnv.class);
    @Inject
    private Photos photoManager;
    
    @After
    public void cleanUp() {
    }

    public Date getDate(int year, int month, int date) {
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DATE, date);
        return cal.getTime();
    }

    
    @Test
    public void simple() {
        Photo photo = photoManager.getOrNew(TEST_ID);
        if (photoManager.getById(TEST_ID) != null) {
            fail();
        }
        photoManager.save(photo);
        photoManager.delete(TEST_ID);
        
    }
    
    @Test
    public void simpleDescr() {
        Photo photo = photoManager.getOrNew(TEST_ID);
        photo.setDescription("Test desc");
        if (photoManager.getById(TEST_ID) != null) {
            fail();
        }
        photoManager.save(photo);
        photo = null;
       photo = photoManager.getOrNew(TEST_ID);
       assertEquals("Test desc", photo.getDescription());
        photoManager.delete(TEST_ID);
        
    }
    @Test
    public void testGetOrNew() {
        Photo photo = photoManager.getOrNew(TEST_ID);
        assertEquals(Boolean.FALSE, photo.isFullsizeLoaded());
        assertEquals(Boolean.FALSE, photo.isImageLoaded());
        assertEquals(Boolean.FALSE, photo.isThumbLoaded());
        assertNotNull(photo.getTagList());
        if (photoManager.getById(TEST_ID) != null) {
            fail();
        }
        
    }

    @Test
    public void testCreateLoadModify() {
        Photo photo, retrieved;
        photo = new PhotoEntity();
        photo.setId(TEST_ID);
        photoManager.save(photo);
        retrieved = photoManager.getOrNew(TEST_ID);
        retrieved.setDescription("Nice");
        photoManager.save(retrieved);
        retrieved = photoManager.getOrNew(TEST_ID);
        assertEquals("Nice", retrieved.getDescription());
        photoManager.delete(TEST_ID);
    }

    @Test
    public void testSave() {
        PhotoEntity photo = new PhotoEntity();
        photo.setId(TEST_ID);
        photo.setDescription("Nice");
        photoManager.save(photo);
        Photo retrieved = photoManager.getOrNew(TEST_ID);
        assertEquals("Nice", retrieved.getDescription());
        photoManager.delete(TEST_ID);
    }

    @Test
    public void testDelete() {
        PhotoEntity photo = new PhotoEntity();
        photo.setId(TEST_ID);
        photo.setDescription("Nice");
        photoManager.save(photo);
        photoManager.delete(TEST_ID);
    }
    
    @Test public void tagListPersists() {
        List abc = ImmutableList.of("a", "b", "c");
        Photo photo = new PhotoEntity();
        photo.setId(TEST_ID);
        photo.setTagList(abc);
        photoManager.save(photo);
        photo = null;
        
        photo = photoManager.getById(TEST_ID);
        assertEquals(abc, photo.getTagList());
    }
    
    @Test public void saveAndLoadMediumImage() {
        byte[] image = new byte[100]; //100butes
        (new Random()).nextBytes(image);
        Photo photo = new PhotoEntity();
        photo.setId(TEST_ID);
        photo.setImage(image);
        photoManager.save(photo);
        photo = null;
        
        photo = photoManager.getById(TEST_ID);
        assertTrue(Arrays.equals(image, photo.getImage()));
        photoManager.delete(TEST_ID);
    }
    
     @Test public void saveAndLoadSmallImage() {
        byte[] image = new byte[400000]; //400k
        (new Random()).nextBytes(image);
        Photo photo = new PhotoEntity();
        photo.setId(TEST_ID);
        photo.setImage(image);
        photoManager.save(photo);
        photo = null;
        
        photo = photoManager.getById(TEST_ID);
        assertTrue(Arrays.equals(image, photo.getImage()));
        photoManager.delete(TEST_ID);
    }
        
    

}
