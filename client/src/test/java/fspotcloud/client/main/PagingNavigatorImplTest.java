package fspotcloud.client.main;

import java.util.List;

import junit.framework.TestCase;

import org.jmock.Expectations;
import org.jmock.Mockery;

import com.google.common.collect.ImmutableList;
import com.google.gwt.user.client.rpc.AsyncCallback;

import fspotcloud.client.data.DataManager;
import fspotcloud.client.data.DataManagerImpl;
import fspotcloud.client.data.IndexingUtil;
import fspotcloud.client.place.BasePlace;

public class PagingNavigatorImplTest extends TestCase {

	DataManager dataManager = new DataManagerImpl(
			new TagServiceAsyncTestImpl(), new IndexingUtil());
	PagingNavigator pager = new PagingNavigatorImpl(dataManager);
	Mockery context;
	BasePlace daniel = new BasePlace("1", "1");
	BasePlace jan = new BasePlace("1", "3");
	BasePlace snowie = new BasePlace("4", "11");
	BasePlace siepie = new BasePlace("4", "12");
	BasePlace aute = new BasePlace("1", "2");

	@Override
	protected void setUp() throws Exception {
		context = new Mockery();
		super.setUp();
	}

	public void testGetPageCount() {
		final AsyncCallback<Integer> result = context.mock(AsyncCallback.class);
		
		context.checking(new Expectations() {
			{
				oneOf(result).onSuccess(with(2));
			}
		});
		pager.getPageCount("1",2, result);
		context.assertIsSatisfied();
	}

	public void testGetPageStringIntIntAsyncCallbackOfListOfBasePlace1() {
		final AsyncCallback<List<BasePlace>> result = context.mock(AsyncCallback.class);
		final ImmutableList<BasePlace> expected = ImmutableList.of(daniel, aute);
		context.checking(new Expectations() {
			{
				oneOf(result).onSuccess(expected);
			}
		});
		pager.getPage("1", 2, 0, result);
		context.assertIsSatisfied();
	}
	
	public void testGetPageStringIntIntAsyncCallbackOfListOfBasePlace2() {
		final AsyncCallback<List<BasePlace>> result = context.mock(AsyncCallback.class);
		final ImmutableList<BasePlace> expected = ImmutableList.of(jan);
		context.checking(new Expectations() {
			{
				oneOf(result).onSuccess(expected);
			}
		});
		pager.getPage("1", 2, 1, result);
		context.assertIsSatisfied();
	}

	public void testGetPageStringStringIntAsyncCallbackOfListOfBasePlace1() {
		final AsyncCallback<List<BasePlace>> result = context.mock(AsyncCallback.class);
		final ImmutableList<BasePlace> expected = ImmutableList.of(daniel, aute);
		context.checking(new Expectations() {
			{
				oneOf(result).onSuccess(expected);
			}
		});
		pager.getPage("1", "2", 2, result);
		context.assertIsSatisfied();
	}

	public void testGetPageStringStringIntAsyncCallbackOfListOfBasePlace2() {
		final AsyncCallback<List<BasePlace>> result = context.mock(AsyncCallback.class);
		final ImmutableList<BasePlace> expected = ImmutableList.of(daniel, aute);
		context.checking(new Expectations() {
			{
				oneOf(result).onSuccess(expected);
			}
		});
		pager.getPage("1", "1", 2, result);
		context.assertIsSatisfied();
	}
	public void testGetPageStringStringIntAsyncCallbackOfListOfBasePlace3() {
		final AsyncCallback<List<BasePlace>> result = context.mock(AsyncCallback.class);
		final ImmutableList<BasePlace> expected = ImmutableList.of(jan);
		context.checking(new Expectations() {
			{
				oneOf(result).onSuccess(expected);
			}
		});
		pager.getPage("1", "3", 2, result);
		context.assertIsSatisfied();
	}
	
}
