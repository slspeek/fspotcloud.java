package fspotcloud.server.model.tag;

import static org.mockito.Mockito.mock;
import junit.framework.TestCase;
import net.sf.jsr107cache.Cache;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

import fspotcloud.server.model.MemCacheProvider;
import fspotcloud.server.model.api.Tag;
import fspotcloud.server.model.api.Tags;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
public class CachedTagManagerTest extends TestCase {
	private static final String TAG_ID_0 = "0";

	private final LocalServiceTestHelper helper = new LocalServiceTestHelper(
			new LocalDatastoreServiceTestConfig());

	Tags manager;
	CachedTagManager target;
	Cache cache;
		
	public void setUp() {
		helper.setUp();
		manager = mock(Tags.class);
		MemCacheProvider provider = new MemCacheProvider();
		Cache cache = provider.get();
		target = new CachedTagManager(cache, manager);
	}

	public void tearDown() {
		helper.tearDown();
	}

	public void testGetOrNew() {
		TagDO tag0tagManager = new TagDO();
		tag0tagManager.setId(TAG_ID_0);
		when(manager.getOrNew(TAG_ID_0)).thenReturn(tag0tagManager);
		Tag tag0 = target.getOrNew(TAG_ID_0);
		verify(manager).getOrNew(TAG_ID_0);
		Tag tag0fromcache = target.getOrNew(TAG_ID_0);
		verifyNoMoreInteractions(manager);
	}

	public void testGetById() {
		TagDO tag0tagManager = new TagDO();
		tag0tagManager.setId(TAG_ID_0);
		when(manager.getById(TAG_ID_0)).thenReturn(tag0tagManager);
		Tag tag0 = target.getById(TAG_ID_0);
		verify(manager).getById(TAG_ID_0);
		Tag tag0fromcache = target.getById(TAG_ID_0);
		verifyNoMoreInteractions(manager);
	}

	public void testSave() {
		TagDO tag0 = new TagDO();
		tag0.setId(TAG_ID_0);
		target.save(tag0);
		verify(manager).save(tag0);
		
		Tag tag0fromcache = target.getOrNew(TAG_ID_0);
		verifyNoMoreInteractions(manager);
	}

}
