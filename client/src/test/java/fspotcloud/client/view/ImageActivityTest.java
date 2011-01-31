package fspotcloud.client.view;

import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;

import junit.framework.TestCase;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;

import fspotcloud.client.data.DataManager;
import fspotcloud.client.data.DataManagerImpl;
import fspotcloud.client.data.IndexingUtil;
import fspotcloud.client.main.TagServiceAsyncTestImpl;
import fspotcloud.shared.tag.TagNode;

public class ImageActivityTest extends TestCase {

	
	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	Mockery context = new Mockery();
	
	protected ImageActivity create(PlaceGoTo placeGoTo, ImageViewingPlace place) {
		
		DataManager dataManager = new DataManagerImpl(new TagServiceAsyncTestImpl(), new IndexingUtil());
		dataManager.getTagTree(new AsyncCallback<List<TagNode>>() {
			@Override
			public void onSuccess(List<TagNode> result) {}
			@Override
			public void onFailure(Throwable caught) {}
		});
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
		ImageActivity imageActivity = new ImageActivity(imageViewMock, dataManager, placeGoTo, null);
		imageActivity.setPlace(place);
		return imageActivity;
	}
	
	public void testGoFirst() {
		final PlaceGoTo goTo = context.mock(PlaceGoTo.class);
		context.checking(new Expectations() {
			{
				oneOf(goTo).goTo(with(new ImageViewingPlace("1", "1")));
			}
		});
		ImageViewingPlace imageViewingPlace = new ImageViewingPlace("1", "3");
		ImageActivity imageActivity = create(goTo, imageViewingPlace);
		imageActivity.goFirst();
		context.assertIsSatisfied();
	}
	

	public void testGoForward() {
		final PlaceGoTo goTo = context.mock(PlaceGoTo.class);
		context.checking(new Expectations() {
			{
				oneOf(goTo).goTo(with(new ImageViewingPlace("1", "2")));
			}
		});
		ImageViewingPlace imageViewingPlace = new ImageViewingPlace("1", "1");
		ImageActivity imageActivity = create(goTo, imageViewingPlace);
		int offset = imageActivity.offset;
		assertEquals(0, offset);
		imageActivity.goForward();
		context.assertIsSatisfied();
	}
	
	public void testCanGoBackward() {
		final PlaceGoTo goTo = context.mock(PlaceGoTo.class);
		context.checking(new Expectations() {
			{
				
			}
		});
		ImageViewingPlace middle = new ImageViewingPlace("1", "2");
		ImageActivity imageActivity = create(goTo, middle);
		assertEquals(true, imageActivity.canGoBackward());
	}
	
	public void testNotCanGoBackward() {
		final PlaceGoTo goTo = context.mock(PlaceGoTo.class);
		context.checking(new Expectations() {
			{
				
			}
		});
		ImageViewingPlace start = new ImageViewingPlace("1", "1");
		ImageActivity imageActivity = create(goTo, start);
		assertEquals(false, imageActivity.canGoBackward());
	}

}
