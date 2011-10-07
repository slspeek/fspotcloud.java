package fspotcloud.server.control.callback;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

import fspotcloud.server.model.api.Tag;
import fspotcloud.server.model.api.Tags;
import fspotcloud.shared.peer.rpc.actions.TagData;
import fspotcloud.shared.peer.rpc.actions.TagDataResult;

public class TagDataCallback implements AsyncCallback<TagDataResult>,
		Serializable {

	private static final long serialVersionUID = 5342287706825285919L;

	@Inject
	transient private Tags tagManager;

	public TagDataCallback(Tags tagManager) {
		super();
		this.tagManager = tagManager;
	}

	@Override
	public void onFailure(Throwable caught) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSuccess(TagDataResult result) {
		List<Tag> tagList = new ArrayList<Tag>();
		for (TagData data : result.getTagDataList()) {
			tagList.add(recieveTag(data));
		}
		tagManager.saveAll(tagList);
	}

	private Tag recieveTag(TagData data) {
		String keyName = data.getTagId();
		String tagName = data.getName();
		String parentId = data.getParentId();
		int count = data.getCount();

		Tag tag = tagManager.getOrNew(keyName);
		tag.setTagName(tagName);
		tag.setParentId(parentId);
		tag.setCount(count);

		return tag;
	}

}
