package com.googlecode.fspotcloud.shared.peer.rpc.actions;

import com.googlecode.fspotcloud.shared.peer.rpc.actions.PeerMetaDataResult;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

import junit.framework.TestCase;

public class PeerMetaDataResultTest extends TestCase {

	
	int TAG_COUNT = 10;
	int PHOTO_COUNT = 1000;
	PeerMetaDataResult result = new PeerMetaDataResult(TAG_COUNT, PHOTO_COUNT);
	
	
	public void testSerialize() throws Exception {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream out = new ObjectOutputStream(bos);
		out.writeObject(result);
		out.close();
	}
	
	
	public void testTagCount() {
		assertEquals(TAG_COUNT, result.getTagCount());
	}
	
	public void testPhotoCount() {
		assertEquals(PHOTO_COUNT, result.getPhotoCount());
	}
}

