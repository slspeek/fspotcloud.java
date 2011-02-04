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

	Mockery context;

	@Override
	protected void setUp() throws Exception {
		context = new Mockery();
		super.setUp();
	}

	protected ImageActivity create(PlaceGoTo placeGoTo,
			ImageView imageView) {
		DataManager dataManager = new DataManagerImpl(
				new TagServiceAsyncTestImpl(), new IndexingUtil());
		dataManager.getTagTree(new AsyncCallback<List<TagNode>>() {
			@Override
			public void onSuccess(List<TagNode> result) {
			}

			@Override
			public void onFailure(Throwable caught) {
			}
		});
		ImageActivity imageActivity = new ImageActivity(imageView, dataManager,
				placeGoTo, null);
		return imageActivity;
	}

	public void testGoFirst() {
		final PlaceGoTo goTo = context.mock(PlaceGoTo.class);
		final ImageView imageView = context.mock(ImageView.class);
		ImageViewingPlace imageViewingPlace = new ImageViewingPlace("1", "3");
		ImageActivity imageActivity = create(goTo, imageView);

		context.checking(new Expectations() {
			{
				oneOf(imageView).setImageUrl(with("/image?id=3"));
			}
		});
		imageActivity.setPlace(imageViewingPlace);
		context.assertIsSatisfied();

		context.checking(new Expectations() {
			{
				oneOf(goTo).goTo(with(new ImageViewingPlace("1", "1")));
			}
		});
		imageActivity.goFirst();
		context.assertIsSatisfied();
	}

	public void testGoForward() throws Exception {
		testGoForward("1", "1", "2");
		testGoForward("1", "2", "3");
		testGoForward("2", "4", "5");
		testGoForward("2", "4", "5");
		testGoForward("5", "21", "22");
	}

	private void testGoForward(final String tagId, final String photoId,
			final String nextPhotoId) throws Exception {
		setUp();
		final PlaceGoTo goTo = context.mock(PlaceGoTo.class);
		final ImageView imageView = context.mock(ImageView.class);
		context.checking(new Expectations() {
			{
				oneOf(imageView).setImageUrl(with("/image?id=" + photoId));
			}
		});
		ImageViewingPlace imageViewingPlace = new ImageViewingPlace(tagId,
				photoId);
		ImageActivity imageActivity = create(goTo, imageView);
		imageActivity.setPlace(imageViewingPlace);

		context.assertIsSatisfied();
		context.checking(new Expectations() {
			{
				oneOf(goTo).goTo(
						with(new ImageViewingPlace(tagId, nextPhotoId)));

			}
		});
		imageActivity.goForward();
		context.assertIsSatisfied();
	}

	public void testGoBackward() {
		testCanGoBackward("2", "5", "4");
	}
	private void testCanGoBackward(final String tagId, final String photoId,
			final String previousPhotoId) {
		final PlaceGoTo goTo = context.mock(PlaceGoTo.class);
		final ImageView imageView = context.mock(ImageView.class);
		context.checking(new Expectations() {
			{
				oneOf(imageView).setImageUrl(with("/image?id=" + photoId));
			}
		});

		ImageViewingPlace middle = new ImageViewingPlace(tagId, photoId);
		ImageActivity imageActivity = create(goTo, imageView);
		imageActivity.setPlace(middle);
		
		context.assertIsSatisfied();
		context.checking(new Expectations() {
			{
				oneOf(goTo).goTo(
						with(new ImageViewingPlace(tagId, previousPhotoId)));
			}
		});
		assertEquals(true, imageActivity.canGoBackward());
		imageActivity.goBackward();
		context.assertIsSatisfied();
	}

	public void testNotCanGoBackward() {
		final PlaceGoTo goTo = context.mock(PlaceGoTo.class);
		final ImageView imageView = context.mock(ImageView.class);
		context.checking(new Expectations() {
			{

			}
		});
		ImageViewingPlace start = new ImageViewingPlace("1", "1");
		ImageActivity imageActivity = create(goTo, imageView);
		assertEquals(false, imageActivity.canGoBackward());
		imageActivity.goBackward();
		context.assertIsSatisfied();
	}

}
