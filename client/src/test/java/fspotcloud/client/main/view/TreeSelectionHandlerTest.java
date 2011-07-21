package fspotcloud.client.main.view;

import junit.framework.TestCase;

import org.jmock.Mockery;

import com.google.gwt.view.client.SingleSelectionModel;

import fspotcloud.client.main.view.api.TreeSelectionHandlerInterface;
import fspotcloud.client.place.api.Navigator;
import fspotcloud.shared.tag.TagNode;

public class TreeSelectionHandlerTest extends TestCase {

	Mockery context;
	TreeSelectionHandlerInterface handler;
	SingleSelectionModel<TagNode> model;
	Navigator navigator;
	
	@Override
	protected void setUp() throws Exception {
		context = new Mockery();
		navigator = context.mock(Navigator.class);
		model = new SingleSelectionModel<TagNode>();
		super.setUp();
	}

	public void testTreeSelectionHandler() {
		handler = new TreeSelectionHandler(navigator);
		assertNotNull(handler);
	}

	public void testSetSelectionModel() {
		testTreeSelectionHandler();
		handler.setSelectionModel(model);
	}

}
