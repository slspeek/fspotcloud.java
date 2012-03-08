package com.googlecode.fspotcloud.client.main.view;

import junit.framework.TestCase;

import org.jmock.Expectations;
import org.jmock.Mockery;

import com.google.gwt.view.client.SingleSelectionModel;
import com.google.gwt.view.client.TreeViewModel;

import com.googlecode.fspotcloud.client.data.DataManager;
import com.googlecode.fspotcloud.client.data.DataManagerImpl;
import com.googlecode.fspotcloud.client.main.TagServiceAsyncTestImpl;
import com.googlecode.fspotcloud.client.main.view.api.TreeSelectionHandlerInterface;
import com.googlecode.fspotcloud.client.main.view.api.TreeView;
import com.googlecode.fspotcloud.shared.tag.TagNode;

public class TreePresenterImplTest extends TestCase {

	Mockery context;
	TreeView.TreePresenter presenter;
	TreeSelectionHandlerInterface handler;
	TreeView treeView;
	DataManager dataManager = new DataManagerImpl(
			new TagServiceAsyncTestImpl());
	SingleSelectionModel<TagNode> model = new SingleSelectionModel<TagNode>();

	@Override
	protected void setUp() throws Exception {
		context = new Mockery();
		handler = context.mock(TreeSelectionHandlerInterface.class);
		treeView = context.mock(TreeView.class);
		super.setUp();
	}

	public void testConstructor() {
		presenter = new TreePresenterImpl(treeView, dataManager, model, handler);
		assertNotNull(presenter);
	}

	public void testInit() {
		testConstructor();
		context.checking(new Expectations() {

			{
				oneOf(handler).setSelectionModel(with(model));
				oneOf(treeView).setTreeModel(with(any(TreeViewModel.class)));
			}
		});
		presenter.init();
		context.assertIsSatisfied();
	}

//	public void testSetPlace() {
//		testConstructor();
//		BasePlace place = new BasePlace("1", "1");
//		presenter.setPlace(place);
//	}

}
