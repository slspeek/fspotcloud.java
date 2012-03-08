package com.googlecode.fspotcloud.server.control.task.actions.intern;

import com.googlecode.fspotcloud.server.control.task.actions.intern.TagImportAction;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.AssertJUnit;
import org.apache.commons.lang.SerializationUtils;

public class TagImportActionTest {

	private static final int OFFSET = 0;
	private static final int LIMIT = 1;

	@BeforeMethod
	protected void setUp() throws Exception {
	}

	@Test
	public void testTagImportActionIO() {
		TagImportAction action = new TagImportAction(OFFSET,LIMIT);
		byte[] ser = SerializationUtils.serialize(action);
		TagImportAction des = (TagImportAction) SerializationUtils.deserialize(ser);
	}

	@Test
	public void testGetOffset() {
		TagImportAction action = new TagImportAction(OFFSET,LIMIT);
		AssertJUnit.assertEquals(OFFSET, action.getOffset());
	}

	@Test
	public void testGetLimit() {
		TagImportAction action = new TagImportAction(OFFSET,LIMIT);
		AssertJUnit.assertEquals(LIMIT, action.getLimit());
	}

	@Test
	public void testEquals() {
		TagImportAction action = new TagImportAction(OFFSET,LIMIT);
		TagImportAction other = new TagImportAction(OFFSET,LIMIT);
		AssertJUnit.assertEquals(action, other);
		AssertJUnit.assertEquals( other, action);
	}

	
}
