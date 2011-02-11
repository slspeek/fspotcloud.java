package fspotcloud.shared.tag;

import java.util.Date;

import junit.framework.TestCase;

import com.google.common.collect.ImmutableSortedSet;

import fspotcloud.shared.photo.PhotoInfo;
import fspotcloud.shared.photo.PhotoInfoStore;

public class TagNodeTest extends TestCase {

	TagNode node;
	
	protected void setUp() throws Exception {
		super.setUp();
		node = new TagNode();
		node.setTagName("Friends");
		node.setDescription("Friends and family");
		node.setId("1");
		PhotoInfo man = new PhotoInfo("2", "Human", new Date());;
		PhotoInfoStore store = new PhotoInfoStore(ImmutableSortedSet.of(man));
		node.setCachedPhotoList(store);
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
