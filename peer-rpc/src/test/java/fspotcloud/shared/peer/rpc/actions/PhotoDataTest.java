package fspotcloud.shared.peer.rpc.actions;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

public class PhotoDataTest extends TestCase {

	private static final byte[] IMAGE_DATA = new byte[] { 0, 1};
	private static final byte[] THUMB_DATA = new byte[] {0};
	private static final String TAG = "TAG";
	private static final Date LONG_TIME_AGO = new Date(10);
	private static final String DESCR = "Story";
	private static final String PHOTO_ID = "1";
	PhotoData data;
	
	@Override
	protected void setUp() throws Exception {
		List<String> tags = new ArrayList<String>();
		tags.add(TAG);
		data = new PhotoData(PHOTO_ID, DESCR, LONG_TIME_AGO, IMAGE_DATA, THUMB_DATA, tags);
		super.setUp();
	}
	
	public void testSerialize() throws Exception {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream out = new ObjectOutputStream(bos);
		out.writeObject(data);
		out.close();
	}
	public void testGetPhotoId() {
		assertEquals(PHOTO_ID, data.getPhotoId());
	}

	public void testGetDesscription() {
		assertEquals(DESCR, data.getDescription());
	}

	public void testGetDate() {
		assertEquals(LONG_TIME_AGO, data.getDate());
	}

	public void testGetTagList() {
		assertEquals(TAG, data.getTagList().get(0));
	}

	public void testGetThumbData() {
		assertEquals(THUMB_DATA, data.getThumbData());
	}

	public void testGetImageData() {
		assertEquals(IMAGE_DATA, data.getImageData());
	}

	
	
}
