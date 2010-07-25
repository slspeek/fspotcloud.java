package fspotcloud.server.model.photo;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.google.inject.Provider;

import fspotcloud.server.model.PersistenceManagerProvider;
import fspotcloud.server.model.api.Photo;
import fspotcloud.server.model.api.Photos;
import fspotcloud.server.model.tag.TagDO;

public class PhotoManagerTest extends TestCase {

	private final LocalServiceTestHelper helper = new LocalServiceTestHelper(
			new LocalDatastoreServiceTestConfig());

	private Photos photoManager;

	private Provider<PersistenceManager> pmProviver = new PersistenceManagerProvider();

	@Before
	public void setUp() {
		helper.setUp();
		photoManager = new PhotoManager(pmProviver, 100);
	}

	@After
	public void tearDown() {
		helper.tearDown();
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
	public void testGetPhotosStartingAtDate() {
		Photo before = new PhotoDO();
		before.setDate(getDate(2006, 0, 0));
	}

	@Test
	public void testGetOrNew() {
		Photo photo = photoManager.getOrNew("1");
		try {
			photoManager.getById("1");
			fail();
		} catch(JDOObjectNotFoundException yes) {
			
		}
		
	}
	
	public void testFilteringOnBoolean() {
		Photo photo = photoManager.getOrNew("1");
		photo.setImageLoaded(true);
		photoManager.save(photo);
		
		PersistenceManager pm = pmProviver.get();
		Query query = pm.newQuery(PhotoDO.class);
		query.setFilter("imageLoaded == true");
		List<PhotoDO> photos = (List<PhotoDO>) query.execute();
		assertEquals(1, photos.size());
		
		//see that there are no photos with image loaded is false
		pm = pmProviver.get();
		query = pm.newQuery(PhotoDO.class);
		query.setFilter("imageLoaded == false");
		photos = (List<PhotoDO>) query.execute();
		assertEquals(0, photos.size());
		
		
	}
	
	public void testCreateLoadModify() {
		Photo photo, retrieved;
		photo = new PhotoDO();
		photo.setName("1");
		photoManager.save(photo);
		retrieved = photoManager.getOrNew("1");
		retrieved.setDescription("Nice");
		photoManager.save(retrieved);
		retrieved = photoManager.getOrNew("1");
		assertEquals("Nice", retrieved.getDescription());
	}

	@Test
	public void testGetOldestPhotosChunk() {
	}

	@Test
	public void testDelete() {
		Photo photo = photoManager.getOrNew("1");
		
		
	}

	@Test
	public void testDeleteAll() {
	}

	@Test
	public void testSave() {
		PhotoDO photo = new PhotoDO();
		photo.setName("1");
		photo.setDescription("Nice");
		photoManager.save(photo);
		Photo retrieved = photoManager.getOrNew("1");
		assertEquals("Nice", retrieved.getDescription());
	}

	@Test
	public void testSaveAll() {
	}

}
