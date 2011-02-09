package fspotcloud.shared.tag;

import junit.framework.TestCase;

public class TagNodeTest extends TestCase {

	TagNode node;
	protected void setUp() throws Exception {
		super.setUp();
		node = new TagNode();
		node.setTagName("Friends");
		node.setDescription("Friends and family");
		node.setId("1");
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
}
