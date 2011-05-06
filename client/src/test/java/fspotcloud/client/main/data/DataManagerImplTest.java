package fspotcloud.client.main.data;

import java.util.List;
import java.util.logging.Logger;

import junit.framework.TestCase;

import com.google.gwt.user.client.rpc.AsyncCallback;

import fspotcloud.client.data.DataManagerImpl;
import fspotcloud.client.data.IndexingUtil;
import fspotcloud.client.main.TagServiceAsyncTestImpl;
import fspotcloud.shared.photo.PhotoInfoStore;
import fspotcloud.shared.tag.TagNode;

public class DataManagerImplTest extends TestCase {

	private static final Logger log = Logger.getLogger(DataManagerImplTest.class
			.getName());
	private DataManagerImpl dataManager;

	public DataManagerImplTest() {
		dataManager = new DataManagerImpl(
				new TagServiceAsyncTestImpl(), new IndexingUtil());
		//testNonNull();
	}
	
	public void testNonNull() {
		dataManager.getTagTree(new AsyncCallback<List<TagNode>>() {
			
			@Override
			public void onSuccess(List<TagNode> result) {
				assertNotNull(result);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				fail();
			}
		});
	 
	}
	
	public void testCatsIs2() {
		dataManager.getTagNode("2", new AsyncCallback<TagNode>() {

			@Override
			public void onFailure(Throwable arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(TagNode cats) {
				assertEquals("Cats", cats.getTagName());
				
				PhotoInfoStore store = cats.getCachedPhotoList();
				assertEquals(2, store.lastIndex());
				
			}
		});
		
	}
	
	public void testFriendsIs1() {
		dataManager.getTagNode("1", new AsyncCallback<TagNode>() {

			@Override
			public void onFailure(Throwable arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(TagNode friends) {
				assertEquals("Friends", friends.getTagName());
				
				PhotoInfoStore store = friends.getCachedPhotoList();
				assertEquals(2, store.lastIndex());
				
			}
		});
		
	}
	public void testLangauagesIs3() {
		dataManager.getTagNode("3", new AsyncCallback<TagNode>() {

			@Override
			public void onFailure(Throwable arg0) {
			}

			@Override
			public void onSuccess(TagNode friends) {
				assertEquals("Languages", friends.getTagName());
				PhotoInfoStore store = friends.getCachedPhotoList();
				assertEquals(3, store.lastIndex());
				
			}
		});
		
	}
	

}
