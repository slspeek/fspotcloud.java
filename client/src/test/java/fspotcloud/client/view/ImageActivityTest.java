package fspotcloud.client.view;

import java.util.List;

import junit.framework.TestCase;

import org.jmock.Expectations;
import org.jmock.Mockery;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.rpc.AsyncCallback;

import fspotcloud.client.data.DataManager;
import fspotcloud.client.data.DataManagerImpl;
import fspotcloud.client.data.IndexingUtil;
import fspotcloud.client.main.TagServiceAsyncTestImpl;
import fspotcloud.client.view.PagerView.PagerPresenter;
import fspotcloud.shared.photo.PhotoInfoStore;
import fspotcloud.shared.tag.TagNode;

public class ImageActivityTest extends TestCase {

	Mockery context;

	@Override
	protected void setUp() throws Exception {
		context = new Mockery();
		super.setUp();
	}

	protected ImageActivity create(ImageView imageView, PagerPresenter pager) {
		ImageActivity imageActivity = new ImageActivity(imageView, null, null, null);
		return imageActivity;
	}
	
	public void testImageActivitySetPlace() {
		final PagerView.PagerPresenter pager = context.mock(PagerPresenter.class);
		final ImageView imageView = context.mock(ImageView.class);
		final BasePlace place = new ImageViewingPlace("1", "1");
		context.checking(new Expectations() {
			{
				//oneOf(imageView).getPagerViewContainer();
				
				oneOf(imageView).setImageUrl(with("/image?id=1"));
				//oneOf(pager).setData(with(any(PhotoInfoStore.class)));
				//oneOf(pager).setPlace(place);
			}
		});
		ImageView.ImagePresenter activity = create(imageView, pager);
		activity.setPlace(place);
		context.assertIsSatisfied();
	}
}
