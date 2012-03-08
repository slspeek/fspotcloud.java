package com.googlecode.fspotcloud.server.admin.actions;

import static org.mockito.Mockito.when;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.googlecode.botdispatch.controller.dispatch.ControllerDispatchAsync;
import com.googlecode.fspotcloud.model.jpa.tag.TagEntity;
import com.googlecode.fspotcloud.server.control.task.actions.intern.PhotoUpdateAction;
import com.googlecode.fspotcloud.server.model.api.Tag;
import com.googlecode.fspotcloud.server.model.api.Tags;
import com.googlecode.fspotcloud.shared.dashboard.actions.ImportTag;
import com.googlecode.fspotcloud.user.AdminPermission;
import com.googlecode.taskqueuedispatch.SerializableAsyncCallback;

public class ImportTagHandlerTest {

	private static final int COUNT = 10;
	private static final int PREVIOUS_COUNT = 6;
	private static final String TAG_ID = "Foo";
	@Mock
	private Tags tagManager;
	@Mock
	private ControllerDispatchAsync dispatch;
        @Mock
        AdminPermission adminPermission;
	ImportTagHandler handler;
	ImportTag action = new ImportTag(TAG_ID, PREVIOUS_COUNT);
	Tag tag;

	ArgumentCaptor<PhotoUpdateAction> actionCaptor;
	ArgumentCaptor<SerializableAsyncCallback> callbackCaptor;

	@BeforeMethod
	protected void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		handler = new ImportTagHandler(tagManager, dispatch, adminPermission);
		tag = new TagEntity();
		tag.setId(TAG_ID);
		tag.setCount(COUNT);
		when(tagManager.find(TAG_ID)).thenReturn(tag);
	}

	@Test
	public void testExecute() throws DispatchException {

	}

	@Test
	public void testExecuteAllreadyImported() throws DispatchException {

	}

}
