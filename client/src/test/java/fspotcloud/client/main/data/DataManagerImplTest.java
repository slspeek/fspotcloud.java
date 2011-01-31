package fspotcloud.client.main.data;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import fspotcloud.client.data.DataManagerImpl;
import fspotcloud.client.data.IndexingUtil;
import fspotcloud.client.main.TagServiceAsyncTestImpl;
import fspotcloud.shared.tag.TagNode;
import junit.framework.TestCase;

public class DataManagerImplTest extends TestCase {

	private DataManagerImpl dataManager;

	public DataManagerImplTest() {
		dataManager = new DataManagerImpl(
				new TagServiceAsyncTestImpl(), new IndexingUtil());
		testNonNull();
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
		TagNode cats = dataManager.getTagNode("2");
		assertEquals("Cats", cats.getTagName());
	}

}
