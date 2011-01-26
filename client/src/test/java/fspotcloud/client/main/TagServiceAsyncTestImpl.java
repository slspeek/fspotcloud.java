package fspotcloud.client.main;

import java.util.List;

import com.google.common.collect.ImmutableList;
import com.google.gwt.user.client.rpc.AsyncCallback;

import fspotcloud.rpc.TagServiceAsync;
import fspotcloud.shared.tag.TagNode;

public class TagServiceAsyncTestImpl implements TagServiceAsync {

	private List<TagNode> tagTreeData;

	public TagServiceAsyncTestImpl() {
		initData();
	}
	
	private static TagNode createNode(String id, String name, int count) {
		TagNode node = new TagNode();
		node.setId(id);
		node.setTagName(name);
		node.setCount(count);
		return node;
	}
	
	private void initData() {
		TagNode root1 = createNode("1", "Friends", 10);
		TagNode root2 = createNode("2", "Cats", 10);
		TagNode root3 = createNode("3", "Languages", 10);
		
		TagNode felix = createNode("4", "Felix", 2);
		TagNode woefje = createNode("5", "Woefje", 1);
		root2.addChild(felix);
		root2.addChild(woefje);
		tagTreeData = ImmutableList.of(root1, root2, root3);
	}
	
	@Override
	public void keysForTag(String tagId, AsyncCallback<List<String>> callback) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void loadTagTree(AsyncCallback<List<TagNode>> callback) {
		callback.onSuccess(tagTreeData);
	}

}
