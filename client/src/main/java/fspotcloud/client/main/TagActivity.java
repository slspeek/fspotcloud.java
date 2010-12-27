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
	private static final Logger log = Logger.getLogger(TagActivity.class.getName());
	EventBus eventBus;
	ClientFactory clientFactory;
	TagView tagView;
	private String tagId;

	public TagActivity(TagPlace place, ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
		this.tagView = clientFactory.getTagView();
		this.tagId = place.getTagId();
		log.info("Created for tagId: " + tagId);
	}

	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
		log.info("S!tart started for tagId: " + tagId );
		this.eventBus = eventBus;
		tagView.setPresenter(this);
		TagNode tag = clientFactory.getDataManager().getTagNode(tagId);
		if (tag != null) {
			List<String> imageURIS = makeImageURIS(tag.getCachedPhotoList());
			log.info("bEFORE");
			tagView.setImageList(imageURIS);
		}
		log.info("bEFORE setWidhet");
		containerWidget.setWidget(tagView);
		log.info("After setWidGet, END OF START	");
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

}
