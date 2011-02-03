package fspotcloud.client.view;

import java.util.List;

import com.google.gwt.view.client.TreeViewModel.NodeInfo;

import junit.framework.TestCase;
import fspotcloud.client.main.TagServiceAsyncTestImpl;
import fspotcloud.shared.tag.TagNode;

public class TagTreeModelTest extends TestCase {

	public List<TagNode> data;
	
	private TagTreeModel model;
	
	public TagTreeModelTest() {
		TagServiceAsyncTestImpl service = new TagServiceAsyncTestImpl();
		data = service.initData();
	}
	protected void setUp() throws Exception {
		super.setUp();
		model = new TagTreeModel(data, null);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testGetNodeInfo() {
		NodeInfo<TagNode> roots = (NodeInfo<TagNode>) model.getNodeInfo(null);
		
	}

	public void testIsLeaf() {
		assertFalse(model.isLeaf(null));
	}

}
