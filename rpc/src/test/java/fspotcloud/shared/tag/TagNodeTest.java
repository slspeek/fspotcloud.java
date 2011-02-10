package fspotcloud.shared.tag;

import java.util.Date;

import junit.framework.TestCase;

import com.google.common.collect.ImmutableList;

import fspotcloud.shared.photo.PhotoInfo;

public class TagNodeTest extends TestCase {

	TagNode node;
	protected void setUp() throws Exception {
		super.setUp();
		node = new TagNode();
		node.setTagName("Friends");
		node.setDescription("Friends and family");
		node.setId("1");
		PhotoInfo man = new PhotoInfo("2", "Human", new Date());
		node.setCachedPhotoList(ImmutableList.of(man));
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testEqualsObject() {
		TagNode other = new TagNode();
		other.setId("1");
		assertEquals(node, other);
	}

	public void testConstructor() {
		TagNode t = new TagNode("1", "0");
		assertEquals("1", t.getId());
		assertEquals("0", t.getParentId());
	}
	
	public void testToString() {
		String s = node.toString();
		System.out.println(s);
	}
}
