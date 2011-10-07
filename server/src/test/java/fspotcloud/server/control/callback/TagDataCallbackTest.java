package fspotcloud.server.control.callback;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.mockito.ArgumentCaptor;

import fspotcloud.server.model.api.Tag;
import fspotcloud.server.model.api.Tags;
import fspotcloud.server.model.tag.TagDO;
import fspotcloud.shared.peer.rpc.actions.TagData;
import fspotcloud.shared.peer.rpc.actions.TagDataResult;

public class TagDataCallbackTest extends TestCase {


	Tags tagManager;
	TagDO tag;
	TagDataCallback callback;
	final String TAGNAME = "Foo";
	final String TAGID = "FooID";
	TagDataResult incoming;
	TagData row;
	ArgumentCaptor<List<Tag>> argumentCaptor = (ArgumentCaptor<List<Tag>>) (Object) ArgumentCaptor.forClass(List.class);


	@Override
	protected void setUp() throws Exception {
		tagManager = mock(Tags.class);
		tag = new TagDO();
		tag.setId(TAGID);
		row = new TagData(TAGID, TAGNAME, null, 10);
		
		List<TagData> list = new ArrayList<TagData>();
		list.add(row);
		incoming = new TagDataResult(list);
		when(tagManager.getOrNew(TAGID)).thenReturn(tag);
		callback = new TagDataCallback(tagManager);
		super.setUp();
	}

	public void testSerialize() throws Exception {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream out = new ObjectOutputStream(bos);
		out.writeObject(callback);
		out.close();
	}
	
	public void testRecieveTagData() {
		callback.onSuccess(incoming);
		assertEquals(10, tag.getCount());
		assertEquals(TAGNAME,tag.getTagName());
		assertNull(tag.getParentId());
		verify(tagManager).saveAll(argumentCaptor.capture());
		List<Tag> listOfTags = argumentCaptor.getValue();
		assertEquals(tag, listOfTags.get(0));
	}

}
