package fspotcloud.server.control.task.actions.intern;

import org.apache.commons.lang.SerializationUtils;

import junit.framework.TestCase;

public class TagImportActionTest extends TestCase {

	private static final int OFFSET = 0;
	private static final int LIMIT = 1;

	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testTagImportActionIO() {
		TagImportAction action = new TagImportAction(OFFSET,LIMIT);
		byte[] ser = SerializationUtils.serialize(action);
		TagImportAction des = (TagImportAction) SerializationUtils.deserialize(ser);
	}

	public void testGetOffset() {
		TagImportAction action = new TagImportAction(OFFSET,LIMIT);
		assertEquals(OFFSET, action.getOffset());
	}

	public void testGetLimit() {
		TagImportAction action = new TagImportAction(OFFSET,LIMIT);
		assertEquals(LIMIT, action.getLimit());
	}

	public void testEquals() {
		TagImportAction action = new TagImportAction(OFFSET,LIMIT);
		TagImportAction other = new TagImportAction(OFFSET,LIMIT);
		assertEquals(action, other);
		assertEquals( other, action);
	}

	
}
