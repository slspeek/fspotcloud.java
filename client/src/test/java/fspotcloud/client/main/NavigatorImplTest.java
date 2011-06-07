package fspotcloud.client.main;

import junit.framework.TestCase;

import org.jmock.Expectations;
import org.jmock.Mockery;

import fspotcloud.client.data.DataManager;
import fspotcloud.client.data.DataManagerImpl;
import fspotcloud.client.data.IndexingUtil;
import fspotcloud.client.place.BasePlace;
import fspotcloud.client.place.PlaceGoTo;
import fspotcloud.client.place.PlaceWhere;

public class NavigatorImplTest extends TestCase {

	
	DataManager dataManager = new DataManagerImpl(new TagServiceAsyncTestImpl(), new IndexingUtil());
	Navigator navigator;
	Mockery context;
	BasePlace daniel = new BasePlace("1", "1");
	BasePlace jan = new BasePlace("1", "3");
	BasePlace snowie = new BasePlace("4", "11");
	BasePlace siepie = new BasePlace("4", "12");

		
	@Override
	protected void setUp() throws Exception {
		context = new Mockery();
		super.setUp();
	}

	public Navigator get(PlaceGoTo goTo) {
		return new NavigatorImpl(null, goTo, dataManager);
	}
	
	public Navigator get(PlaceWhere where, PlaceGoTo goTo) {
		return new NavigatorImpl(where, goTo, dataManager);
	}
	
	public void testGoLast() {
		final PlaceGoTo goTo = context.mock(PlaceGoTo.class);
		context.checking(new Expectations() {
			{
				oneOf(goTo).goTo(with(jan));
			}
		});
		navigator = get(goTo);
		//'false' means towards the end
		navigator.goEnd(false, daniel);
		context.assertIsSatisfied();
	}
	
	public void testGoLastWithoutPlace() {
		final PlaceGoTo goTo = context.mock(PlaceGoTo.class);
		final PlaceWhere where = context.mock(PlaceWhere.class);
		context.checking(new Expectations() {
			{
				oneOf(where).where(); will(returnValue(daniel));
				oneOf(goTo).goTo(with(jan));
			}
		});
		navigator = get(where, goTo);
		//'false' means towards the end
		navigator.goEnd(false);
		context.assertIsSatisfied();
	}
	public void testGoFirst() {
		final PlaceGoTo goTo = context.mock(PlaceGoTo.class);
		context.checking(new Expectations() {
			{
				oneOf(goTo).goTo(with(daniel));
			}
		});
		navigator = get(goTo);
		navigator.goEnd(true, jan);
		context.assertIsSatisfied();
	}

	public void testGoForward() throws InterruptedException {
		final PlaceGoTo goTo = context.mock(PlaceGoTo.class);
		context.checking(new Expectations() {
			{
				oneOf(goTo).goTo(with(siepie));
			}
		});
		navigator = get(goTo);
		navigator.go(true, snowie);
		context.assertIsSatisfied();
	}

	public void testGoBackward() {
		final PlaceGoTo goTo = context.mock(PlaceGoTo.class);
		context.checking(new Expectations() {
			{
				oneOf(goTo).goTo(with(snowie));
			}
		});
		navigator = get(goTo);
		navigator.go(false, siepie);
		context.assertIsSatisfied();
	}
	
	public void testCannotGo() {
		final BasePlace origin = new BasePlace("5", "23");//last in its tag
		final PlaceGoTo goTo = context.mock(PlaceGoTo.class);
		context.checking(new Expectations() {
			{
			}
		});
		navigator = get(goTo);
		navigator.go(true, origin);
		context.assertIsSatisfied();
	}

}
