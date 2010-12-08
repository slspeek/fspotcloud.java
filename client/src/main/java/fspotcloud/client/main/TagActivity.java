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
	String tagId;
	String photoId;
	Integer offset = null;
	List<String> photoList = new ArrayList<String>();
	EventBus eventBus;
	ClientFactory clientFactory;
	TagView tagView;
	

	public TagActivity(TagPlace place, ClientFactory clientFactory) {
		tagId = place.getTagId();
		photoId = place.getPhotoId();
		if (photoId != null) {
			offset = photoList.indexOf(photoId);
		}
		this.clientFactory = clientFactory;
		this.tagView = clientFactory.getTagView();
		requestKeysForTag(tagId);
	}

	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
		this.eventBus = eventBus;
		tagView.setPresenter(this);
		if (photoId != null) {
			tagView.setMainImageUrl("/image?id=" + photoId);
		}
		tagView.setTagId(tagId);
		containerWidget.setWidget(tagView);
		TreeItem item = findTagId(tagId);
		item.setSelected(true);
		//requestTagTreeData();
	}

	@Override
/*	public boolean canGoBackward() {
		if (offset != null) {
			return offset > 0;
		} else {
			return false;
		}
	}
*/
	
	public boolean canGoBackward() {
			return true;
	}

	@Override
	/*public boolean canGoForward() {
		if (offset != null) {
			return offset >= 0 && offset < photoList.size() - 1;
		} else {
			return false;
		}
	}
*/

	public boolean canGoForward() {
			return true;
	}
@Override
	public void goBackward() {
		if (!photoList.isEmpty() && canGoBackward()) {
			clientFactory.getPlaceController().goTo(
					new TagPlace(tagId, photoList.get(offset - 1)));
		}
	}

	@Override
	public void goFirst() {
		if (!photoList.isEmpty()) {
			clientFactory.getPlaceController().goTo(
					new TagPlace(tagId, photoList.get(0)));
		}
	}

	@Override
	public void goForward() {
		if (!photoList.isEmpty() && canGoForward()) {
			clientFactory.getPlaceController().goTo(
					new TagPlace(tagId, photoList.get(offset + 1)));
		}
	}

	@Override
	public void goLast() {
		if (!photoList.isEmpty()) {
			clientFactory.getPlaceController().goTo(
					new TagPlace(tagId, photoList.get(photoList.size() - 1)));
		}
	}

	private void requestKeysForTag(final String tagId) {
		clientFactory.getDataManager().getPhotoListForTag(tagId,
				new AsyncCallback<List<String>>() {
					public void onFailure(Throwable caught) {
						tagView
								.setStatusText("Error recieving photo list for id: "
										+ tagId + ".");
					}

					public void onSuccess(List<String> result) {
						tagView.setStatusText("Photo list recieved.");
						photoList = result;
						if (photoId == null) {
							goFirst();
						}
					}
				});
	}
	
	private void requestTagTreeData() {
		clientFactory.getDataManager().getTagTree(new AsyncCallback<List<TagNode>>() {
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
		attach(root,tagTrees);
		return root;
	}
	
	private void attach(TreeItem item, List<TagNode> children) {
		for (TagNode child: children) {
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
			String firstPhotoId = selectedTag.getCachedPhotoList().get(0);
			TagPlace newPlace = new TagPlace(tagId, firstPhotoId);
			clientFactory.getPlaceController().goTo(newPlace);
		}
	}

	@Override
	public void reloadTree() {
		requestTagTreeData();
	}

	private TreeItem findTagId(String tagId) {
		TreeItem root = tagView.getTreeModel();
		TreeItem itemToBeSelected = findTagIdUnder(tagId, root);
		return itemToBeSelected;
	}
	
	private TreeItem findTagIdUnder(String tagId, TreeItem root) {
		for(int index = 0; index < root.getChildCount(); index++) {
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
