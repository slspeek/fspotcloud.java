package fspotcloud.shared.peer.rpc.actions;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

import junit.framework.TestCase;

public class ImageDataResultTest extends TestCase {

	private static final int BYTE_COUNT = 10;
	byte[] DATA = new byte[BYTE_COUNT];
	String EXIF = "bar";
	ImageDataResult result = new ImageDataResult(DATA, EXIF);

	public void testSerialize() throws Exception {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream out = new ObjectOutputStream(bos);
		out.writeObject(result);
		out.close();
	}
	public void testGetImageBinary() {
		assertEquals(BYTE_COUNT, result.getImageBinary().length);
	}

	public void testGetExif() {
		assertEquals(EXIF, result.getExif());
	}

}
