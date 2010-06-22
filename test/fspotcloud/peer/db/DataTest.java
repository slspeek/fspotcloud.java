/**
 * 
 */
package fspotcloud.peer.db;

import java.sql.SQLException;

import junit.framework.TestCase;

/**
 * @author rocco
 * 
 */
public class DataTest extends TestCase {

	private Data data;

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		data = new Data();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
		data = null;
	}

	/**
	 * Test method for {@link fspotcloud.peer.db.Data#getPhotoCount()}.
	 */
	public final void testGetPhotoCount() {
		try {
			int count = data.getPhotoCount();
			assertTrue(count > 100);
		} catch (SQLException e) {
			fail(e.getMessage());
		}
	}

	/**
	 * Test method for {@link fspotcloud.peer.db.Data#getTagList()}.
	 */
	public final void testGetTagList() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link fspotcloud.peer.db.Data#getPhotoList(java.lang.String, java.lang.String)}
	 * .
	 */
	public final void testGetPhotoList() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link fspotcloud.peer.db.Data#getImageURL(java.lang.String)}.
	 */
	public final void testGetImageURL() {
		fail("Not yet implemented"); // TODO
	}

}
