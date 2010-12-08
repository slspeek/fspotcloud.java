package fspotcloud.client.main;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.view.client.SingleSelectionModel;

import fspotcloud.shared.tag.TagNode;

public class TagActivity extends AbstractActivity implements
		TagView.TagPresenter {
	EventBus eventBus;
	ClientFactory clientFactory;
	TagView tagView;
	private String tagId;

	public TagActivity(TagPlace place, ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
		this.tagView = clientFactory.getTagView();
		this.tagId = place.getTagId();
	}

	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
		this.eventBus = eventBus;
		tagView.setPresenter(this);
		TagNode tag = getTagNode();
		if (tag != null) {
			List<String> imageURIS = makeImageURIS(tag.getCachedPhotoList());
			tagView.setImageList(imageURIS);
		}
		containerWidget.setWidget(tagView);
		TreeItem item = findTagId(tagId);
		item.setSelected(true);
	}

	private TagNode getTagNode() {
		TreeItem model = tagView.getTreeModel();
		if (model != null) {
			return findTagNode(tagId);
		}
		return null;
	}

	private List<String> makeImageURIS(List<String> ids) {
		List<String> result = new ArrayList<String>();
		for (String id : ids) {
			result.add("/image?id=" + id);
		}
		return result;
	}

	private void requestTagTreeData() {
		clientFactory.getDataManager().getTagTree(
				new AsyncCallback<List<TagNode>>() {
					@Override
					public void onFailure(Throwable caught) {

					}

					@Override
					public void onSuccess(List<TagNode> result) {
						TreeItem treeModel = build(result);
						clientFactory.getTagView().setTreeModel(treeModel);
					}
				});
	}

	private TreeItem build(List<TagNode> tagTrees) {
		TreeItem root = new TreeItem("");
		attach(root, tagTrees);
		return root;
	}

	private void attach(TreeItem item, List<TagNode> children) {
		for (TagNode child : children) {
			TreeItem newChild = item.addItem(child.getTagName());
			newChild.setUserObject(child);
			attach(newChild, child.getChildren());
		}
	}

	@Override
	public void treeSelectionChanged(SelectionEvent<TreeItem> event) {
		TreeItem selectedItem = event.getSelectedItem();
		if (selectedItem != null) {
			TagNode selectedTag = (TagNode) selectedItem.getUserObject();
			String tagId = selectedTag.getId();
			TagPlace newPlace = new TagPlace(tagId);
			clientFactory.getPlaceController().goTo(newPlace);
		}
	}

	@Override
	public void reloadTree() {
		requestTagTreeData();
	}

	private TagNode findTagNode(String tagId) {
		TreeItem tagItem = findTagId(tagId);
		return (TagNode) tagItem.getUserObject();
	}

	private TreeItem findTagId(String tagId) {
		TreeItem root = tagView.getTreeModel();
		TreeItem itemToBeSelected = findTagIdUnder(tagId, root);
		return itemToBeSelected;
	}

	private TreeItem findTagIdUnder(String tagId, TreeItem root) {
		for (int index = 0; index < root.getChildCount(); index++) {
			TreeItem item = root.getChild(index);
			TagNode tagNode = (TagNode) item.getUserObject();
			if (tagId.equals(tagNode.getId())) {
				return item;
			} else {
				return findTagIdUnder(tagId, item);
			}
		}
		return null;
	}
}
