package fspotcloud.client.main.view;

import junit.framework.TestCase;

import org.jmock.Expectations;
import org.jmock.Mockery;

import com.google.gwt.view.client.SingleSelectionModel;
import com.google.gwt.view.client.TreeViewModel;

import fspotcloud.client.data.DataManager;
import fspotcloud.client.data.DataManagerImpl;
import fspotcloud.client.main.TagServiceAsyncTestImpl;
import fspotcloud.client.main.view.api.TreeSelectionHandlerInterface;
import fspotcloud.client.main.view.api.TreeView;
import fspotcloud.shared.tag.TagNode;

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
