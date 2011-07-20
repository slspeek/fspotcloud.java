package fspotcloud.client.view;

import junit.framework.TestCase;

import org.jmock.Expectations;
import org.jmock.Mockery;

import com.google.gwt.event.shared.SimpleEventBus;

import fspotcloud.client.main.view.ImagePresenterImpl;
import fspotcloud.client.main.view.api.ImageView;
import fspotcloud.client.main.view.api.ImageView.ImagePresenter;
import fspotcloud.client.place.BasePlace;

public class ImageActivityTest extends TestCase {

	Mockery context;
	private ImagePresenter presenter;

	@Override
	protected void setUp() throws Exception {
		context = new Mockery();
		super.setUp();
	}

	protected ImagePresenterImpl create(BasePlace place, ImageView imageView) {
		ImagePresenterImpl imageActivity = new ImagePresenterImpl(0, 0, place,
				imageView, false, new SimpleEventBus());
		return imageActivity;
	}

	public void testImageActivitySetPlace() {
		final ImageView imageView = context.mock(ImageView.class);
		final BasePlace place = new BasePlace("1", "1");

		context.checking(new Expectations() {

			{
				oneOf(imageView).setMaxWidth(with(0));
				oneOf(imageView).setMaxHeight(with(0));
				oneOf(imageView).setImageUrl(with("/image?id=1"));
			}
		});
		presenter = create(place, imageView);
		context.checking(new Expectations() {

			{
				oneOf(imageView).setPresenter(with(presenter));
			}
		});
		presenter.init();
		context.assertIsSatisfied();
	}
}
