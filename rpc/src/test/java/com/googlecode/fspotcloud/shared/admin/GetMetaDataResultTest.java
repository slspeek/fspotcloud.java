package com.googlecode.fspotcloud.shared.admin;

import com.googlecode.fspotcloud.shared.admin.GetMetaDataResult;
import java.util.Date;

import junit.framework.TestCase;

public class GetMetaDataResultTest extends TestCase {

	GetMetaDataResult result;

	@Override
	protected void setUp() throws Exception {
		result = new GetMetaDataResult();
		super.setUp();
	}

	public void testPhotoCount() {
		final int count = 100000;
		result.setPhotoCount(count);
		assertEquals(count, result.getPhotoCount());

	}

	public void testPhotosLastCounted() {
		final Date lastCounted = new Date(10000);
		result.setPhotosLastCounted(lastCounted);
		assertEquals(lastCounted, result.getPhotosLastCounted());
	}

	public void testGetCreated() {
		assertNotNull(result.getCreated());
	}

	public void testPeerPhotoCount() {
		final int peerCount = 100000;
		result.setPeerPhotoCount(peerCount);
		assertEquals(peerCount, result.getPeerPhotoCount());
	}

	public void testTagCount() {
		final int tagCount = 1000;
		result.setTagCount(tagCount);
		assertEquals(tagCount, result.getTagCount());
	}

	public void testPeerLastSeen() {
		final Date peerLastSeen = new Date(0);
		result.setPeerLastSeen(peerLastSeen);
		assertEquals(peerLastSeen, result.getPeerLastSeen());
	}

	public void testGetInstanceName() {
		final String INSTANCE = "FOO";
		result.setInstanceName(INSTANCE);
		assertEquals(INSTANCE, result.getInstanceName());
	}

	public void testGetPendingCommandCount() {
		final int PENDING_COUNT = 999;
		result.setPendingCommandCount(PENDING_COUNT);
		assertEquals(PENDING_COUNT, result.getPendingCommandCount());
	}

}
