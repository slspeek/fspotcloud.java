package fspotcloud.peer.handlers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.server.SimpleActionHandler;
import net.customware.gwt.dispatch.shared.DispatchException;
import fspotcloud.peer.db.Data;
import fspotcloud.shared.peer.rpc.actions.GetTagUpdateInstructionsAction;
import fspotcloud.shared.peer.rpc.actions.PhotoRemovedFromTag;
import fspotcloud.shared.peer.rpc.actions.PhotoUpdate;
import fspotcloud.shared.peer.rpc.actions.TagUpdateInstructionsResult;
import fspotcloud.shared.photo.PhotoInfo;

public class GetTagUpdateInstructionsHandler
		extends
		SimpleActionHandler<GetTagUpdateInstructionsAction, TagUpdateInstructionsResult> {

	final private Data data;

	@Inject
	public GetTagUpdateInstructionsHandler(Data data) {
		super();
		this.data = data;
	}

	@Override
	public TagUpdateInstructionsResult execute(
			GetTagUpdateInstructionsAction action, ExecutionContext context)
			throws DispatchException {
		TagUpdateInstructionsResult result = new TagUpdateInstructionsResult(
				new ArrayList<PhotoUpdate>(),
				new ArrayList<PhotoRemovedFromTag>());
		try {
			List<String> keysOnThePeer = data.getPhotoKeysInTag(action.getTagId());
			for (PhotoInfo photoInfo : action.getPhotosOnServer()) {
				checkForUpdates(action.getTagId(),photoInfo, result);
				keysOnThePeer.remove(photoInfo.getId());
			}
			for (String key: keysOnThePeer) {
				PhotoUpdate update = new PhotoUpdate(key);
				result.getToBoUpdated().add(update);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private void checkForUpdates(String tagId, PhotoInfo photoInfo,
			TagUpdateInstructionsResult result) throws SQLException {
		String id = photoInfo.getId();
		if (data.isPhotoInTag(tagId, id)) {
			if (photoInfo.getVersion() != data.getPhotoDefaultVersion(id)) {
				result.getToBoUpdated().add(new PhotoUpdate(id));
			}
		} else {
			result.getToBoRemovedFromTag().add(new PhotoRemovedFromTag(id, tagId));
		}
		
	}

}
