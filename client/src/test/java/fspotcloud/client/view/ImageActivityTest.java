package fspotcloud.client.view;

import junit.framework.TestCase;

import org.jmock.Expectations;
import org.jmock.Mockery;

import com.google.gwt.event.shared.SimpleEventBus;

import fspotcloud.client.main.view.ImagePresenterImpl;
import fspotcloud.client.main.view.api.ImageView;
import fspotcloud.client.main.view.api.PagerView;
import fspotcloud.client.main.view.api.PagerView.PagerPresenter;
import fspotcloud.client.place.BasePlace;

public class ImageActivityTest extends TestCase {

	Mockery context;

	@Override
	protected void setUp() throws Exception {
		context = new Mockery();
		super.setUp();
	}

	protected ImagePresenterImpl create(BasePlace place, ImageView imageView) {
		ImagePresenterImpl imageActivity = new ImagePresenterImpl(0, 0,place, imageView, false, new SimpleEventBus());
		return imageActivity;
	}
	
	public void testImageActivitySetPlace() {
		final PagerView.PagerPresenter pager = context.mock(PagerPresenter.class);
		final ImageView imageView = context.mock(ImageView.class);
		final BasePlace place = new BasePlace("1", "1");
		context.checking(new Expectations() {
			{
				oneOf(imageView).setImageUrl(with("/image?id=1"));
				
			}
		});
		ImagePresenterImpl activity = create(place, imageView);
		activity.setImage();
		context.assertIsSatisfied();
	}
}
