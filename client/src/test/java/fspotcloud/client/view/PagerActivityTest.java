package fspotcloud.client.view;

import java.util.Date;
import java.util.SortedSet;
import java.util.TreeSet;

import junit.framework.TestCase;

import org.jmock.Expectations;
import org.jmock.Mockery;

import fspotcloud.client.view.PagerView.PagerPresenter;
import fspotcloud.shared.photo.PhotoInfo;
import fspotcloud.shared.photo.PhotoInfoStore;

public class PagerActivityTest extends TestCase {

	Mockery context;
	PhotoInfoStore data;

	@Override
	protected void setUp() throws Exception {
		context = new Mockery();
		SortedSet<PhotoInfo> set = new TreeSet<PhotoInfo>();
		set.add(new PhotoInfo("5", "Me", new Date(0)));
		set.add(new PhotoInfo("4", "John", new Date(1)));
		set.add(new PhotoInfo("3", "Mary", new Date(2)));
		set.add(new PhotoInfo("2", "Pete", new Date(4)));
		data = new PhotoInfoStore(set);
		super.setUp();
	}
	
	public void testGoFirst() {
		final PlaceGoTo goTo = context.mock(PlaceGoTo.class);
		ImageViewingPlace imageViewingPlace = new ImageViewingPlace("1", "3");
		PagerView.PagerPresenter pager = new PagerActivity(goTo, null);
		pager.setData(data);
		pager.setPlace(imageViewingPlace);
		context.checking(new Expectations() {
			{
				oneOf(goTo).goTo(with(new ImageViewingPlace("1", "5")));
			}
		});
		pager.goFirst();
		context.assertIsSatisfied();
	}

	public void testGoForward() throws Exception {
		testGoForward("1", "5", "4");
	}

	public void testGoForward2() throws Exception {

		testGoForward("1", "4", "3");
	}

	private void testGoForward(final String tagId, final String photoId,
			final String nextPhotoId) throws Exception {
		setUp();
		final PlaceGoTo goTo = context.mock(PlaceGoTo.class);
		ImageViewingPlace imageViewingPlace = new ImageViewingPlace(tagId,
				photoId);
		PagerPresenter pager = new PagerActivity(goTo, null);
		context.checking(new Expectations() {
			{
				oneOf(goTo).goTo(
						with(new ImageViewingPlace(tagId, nextPhotoId)));

			}
		});
		pager.setData(data);
		pager.setPlace(imageViewingPlace);
		pager.goForward();
		context.assertIsSatisfied();
	}

	
	private void testCanGoBackward(final String photoId,
			final String previousPhotoId) throws Exception {
	

		final PlaceGoTo goTo = context.mock(PlaceGoTo.class);
		ImageViewingPlace middle = new ImageViewingPlace("testTag", photoId);
		PagerPresenter pager = new PagerActivity(goTo, null);
		pager.setData(data);
		context.checking(new Expectations() {
			{
				oneOf(goTo).goTo(
						with(new ImageViewingPlace("testTag", previousPhotoId)));
			}
		});
		pager.setPlace(middle);
		
		assertEquals(true, pager.canGoBackward());
		pager.goBackward();
		context.assertIsSatisfied();
	}

	public void testGoBackward1() throws Exception {
		testCanGoBackward("4", "5");
		
	}
	
	public void testGoBackward2() throws Exception {
		testCanGoBackward("2", "3");
	}
	
		
	public void testGoBackward3() throws Exception {
		testCanGoBackward("3", "4");
	}

}
