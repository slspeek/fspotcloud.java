package fspotcloud.shared.peer.rpc.actions;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

import junit.framework.TestCase;

public class GetImageDataTest extends TestCase {

	private static final int TYPE = 0;

	private static final int HEIGHT = 768;

	private static final int WIDTH = 1024;

	private static final String PHOTO_ID = "foo";
	
	GetImageData action = new GetImageData(PHOTO_ID, WIDTH, HEIGHT, TYPE);

	
	public void testSerialize() throws Exception {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream out = new ObjectOutputStream(bos);
		out.writeObject(action);
		out.close();
	}
	
	public void testGetPhotoId() {
		assertEquals(PHOTO_ID, action.getPhotoId());
	}
	
	public void testType() {
		assertEquals(TYPE, action.getType());
	}
	
	public void testWidth() {
		assertEquals(WIDTH, action.getWidth());
	}
	
	public void testHeight() {
		assertEquals(HEIGHT, action.getHeight());
	}

}
