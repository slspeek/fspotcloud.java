package fspotcloud.server.control.callback;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.customware.gwt.dispatch.server.Dispatch;
import net.customware.gwt.dispatch.shared.DispatchException;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

import fspotcloud.server.model.api.Tag;
import fspotcloud.server.model.api.Tags;
import fspotcloud.shared.dashboard.actions.ImportTag;
import fspotcloud.shared.peer.rpc.actions.TagData;
import fspotcloud.shared.peer.rpc.actions.TagDataResult;

public class TagDataCallback implements AsyncCallback<TagDataResult>,
		Serializable {

	private static final long serialVersionUID = 5342287706825285919L;

	@Inject
	transient private Tags tagManager;
	@Inject
	transient private Dispatch dispatch;

	public TagDataCallback(Tags tagManager, Dispatch dispatch) {
		super();
		this.tagManager = tagManager;
		this.dispatch = dispatch;
	}

	@Override
	public void onFailure(Throwable caught) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSuccess(TagDataResult result) {
		List<Tag> tagList = new ArrayList<Tag>();
		for (TagData data : result.getTagDataList()) {
			String keyName = data.getTagId();
			Tag tag = tagManager.getOrNew(keyName);
			int previousCount = tag.getCount();

			tagList.add(tag);
			if (tag.isImportIssued()) {
				try {
					dispatch.execute(new ImportTag(tag.getId(), previousCount));
				} catch (DispatchException e) {

				}
			}
			// must be last, very bad :(
			recieveTag(data, tag);
		}
		tagManager.saveAll(tagList);
	}

	private void recieveTag(TagData data, Tag tag) {

		String tagName = data.getName();
		String parentId = data.getParentId();
		int count = data.getCount();

		tag.setTagName(tagName);
		tag.setParentId(parentId);
		tag.setCount(count);
	}

}
