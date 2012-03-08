package com.googlecode.fspotcloud.client.main.data;


import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import junit.framework.TestCase;

import org.mockito.ArgumentCaptor;

import com.google.gwt.user.client.rpc.AsyncCallback;

import com.googlecode.fspotcloud.client.data.DataManagerImpl;
import com.googlecode.fspotcloud.rpc.TagServiceAsync;
import com.googlecode.fspotcloud.shared.tag.TagNode;
import static org.mockito.Mockito.*;
public class DataManagerImplTest extends TestCase {

	private static final Logger log = Logger
			.getLogger(DataManagerImplTest.class.getName());
	private static final String ID = "1";
	private static final String WRONG_ID = "2";
	private DataManagerImpl dataManager;

	TagServiceAsync tagService;
	AsyncCallback<List<TagNode>> firstCall;
	AsyncCallback<TagNode> secondCall;
	AsyncCallback<List<TagNode>> thirdCall;
	ArgumentCaptor<AsyncCallback<List<TagNode>>> remoteCallCaptor;
	TagNode tagNode = new TagNode(ID);

	@Override
	protected void setUp() throws Exception {
		tagService = mock(TagServiceAsync.class);
		firstCall = mock(AsyncCallback.class);
		secondCall = mock(AsyncCallback.class);
		thirdCall = mock(AsyncCallback.class);
		dataManager = new DataManagerImpl(tagService);
		remoteCallCaptor = (ArgumentCaptor<AsyncCallback<List<TagNode>>>) (Object) ArgumentCaptor
				.forClass(AsyncCallback.class);
		super.setUp();
	}

	public void testSimpleCall() {
		dataManager.getTagTree(firstCall);
		verify(tagService).loadTagTree(remoteCallCaptor.capture());
		AsyncCallback<List<TagNode>> callback = remoteCallCaptor.getValue();
		List<TagNode> result = new ArrayList<TagNode>(); 
		callback.onSuccess(result);
		verify(firstCall).onSuccess(result);
		
	}
	

	public void testTwoCalls() {
		dataManager.getTagTree(firstCall);
		verify(tagService).loadTagTree(remoteCallCaptor.capture());
		dataManager.getTagNode(ID, secondCall);
		verifyNoMoreInteractions(tagService);
		
		AsyncCallback<List<TagNode>> callback = remoteCallCaptor.getValue();
		List<TagNode> result = new ArrayList<TagNode>(); 
		result.add(tagNode);
		callback.onSuccess(result);
		verify(firstCall).onSuccess(result);
		verify(secondCall).onSuccess(tagNode);
		
	}
	
	public void testThreeCalls() {
		dataManager.getTagTree(firstCall);
		verify(tagService).loadTagTree(remoteCallCaptor.capture());
		dataManager.getTagNode(ID, secondCall);
		verifyNoMoreInteractions(tagService);
		dataManager.getTagTree(thirdCall);
		verifyNoMoreInteractions(tagService);
		AsyncCallback<List<TagNode>> callback = remoteCallCaptor.getValue();
		List<TagNode> result = new ArrayList<TagNode>(); 
		result.add(tagNode);
		callback.onSuccess(result);
		verify(firstCall).onSuccess(result);
		verify(secondCall).onSuccess(tagNode);
		verify(thirdCall).onSuccess(result);
		
	}
	
	public void testThreeCallsWithWrongId() {
		dataManager.getTagTree(firstCall);
		verify(tagService).loadTagTree(remoteCallCaptor.capture());
		dataManager.getTagNode(WRONG_ID, secondCall);
		verifyNoMoreInteractions(tagService);
		dataManager.getTagTree(thirdCall);
		verifyNoMoreInteractions(tagService);
		AsyncCallback<List<TagNode>> callback = remoteCallCaptor.getValue();
		List<TagNode> result = new ArrayList<TagNode>(); 
		result.add(tagNode);
		callback.onSuccess(result);
		verify(firstCall).onSuccess(result);
		verify(secondCall).onSuccess(null);
		verify(thirdCall).onSuccess(result);
	}
	
	public void testThreeFirstWithWrongId() {
		dataManager.getTagNode(WRONG_ID, secondCall);
		verify(tagService).loadTagTree(remoteCallCaptor.capture());
		
		dataManager.getTagTree(firstCall);
		
		verifyNoMoreInteractions(tagService);
		dataManager.getTagTree(thirdCall);
		verifyNoMoreInteractions(tagService);
		AsyncCallback<List<TagNode>> callback = remoteCallCaptor.getValue();
		List<TagNode> result = new ArrayList<TagNode>(); 
		result.add(tagNode);
		callback.onSuccess(result);
		verify(firstCall).onSuccess(result);
		verify(secondCall).onSuccess(null);
		verify(thirdCall).onSuccess(result);
		
	}

	public void testOnceCalledbackNoMoreDelay() {
		dataManager.getTagTree(firstCall);
		
		verify(tagService).loadTagTree(remoteCallCaptor.capture());
		AsyncCallback<List<TagNode>> callback = remoteCallCaptor.getValue();
		List<TagNode> result = new ArrayList<TagNode>(); 
		result.add(tagNode);
		callback.onSuccess(result);
		verify(firstCall).onSuccess(result);
		
		dataManager.getTagNode(WRONG_ID, secondCall);
		verify(secondCall).onSuccess(null);
		verifyNoMoreInteractions(tagService);
		dataManager.getTagTree(thirdCall);
		verify(thirdCall).onSuccess(result);
		verifyNoMoreInteractions(tagService);
		
		
	}



}
