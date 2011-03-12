package fspotcloud.client.main.data;

import java.util.List;
import java.util.logging.Logger;

import junit.framework.TestCase;

import com.google.gwt.user.client.rpc.AsyncCallback;

import fspotcloud.client.data.DataManagerImpl;
import fspotcloud.client.data.IndexingUtil;
import fspotcloud.client.main.TagServiceAsyncTestImpl;
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
	
	private TagNode cats;
	public void testCatsIs2() {
		//final TagNode cats; 
		dataManager.getTagNode("2", new AsyncCallback<TagNode>() {

			@Override
			public void onFailure(Throwable arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(TagNode arg0) {
				cats = arg0;
				log.info(Thread.currentThread().getName());
			}
		});
		assertEquals("Cats", cats.getTagName());
	}

}
