package fspotcloud.client.view;

import java.util.logging.Logger;

import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.inject.Inject;

import fspotcloud.shared.tag.TagNode;

public class TreeSelectionHandler implements Handler {
	private static final Logger log = Logger
			.getLogger(TreeSelectionHandler.class.getName());
	private SingleSelectionModel<TagNode> selectionModel;
	final private PlaceGoTo placeGoTo;

	@Inject
	public TreeSelectionHandler(PlaceGoTo placeGoTo) {
		this.placeGoTo = placeGoTo;
	}

	public void setSelectionModel(SingleSelectionModel<TagNode> selectionModel) {
		this.selectionModel = selectionModel;
		selectionModel.addSelectionChangeHandler(this);
	}

	@Override
	public void onSelectionChange(SelectionChangeEvent event) {
		log.info("Selection event from tree" + selectionModel);
		TagNode node = selectionModel.getSelectedObject();
		if (node != null) {
			log.info("Selection event: nodel != null");
			if (!node.getCachedPhotoList().isEmpty()) {
				log.info("Selection event: not empty");
				String firstPhotoId = node.getCachedPhotoList().get(0).getId();
				String tagId = node.getId();
				goToPhoto(tagId, firstPhotoId);
			}
		}
	}

	private void goToPhoto(String otherTagId, String photoId) {
		placeGoTo.goTo(new TagViewingPlace(otherTagId, photoId));
	}
}
