package fspotcloud.server.control.callback;

import fspotcloud.model.jpa.tag.TagEntity;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.AssertJUnit;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import net.customware.gwt.dispatch.server.Dispatch;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.mockito.ArgumentCaptor;

import fspotcloud.server.model.api.Tag;
import fspotcloud.server.model.api.Tags;
import fspotcloud.shared.dashboard.actions.ImportTag;
import fspotcloud.shared.peer.rpc.actions.TagData;
import fspotcloud.shared.peer.rpc.actions.TagDataResult;

public class TagDataCallbackTest {

Dispatch dispatch;
	Tags tagManager;
	Tag tag;
	TagDataCallback callback;
	final String TAGNAME = "Foo";
	final String TAGID = "FooID";
	TagDataResult incoming;
	TagData row;
	ArgumentCaptor<List<Tag>> argumentCaptor = (ArgumentCaptor<List<Tag>>) (Object) ArgumentCaptor.forClass(List.class);


	@BeforeMethod
	protected void setUp() throws Exception {
		dispatch = mock(Dispatch.class);
		tagManager = mock(Tags.class);
		tag = new TagEntity();
		tag.setId(TAGID);
		row = new TagData(TAGID, TAGNAME, null, 10);
		
		List<TagData> list = new ArrayList<TagData>();
		list.add(row);
		incoming = new TagDataResult(list);
		when(tagManager.findOrNew(TAGID)).thenReturn(tag);
		callback = new TagDataCallback(tagManager, dispatch);
	}

	@Test
	public void testSerialize() throws Exception {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream out = new ObjectOutputStream(bos);
		out.writeObject(callback);
		out.close();
	}
	
	@Test
	public void testRecieveTagData() throws DispatchException {
		callback.onSuccess(incoming);
		AssertJUnit.assertEquals(10, tag.getCount());
		AssertJUnit.assertEquals(TAGNAME,tag.getTagName());
		AssertJUnit.assertNull(tag.getParentId());
		verifyNoMoreInteractions(dispatch);
	}
	
	@Test
	public void testRecieveTagDataImported() throws DispatchException {
		tag.setImportIssued(true);
		callback.onSuccess(incoming);
		AssertJUnit.assertEquals(10, tag.getCount());
		AssertJUnit.assertEquals(TAGNAME,tag.getTagName());
		AssertJUnit.assertNull(tag.getParentId());
		ArgumentCaptor<ImportTag> actionCaptor = ArgumentCaptor.forClass(ImportTag.class);
		verify(dispatch).execute(actionCaptor.capture());
		ImportTag action = actionCaptor.getValue();
		AssertJUnit.assertEquals(TAGID, action.getTagId());
		
	}

}
