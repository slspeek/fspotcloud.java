package fspotcloud.server.model.photo;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import net.sf.jsr107cache.Cache;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

import fspotcloud.server.model.MemCacheProvider;
import fspotcloud.server.model.api.Photo;
import fspotcloud.server.model.api.Photos;
public class CachedPhotoManagerTest extends TestCase {
	private static final String PHOTO_ID_0 = "0";

	private final LocalServiceTestHelper helper = new LocalServiceTestHelper(
			new LocalDatastoreServiceTestConfig());

	Photos manager;
	CachedPhotoManager target;
	PhotoDO original;
	
	protected void setUp() throws Exception {
		super.setUp();
		helper.setUp();
		original = new PhotoDO();
		original.setId(PHOTO_ID_0);
		manager = mock(Photos.class);
		
		MemCacheProvider provider = new MemCacheProvider();
		Cache cache = provider.get();
		target = new CachedPhotoManager(cache, manager);
		
	}

	public void tearDown() {
		helper.tearDown();
	}

	public void testGetOrNew() {
		when(manager.getOrNew(PHOTO_ID_0)).thenReturn(original);
		Photo photo0 = target.getOrNew(PHOTO_ID_0);
		verify(manager).getOrNew(PHOTO_ID_0);
		Photo photo0fromcache = target.getOrNew(PHOTO_ID_0);
		verifyNoMoreInteractions(manager);
	}

	public void testSave() {
		target.save(original);
		Photo cached = target.getById(PHOTO_ID_0);
		verify(manager).save(original);
		verifyNoMoreInteractions(manager);
	}

	public void testSaveAll() {
		List<Photo> list = new ArrayList<Photo>();
		list.add(original);
		target.saveAll(list);
		Photo cached = target.getById(PHOTO_ID_0);
		verify(manager).saveAll(list);
		verifyNoMoreInteractions(manager);
	}

	public void testGetById() {
		when(manager.getById(PHOTO_ID_0)).thenReturn(original);
		Photo photo0 = target.getById(PHOTO_ID_0);
		verify(manager).getById(PHOTO_ID_0);
		Photo photo0fromcache = target.getOrNew(PHOTO_ID_0);
		verifyNoMoreInteractions(manager);
	}

}
