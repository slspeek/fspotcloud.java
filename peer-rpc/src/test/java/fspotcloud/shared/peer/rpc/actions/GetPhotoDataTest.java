package fspotcloud.shared.peer.rpc.actions;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

import junit.framework.TestCase;

public class GetPhotoDataTest extends TestCase {

	private static final int THUMB_WIDTH = 300;
	private static final int BIG_HEIGHT = 200;
	private static final int BIG_WIDTH = 100;
	private static final int THUMB_HEIGHT = 400;
	private static final int COUNT = 30;
	private static final int OFFSET = 20;
	private static final String TAG_ID = "Foo";
	private static final String MIN_KEY = "Bar";
	
	GetPhotoData action = new GetPhotoData(TAG_ID, MIN_KEY, OFFSET, COUNT, BIG_WIDTH, BIG_HEIGHT, THUMB_WIDTH, THUMB_HEIGHT);

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

	public void testGetTagId() {
		assertEquals(TAG_ID, action.getTagId());
	}
	
	public void testGetBigWidth() {
		assertEquals(BIG_WIDTH, action.getBigWidth());
	}

	public void testGetBigHeight() {
		assertEquals(BIG_HEIGHT, action.getBigHeight());
	}

	public void testGetThumbWidth() {
		assertEquals(THUMB_WIDTH, action.getThumbWidth());
	}

	public void testGetThumbHeight() {
		assertEquals(THUMB_HEIGHT, action.getThumbHeigth());
	}
	public void testGetMinKey() {
		assertEquals(MIN_KEY, action.getMinKey());
	}

	
}
