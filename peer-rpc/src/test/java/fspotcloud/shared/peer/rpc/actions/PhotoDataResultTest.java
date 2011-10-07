package fspotcloud.shared.peer.rpc.actions;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

public class PhotoDataResultTest extends TestCase {
	
	private static final String PHOTO_ID = "1";
	PhotoDataResult result;
	
	@Override
	protected void setUp() throws Exception {
		List<String> tags = new ArrayList<String>();
		tags.add("TAG");
		PhotoData p1 = new PhotoData(PHOTO_ID, "Story", new Date(10), tags);
		List<PhotoData> list = new ArrayList<PhotoData>();
		list.add(p1);
		result = new PhotoDataResult(list);
		super.setUp();
	}

	public void testSerialize() throws Exception {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream out = new ObjectOutputStream(bos);
		out.writeObject(result);
		out.close();
	}
	
	public void testData() {
		String id = result.getPhotoDataList().get(0).getPhotoId();
		assertEquals(PHOTO_ID, id);
	}
}
