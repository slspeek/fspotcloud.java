package fspotcloud.shared.peer.rpc.actions;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

import junit.framework.TestCase;

public class TagDataTest extends TestCase {

	
	private static final int COUNT = 10;
	private static final String PARENT = "2";
	private static final String NAME = "Tag";
	private static final String TAG_ID = "1";
	TagData tag = new TagData(TAG_ID, NAME, PARENT, COUNT);
	
	public void testSerialize() throws Exception {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream out = new ObjectOutputStream(bos);
		out.writeObject(tag);
		out.close();
	}
	
	public void testId() {
		assertEquals(TAG_ID, tag.getTagId());
	}
	
	public void testParent() {
		assertEquals(PARENT, tag.getParentId());
	}
	
	public void testName() {
		assertEquals(NAME, tag.getName());
	}
	
	public void testCount() {
		assertEquals(COUNT, tag.getCount());
	}
}
