
package fspotcloud.client.place;

import java.util.List;

import junit.framework.TestCase;

import org.jmock.Expectations;
import org.jmock.Mockery;

import com.google.common.collect.ImmutableList;
import com.google.gwt.user.client.rpc.AsyncCallback;

import fspotcloud.client.data.DataManager;
import fspotcloud.client.data.DataManagerImpl;
import fspotcloud.client.data.IndexingUtil;
import fspotcloud.client.main.TagServiceAsyncTestImpl;
import fspotcloud.client.place.BasePlace;
import fspotcloud.client.place.NavigatorImpl;
import fspotcloud.client.place.PlaceCalculator;
import fspotcloud.client.place.api.Navigator;
import fspotcloud.client.place.api.PlaceGoTo;
import fspotcloud.client.place.api.PlaceWhere;

public class NavigatorImplTest extends TestCase {

	DataManager dataManager = new DataManagerImpl(
			new TagServiceAsyncTestImpl(), new IndexingUtil());
	PlaceCalculator placeCalculator = new PlaceCalculator();
	Navigator navigator;
	Mockery context;
	BasePlace aute = new BasePlace("1", "2");
	BasePlace daniel = new BasePlace("1", "1");
	BasePlace danielRaster = new BasePlace("1", "1", 1, 2);
	BasePlace jan = new BasePlace("1", "3");
	BasePlace janRaster = new BasePlace("1", "3", 1, 2);
	BasePlace snowie = new BasePlace("4", "11");
	BasePlace siepie = new BasePlace("4", "12");
	BasePlace woefje = new BasePlace("5", "23",
			placeCalculator.getRasterWidth(), placeCalculator.getRasterHeight(), true);
	BasePlace r1_3 = new BasePlace("6", "101", 1, 3, false);
	BasePlace r1_3_zoomed_in = new BasePlace("6", "101", 1, 1, false);
	BasePlace r1_3next = new BasePlace("6", "103", 1, 3);

	@Override
	protected void setUp() throws Exception {
		context = new Mockery();
		super.setUp();
	}

	
	public Navigator get(PlaceGoTo goTo) {
		return new NavigatorImpl(null, goTo, placeCalculator, dataManager);
	}

	public Navigator get(PlaceWhere where, PlaceGoTo goTo) {
		return new NavigatorImpl(where, goTo, placeCalculator, dataManager);
	}

	public void testToggleButtonsVisible() {
		final PlaceGoTo goTo = context.mock(PlaceGoTo.class);
		final PlaceWhere where = context.mock(PlaceWhere.class);
		final BasePlace withButtons = new BasePlace("6", "101", 1, 1, false, true);
		final BasePlace withoutButtons = new BasePlace("6", "101", 1, 1, false, false);

		context.checking(new Expectations() {
			{
				oneOf(where).where();
				will(returnValue(withButtons));
				oneOf(goTo).goTo(with(withoutButtons));
			}
		});
		navigator = get(where, goTo);
		
		navigator.toggleButtonsVisible();
		context.assertIsSatisfied();
	}

	public void testGoLast() {
		final PlaceGoTo goTo = context.mock(PlaceGoTo.class);
		final PlaceWhere where = context.mock(PlaceWhere.class);
		context.checking(new Expectations() {
			{
				oneOf(where).where();
				will(returnValue(daniel));

				oneOf(goTo).goTo(with(jan));
			}
		});
		navigator = get(where, goTo);
		// 'false' means towards the end
		navigator.goEndAsync(false);
		context.assertIsSatisfied();
	}

	public void testGoLastWithoutPlace() {
		final PlaceGoTo goTo = context.mock(PlaceGoTo.class);
		final PlaceWhere where = context.mock(PlaceWhere.class);
		context.checking(new Expectations() {
			{
				oneOf(where).where();
				will(returnValue(daniel));
				oneOf(goTo).goTo(with(jan));
			}
		});
		navigator = get(where, goTo);
		// 'false' means towards the end
		navigator.goEndAsync(false);
		context.assertIsSatisfied();
	}

	public void testGoFirst() {
		final PlaceGoTo goTo = context.mock(PlaceGoTo.class);
		final PlaceWhere where = context.mock(PlaceWhere.class);
		context.checking(new Expectations() {
			{
				oneOf(where).where();
				will(returnValue(jan));
				oneOf(goTo).goTo(with(daniel));
			}
		});
		navigator = get(where, goTo);
		navigator.goEndAsync(true);
		context.assertIsSatisfied();
	}

	public void testGoForwardInRaster() throws InterruptedException {
		final PlaceGoTo goTo = context.mock(PlaceGoTo.class);
		final PlaceWhere where = context.mock(PlaceWhere.class);
		context.checking(new Expectations() {
			{
				oneOf(where).where();
				will(returnValue(danielRaster));

				oneOf(goTo).goTo(with(janRaster));
			}
		});
		navigator = get(where, goTo);
		navigator.goAsync(true);
		context.assertIsSatisfied();
	}

	public void testGoForwardInRasterHarder() throws InterruptedException {
		final PlaceGoTo goTo = context.mock(PlaceGoTo.class);
		final PlaceWhere where = context.mock(PlaceWhere.class);
		context.checking(new Expectations() {
			{
				oneOf(where).where();
				will(returnValue(r1_3));
				oneOf(goTo).goTo(with(r1_3next));
			}
		});
		navigator = get(where, goTo);
		navigator.goAsync(true);
		context.assertIsSatisfied();
	}

	public void testGoForward() throws InterruptedException {
		final PlaceGoTo goTo = context.mock(PlaceGoTo.class);
		final PlaceWhere where = context.mock(PlaceWhere.class);
		context.checking(new Expectations() {
			{
				oneOf(where).where();
				will(returnValue(snowie));
				oneOf(goTo).goTo(with(siepie));
			}
		});
		navigator = get(where, goTo);
		navigator.goAsync(true);
		context.assertIsSatisfied();
	}

	public void testGoBackward() {
		final PlaceGoTo goTo = context.mock(PlaceGoTo.class);
		final PlaceWhere where = context.mock(PlaceWhere.class);
		context.checking(new Expectations() {
			{
				oneOf(where).where();
				will(returnValue(siepie));
				oneOf(goTo).goTo(with(snowie));
			}
		});
		navigator = get(where, goTo);
		navigator.goAsync(false);
		context.assertIsSatisfied();
	}

	public void testCannotGo() {
		final BasePlace origin = new BasePlace("5", "23");// last in its tag
		final PlaceWhere where = context.mock(PlaceWhere.class);
		final PlaceGoTo goTo = context.mock(PlaceGoTo.class);
		context.checking(new Expectations() {
			{
				oneOf(where).where();
				will(returnValue(origin));

			}
		});
		navigator = get(where, goTo);
		navigator.goAsync(true);
		context.assertIsSatisfied();
	}

	public void testGetPageCount() {
		final AsyncCallback<Integer> result = context.mock(AsyncCallback.class);

		context.checking(new Expectations() {
			{
				oneOf(result).onSuccess(with(2));
			}
		});
		navigator = get(null);
		navigator.getPageCountAsync("1", 2, result);
		context.assertIsSatisfied();
	}

	public void testGetPageStringIntIntAsyncCallbackOfListOfBasePlace1() {
		final AsyncCallback<List<BasePlace>> result = context
				.mock(AsyncCallback.class);
		final ImmutableList<BasePlace> expected = ImmutableList
				.of(daniel, aute);
		context.checking(new Expectations() {
			{
				oneOf(result).onSuccess(expected);
			}
		});
		navigator = get(null);
		navigator.getPageAsync("1", 2, 0, result);
		context.assertIsSatisfied();
	}

	public void testGetPageStringIntIntAsyncCallbackOfListOfBasePlace2() {
		final AsyncCallback<List<BasePlace>> result = context
				.mock(AsyncCallback.class);
		final ImmutableList<BasePlace> expected = ImmutableList.of(jan);
		context.checking(new Expectations() {
			{
				oneOf(result).onSuccess(expected);
			}
		});
		navigator = get(null);
		navigator.getPageAsync("1", 2, 1, result);
		context.assertIsSatisfied();
	}

	public void testGetPageStringStringIntAsyncCallbackOfListOfBasePlace1() {
		final AsyncCallback<List<BasePlace>> result = context
				.mock(AsyncCallback.class);
		final ImmutableList<BasePlace> expected = ImmutableList
				.of(daniel, aute);
		context.checking(new Expectations() {
			{
				oneOf(result).onSuccess(expected);
			}
		});
		navigator = get(null);
		navigator.getPageAsync("1", "2", 2, result);
		context.assertIsSatisfied();
	}

	public void testGetPageStringStringIntAsyncCallbackOfListOfBasePlace2() {
		final AsyncCallback<List<BasePlace>> result = context
				.mock(AsyncCallback.class);
		final ImmutableList<BasePlace> expected = ImmutableList
				.of(daniel, aute);
		context.checking(new Expectations() {
			{
				oneOf(result).onSuccess(expected);
			}
		});
		navigator = get(null);
		navigator.getPageAsync("1", "1", 2, result);
		context.assertIsSatisfied();
	}

	public void testGetPageStringStringIntAsyncCallbackOfListOfBasePlace3() {
		final AsyncCallback<List<BasePlace>> result = context
				.mock(AsyncCallback.class);
		final ImmutableList<BasePlace> expected = ImmutableList.of(jan);
		context.checking(new Expectations() {
			{
				oneOf(result).onSuccess(expected);
			}
		});
		navigator = get(null);
		navigator.getPageAsync("1", "3", 2, result);
		context.assertIsSatisfied();
	}

	public void testToggleZoomView() {
		final PlaceGoTo goTo = context.mock(PlaceGoTo.class);
		final PlaceWhere where = context.mock(PlaceWhere.class);
		context.checking(new Expectations() {
			{
				oneOf(where).where();
				will(returnValue(r1_3));
				oneOf(goTo).goTo(with(r1_3_zoomed_in));
			}
		});
		navigator = get(where, goTo);
		navigator.toggleZoomViewAsync(r1_3.getTagId(), r1_3.getPhotoId());
		context.assertIsSatisfied();
	}

	public void testGoToLatestTag() {
		final PlaceGoTo goTo = context.mock(PlaceGoTo.class);
		context.checking(new Expectations() {
			{
				oneOf(goTo).goTo(with(woefje));
			}
		});
		navigator = get(goTo);
		navigator.goToLatestTag();
		context.assertIsSatisfied();

	}

}
