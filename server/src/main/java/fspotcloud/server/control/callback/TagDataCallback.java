package fspotcloud.server.control.callback;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

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

	protected static final Logger log = Logger.getLogger(TagDataCallback.class.getName());
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
		log.log(Level.SEVERE, "TagDataCallbask", caught);

	}

	@Override
	public void onSuccess(TagDataResult result) {
		for (TagData data : result.getTagDataList()) {
			String keyName = data.getTagId();
			Tag tag = tagManager.getOrNew(keyName);
			int previousCount = tag.getCount();
			recieveTag(data, tag);
			tagManager.save(tag);
			importNewImages(tag, previousCount);
		}
	}

	private void importNewImages(Tag tag, int previousCount) {
		if (tag.isImportIssued()) {
			try {
				dispatch.execute(new ImportTag(tag.getId(), previousCount));
			} catch (DispatchException e) {
				log.log(Level.SEVERE, "Caught: ", e);
			}
		}
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
