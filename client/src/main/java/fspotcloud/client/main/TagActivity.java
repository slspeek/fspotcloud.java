package fspotcloud.client.main;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.TreeItem;

import fspotcloud.shared.tag.TagNode;

public class TagActivity extends AbstractActivity implements
		TagView.TagPresenter {
	String tagId;
	String photoId;
	Integer offset = null;
	List<String> photoList = new ArrayList<String>();
	final ClientFactory clientFactory;
	final DataManager dataManager;
	final TagView tagView;
	private static final Logger log = Logger.getLogger(TagActivity.class
			.getName());

	public TagActivity(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
		this.tagView = clientFactory.getTagView();
		this.dataManager = clientFactory.getDataManager();
	}

	public void setPlace(TagPlace place) {
		log.info("Set place: tag: " + place.getTagId());
		photoList = new ArrayList<String>();
		tagId = place.getTagId();
		photoId = place.getPhotoId();
		TagNode node = dataManager.getTagNode(tagId);
		if (node != null) {
			photoList = node.getCachedPhotoList();
		}
		log.info("Further in setPlace: " + photoList + " node: " + node);
		int where = photoList.indexOf(photoId);
		if (where == -1) {
			offset =  0;
			if (!photoList.isEmpty()) {
				photoId = photoList.get(0);
			}
		} else {
			offset = where;
		}
		if (photoId != null) {
			tagView.setMainImageUrl("/image?id=" + photoId);
		}
	}

	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
		log.info("Start: photoId: " + photoId);
		tagView.setPresenter(this);
		tagView.setTagId(tagId);
		containerWidget.setWidget(tagView);
	}

	@Override
	public boolean canGoBackward() {
		if (offset != null) {
			return offset > 0;
		} else {
			return false;
		}
	}

	@Override
	public boolean canGoForward() {
		if (offset != null) {
			return offset >= 0 && offset < photoList.size() - 1;
		} else {
			return false;
		}
	}

	@Override
	public void goBackward() {
		log.info("GoBackward list: " + photoList + " offset now : " + offset);
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
			String firstPhotoId = selectedTag.getCachedPhotoList().get(0);
			TagPlace newPlace = new TagPlace(tagId, firstPhotoId);
			clientFactory.getPlaceController().goTo(newPlace);
		}
	}

	@Override
	public void reloadTree() {
		requestTagTreeData();
	}
	
}
