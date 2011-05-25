package fspotcloud.server.model.tag;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;

import junit.framework.TestSuite;

import com.google.inject.Provider;

import fspotcloud.server.model.DatastoreTest;
import fspotcloud.server.model.PersistenceManagerProvider;
import fspotcloud.server.model.api.Tag;
import fspotcloud.server.model.api.Tags;
import fspotcloud.shared.tag.TagNode;

public class TagManagerTest extends DatastoreTest {

	private Provider<PersistenceManager> pmProviver = new PersistenceManagerProvider();
	private Tags tagManager = new TagManager(pmProviver);

	public static TestSuite suite() {
		return new TestSuite(TagManagerTest.class);
	}

	public Date getDate(int year, int month, int date) {
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.DATE, date);
		return cal.getTime();
	}

	public void testGetTags() {
		Tag tag = new TagDO();
		tag.setId("20");
		tag.setCount(10);

		tagManager.save(tag);
		List<TagNode> tags = tagManager.getTags();
		TagNode node = tags.get(0);
		assertEquals(10, node.getCount());
	}

	public void testDeleteAllTags() {
		Tag tag = new TagDO();
		tag.setId("20");
		tag.setCount(10);

		tagManager.save(tag);

		tagManager.getOrNew("21");

		tagManager.deleteAll();

		try {
			tagManager.getById("21");
			fail();
		} catch (JDOObjectNotFoundException e) {
		}

	}
}
