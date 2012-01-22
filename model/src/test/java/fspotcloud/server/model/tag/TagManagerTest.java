package fspotcloud.server.model.tag;

import java.util.List;
import java.util.logging.Logger;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;

import junit.framework.TestSuite;

import com.google.appengine.repackaged.com.google.common.collect.ImmutableList;
import com.google.inject.Provider;

import fspotcloud.server.model.DatastoreTest;
import fspotcloud.server.model.PersistenceManagerProvider;
import fspotcloud.server.model.api.Tag;
import fspotcloud.server.model.api.Tags;
import fspotcloud.shared.tag.TagNode;

public class TagManagerTest extends DatastoreTest {

	private Provider<PersistenceManager> pmProviver;
	private Tags tagManager;
	private static final Logger log = Logger.getLogger(TagManagerTest.class
			.getName());

	public static TestSuite suite() {
		return new TestSuite(TagManagerTest.class);
	}

	@Override
	public void setUp() throws Exception {
		pmProviver =  new PersistenceManagerProvider();
		tagManager  = new TagManager(pmProviver, 3);
		super.setUp();
	}

	public void testGetTags() {
		createSaveTag("21");
		List<TagNode> tags = tagManager.getTags();
		TagNode node = tags.get(0);
		assertEquals("21", node.getId());
	}

	public void testDeleteAllTags() {
		createSaveTag("20");
		createSaveTag("21");
		createSaveTag("22");
		List<String> list = ImmutableList.of("20", "21","22");
		tagManager.deleteAll(list);
		log.info("keys:"+tagManager.getTagKeys());
		assertTrue(tagManager.isEmpty());
		try {
			tagManager.getById("21");
			fail();
		} catch (JDOObjectNotFoundException e) {
		}

	}
	
	private Tag createSaveTag(String id) {
		Tag tag = new TagDO();
		tag.setId(id);
		tagManager.save(tag);
		return tag;
	}
	
	public void testIsEmpty() {
		assertTrue(tagManager.isEmpty());
	}
	
	
	public void testIsNotEmpty() {
		createSaveTag("0");
		assertFalse(tagManager.isEmpty());
	}
	
	public void testGetKeys() {
		createSaveTag("0");
		assertEquals("0", tagManager.getTagKeys().get(0));
	}
}
