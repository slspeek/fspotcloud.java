package fspotcloud.shared.peer.rpc.actions;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

import junit.framework.TestCase;

public class GetImageDataTest extends TestCase {

	private static final String PHOTO_ID = "foo";
	GetImageData action = new GetImageData(PHOTO_ID);

	
	public void testSerialize() throws Exception {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream out = new ObjectOutputStream(bos);
		out.writeObject(action);
		out.close();
	}
	
	public void testGetPhotoId() {
		assertEquals(PHOTO_ID, action.getPhotoId());
	}

}
