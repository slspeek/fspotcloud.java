package fspotcloud.shared.peer.rpc.actions;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

import junit.framework.TestCase;

public class GetPhotoDataTest extends TestCase {

	private static final int COUNT = 30;
	private static final int OFFSET = 20;
	GetPhotoData action = new GetPhotoData(OFFSET, COUNT);

	public void testSerialize() throws Exception {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream out = new ObjectOutputStream(bos);
		out.writeObject(action);
		out.close();
	}

	public void testGetOffset() {
		assertEquals(OFFSET, action.getOffset());
	}

	public void testGetCount() {
		assertEquals(COUNT, action.getCount());
	}

}
