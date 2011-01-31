package fspotcloud.client.view;

import junit.framework.TestCase;

import com.google.gwt.user.client.ui.Widget;

import fspotcloud.client.data.DataManager;
import fspotcloud.client.data.DataManagerImpl;
import fspotcloud.client.data.IndexingUtil;
import fspotcloud.client.main.TagServiceAsyncTestImpl;

public class ImageActivityTest extends TestCase {

	
	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	protected ImageActivity create() {
		
		DataManager dataManager = new DataManagerImpl(new TagServiceAsyncTestImpl(), new IndexingUtil());
		ImageView imageViewMock = new ImageView() {

			@Override
			public void setImageUrl(String url) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void setPresenter(ImagePresenter presenter) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void setSlideshowButtonCaption(String caption) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public Widget asWidget() {
				// TODO Auto-generated method stub
				return null;
			}};
		ImageActivity imageActivity = new ImageActivity(imageViewMock, dataManager, null, null);
		return imageActivity;
	}
	


	public void testSetPlace() {
		ImageActivity imageActivity = create();
		
		ImageViewingPlace imageViewingPlace = new ImageViewingPlace("1", "1");
		imageActivity.setPlace(imageViewingPlace);
		int offset = imageActivity.offset;
		assertEquals(-1, offset);
	}

	public void testCanGoBackward() {
		
	}

	public void testCanGoForward() {
	
	}

	public void testGoBackward() {

	}

	public void testGoFirst() {

	}

	public void testGoForward() {

	}

	public void testGoLast() {

	}
	

}
