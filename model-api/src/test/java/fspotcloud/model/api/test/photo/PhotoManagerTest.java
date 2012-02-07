package fspotcloud.model.api.test.photo;

import com.google.common.collect.ImmutableList;
import com.google.guiceberry.junit4.GuiceBerryRule;
import com.google.inject.Inject;
import fspotcloud.model.api.test.EmptyGuiceBerryEnv;
import java.util.Calendar;
import java.util.Date;




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
    public GuiceBerryRule guiceBerry = new GuiceBerryRule(EmptyGuiceBerryEnv.class);
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
        photo = photoManager.getOrNew(TEST_ID);
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
        Photo photo = photoManager.getOrNew(TEST_ID);
        photo.setDescription("Nice");
        photoManager.save(photo);
        Photo retrieved = photoManager.getOrNew(TEST_ID);
        assertEquals("Nice", retrieved.getDescription());
        photoManager.delete(TEST_ID);
    }

    @Test
    public void testDelete() {
        Photo photo = photoManager.getOrNew(TEST_ID);
        photo.setDescription("Nice");
        photoManager.save(photo);
        photoManager.delete(TEST_ID);
    }
    
    @Test public void tagListPersists() {
        List abc = ImmutableList.of("a", "b", "c");
        Photo photo = photoManager.getOrNew(TEST_ID);
        photo.setTagList(abc);
        photoManager.save(photo);
        photo = null;
        
        photo = photoManager.getById(TEST_ID);
        assertEquals(abc, photo.getTagList());
    }
    
    @Test public void saveAndLoad2kImage() {
        byte[] image = new byte[2000]; 
        testSavingAndLoading(image);
    }
    
    @Test public void saveAndLoad10kImage() {
        byte[] image = new byte[10000]; 
        testSavingAndLoading(image);
    }

    private void testSavingAndLoading(byte[] image) {
        (new Random()).nextBytes(image);
        Photo photo = photoManager.getOrNew(TEST_ID);
        photo.setId(TEST_ID);
        photo.setImage(image);
        photoManager.save(photo);
        photo = null;
        
        photo = photoManager.getById(TEST_ID);
        assertTrue(Arrays.equals(image, photo.getImage()));
        photoManager.delete(TEST_ID);
    }
    
     @Test public void saveAndLoad400kImage() {
        byte[] image = new byte[400000];
        testSavingAndLoading(image);
    }
     
     @Test public void saveAndLoad2MImage() {
        byte[] image = new byte[2000000];
        testSavingAndLoading(image);
    }
        
    

}
