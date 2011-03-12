package fspotcloud.client.view;

import junit.framework.TestCase;

import org.jmock.Expectations;
import org.jmock.Mockery;

import fspotcloud.client.view.PagerView.PagerPresenter;

public class ImageActivityTest extends TestCase {

	Mockery context;

	@Override
	protected void setUp() throws Exception {
		context = new Mockery();
		super.setUp();
	}

	protected ImageActivity create(BasePlace place, ImageView imageView) {
		ImageActivity imageActivity = new ImageActivity(place, imageView);
		return imageActivity;
	}
	
	public void testImageActivitySetPlace() {
		final PagerView.PagerPresenter pager = context.mock(PagerPresenter.class);
		final ImageView imageView = context.mock(ImageView.class);
		final BasePlace place = new ImageViewingPlace("1", "1");
		context.checking(new Expectations() {
			{
				oneOf(imageView).setImageUrl(with("/image?id=1"));
				
			}
		});
		ImageActivity activity = create(place, imageView);
		activity.setImage();
		context.assertIsSatisfied();
	}
}
