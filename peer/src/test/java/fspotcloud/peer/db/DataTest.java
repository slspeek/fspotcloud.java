/**
 * 
 */
package fspotcloud.peer.db;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import junit.framework.TestCase;
import fspotcloud.shared.peer.rpc.actions.PhotoData;
import fspotcloud.shared.peer.rpc.actions.TagData;

public class DataTest extends TestCase {
	final static private Logger log = Logger
			.getLogger(DataTest.class.getName());
	private Data data;

	protected void setUp() throws Exception {
		super.setUp();
		URL testDatabase = ClassLoader.getSystemResource("photos.db");
		String path = testDatabase.getPath();
		data = new Data("jdbc:sqlite:" +path);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		data = null;
	}

	public void testGetPhotoCount() throws SQLException {
		int count = data.getCount("photos");
		assertEquals(28, count);
	}
	
	public void testGetTagCount() throws SQLException {
		int count = data.getCount("tags");
		assertEquals(5, count);
	}

	public final void testGetTagList2() throws SQLException {
		List<TagData> result = data.getTagData(0, 1);
		assertEquals(1, result.size());
	}

	public final void testGetTagListOverItsSize() throws SQLException {
		List<TagData> result = data.getTagData(8,2);
		assertEquals(0, result.size());
	}

		public void testGetPhotoKeysForTag() throws Exception {
		List<String> result = data.getPhotoKeysInTag("5");
		assertEquals("3", result.get(0));
	}

	public final void testGetImageURL() throws MalformedURLException,
			SQLException {
		String url = data.getImageURL("20");
		assertEquals("file:///home/steven/Photos/2010/06/22/img_0859-1.jpg", String.valueOf(url));
		
	}
	
	
	public void testIsPhotoInTag() throws Exception {
		boolean yep = data.isPhotoInTag("5", "3");
		assertTrue(yep);
		boolean nope = data.isPhotoInTag("5", "4");
		assertFalse(nope);
	}
	
	public void testPhotoVersion() throws Exception {
		assertEquals(1, data.getPhotoDefaultVersion("3"));
	}
}
