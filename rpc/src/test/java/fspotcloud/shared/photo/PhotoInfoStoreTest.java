package fspotcloud.shared.photo;

import java.util.Date;
import java.util.SortedSet;
import java.util.TreeSet;

import junit.framework.TestCase;

public class PhotoInfoStoreTest extends TestCase {

	SortedSet<PhotoInfo> set;

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testPhotoInfoStore() {
		set = new TreeSet<PhotoInfo>();
		set.add(new PhotoInfo("5", "Me", new Date(0)));
		set.add(new PhotoInfo("4", "John", new Date(1)));
		set.add(new PhotoInfo("3", "Mary", new Date(2)));
		set.add(new PhotoInfo("2", "Pete", new Date(4)));
		@SuppressWarnings("unused")
		PhotoInfoStore store = new PhotoInfoStore(set);
		assertFalse(store.isEmpty());
		assertEquals(4, store.size());
	}

	public void testGetInfo() {
		set = new TreeSet<PhotoInfo>();
		set.add(new PhotoInfo("5", "Me", new Date(0)));
		PhotoInfoStore store = new PhotoInfoStore(set);
		assertNull(store.getInfo("1"));
		PhotoInfo me = store.getInfo("5");
		assertEquals("Me", me.getDescription());
	}

	public void testGet() {
		set = new TreeSet<PhotoInfo>();
		set.add(new PhotoInfo("5", "Me", new Date(0)));
		PhotoInfoStore store = new PhotoInfoStore(set);
		assertNotNull(store.get(0));
	}

	public void testIndexOf() {
		set = new TreeSet<PhotoInfo>();
		set.add(new PhotoInfo("5", "Me", new Date(0)));
		set.add(new PhotoInfo("4", "John", new Date(1)));
		set.add(new PhotoInfo("3", "Mary", new Date(2)));
		set.add(new PhotoInfo("2", "Pete", new Date(4)));
		PhotoInfoStore store = new PhotoInfoStore(set);
		assertEquals(-1, store.indexOf("Not found"));
		;
		assertEquals(0, store.indexOf("5"));
		assertEquals(1, store.indexOf("4"));
		assertEquals(2, store.indexOf("3"));
		assertEquals(3, store.indexOf("2"));
		
	}

	public void testLast() {
		set = new TreeSet<PhotoInfo>();
		set.add(new PhotoInfo("5", "Me", new Date(0)));
		set.add(new PhotoInfo("4", "John", new Date(1)));
		set.add(new PhotoInfo("3", "Mary", new Date(2)));
		set.add(new PhotoInfo("2", "Pete", new Date(4)));
		PhotoInfoStore store = new PhotoInfoStore(set);
		PhotoInfo last = store.last();
		assertEquals("2", last.getId()); // Pete
	}

	public void testLastOnEmptyStore() {
		set = new TreeSet<PhotoInfo>();
		PhotoInfoStore store = new PhotoInfoStore(set);
		try {
			PhotoInfo info = store.last();
			fail("Exception expected");
		} catch (ArrayIndexOutOfBoundsException e) {
			
		}finally {
		}
	}

}
