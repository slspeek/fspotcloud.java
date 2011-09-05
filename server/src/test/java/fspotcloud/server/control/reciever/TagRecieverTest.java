package fspotcloud.server.control.reciever;

import static org.mockito.Mockito.mock;

import java.util.List;

import org.mockito.ArgumentCaptor;

import fspotcloud.server.model.api.Photo;
import fspotcloud.server.model.api.Tag;
import fspotcloud.server.model.api.Tags;
import fspotcloud.server.model.tag.TagDO;
import junit.framework.TestCase;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TagRecieverTest extends TestCase {


	Tags tagManager;
	TagDO tag;
	TagReciever reciever;
	final String TAGNAME = "Foo";
	final String TAGID = "FooID";
	Object[] incoming;
	Object[] row;
	ArgumentCaptor<List<Tag>> argumentCaptor = (ArgumentCaptor<List<Tag>>) (Object) ArgumentCaptor.forClass(List.class);


	@Override
	protected void setUp() throws Exception {
		tagManager = mock(Tags.class);
		tag = new TagDO();
		tag.setId(TAGID);
		row = new Object[] { TAGID, TAGNAME, null, "10" };
		incoming = new Object[] { row };
		when(tagManager.getOrNew(TAGID)).thenReturn(tag);
		super.setUp();
	}

	public void testTagReciever() {
		reciever = new TagReciever(tagManager);
		assertNotNull(reciever);
	}

	public void testRecieveTagData() {
		testTagReciever();
		reciever.recieveTagData(incoming);
		assertEquals(10, tag.getCount());
		assertEquals(TAGNAME,tag.getTagName());
		assertNull(tag.getParentId());
		verify(tagManager).saveAll(argumentCaptor.capture());
		List<Tag> listOfTags = argumentCaptor.getValue();
		assertEquals(tag, listOfTags.get(0));
	}

}
