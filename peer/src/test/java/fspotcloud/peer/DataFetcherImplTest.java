package fspotcloud.peer;

import java.net.URL;

import fspotcloud.peer.db.Data;
import junit.framework.TestCase;

public class DataFetcherImplTest extends TestCase {
	private Data data;
	private DataFetcher fetcher;

	protected void setUp() throws Exception {
		super.setUp();
		URL testDatabase = ClassLoader.getSystemResource("photos.db");
		String path = testDatabase.getPath();
		data = new Data("jdbc:sqlite:" + path);
		fetcher = new DataFetcherImpl(data);
	}

	public void testMethodNotFound() {
		try {
			fetcher.getData("nonExistentMethodName", null);
			fail();
		} catch (MethodNotFoundException e) {

		}
	}

	public void testGetPhotoCount() throws Exception {
		Object[] args = new Object[] {};
		Object[] result = (Object[]) fetcher.getData("getMetaData", args);
		
		assertEquals("28", String.valueOf(result[0]));
		assertEquals("8", String.valueOf(result[1]));
	}
}
