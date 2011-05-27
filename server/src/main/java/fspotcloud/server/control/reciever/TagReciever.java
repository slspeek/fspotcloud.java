package fspotcloud.server.control.reciever;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;

import fspotcloud.server.model.api.Tag;
import fspotcloud.server.model.api.Tags;

public class TagReciever {

	private Tags tagManager;

	@Inject
	public TagReciever(Tags tagManager) {
		this.tagManager = tagManager;
	}

	public int recieveTagData(Object[] list) {
		List<Tag> tagList = new ArrayList<Tag>();
		for (Object tag : list) {
			Object[] tag_as_array = (Object[]) tag;
			tagList.add(recieveTag(tag_as_array));
		}
		tagManager.saveAll(tagList);
		return 0;
	}

	private Tag recieveTag(Object[] tag_data) {
		String keyName = (String) tag_data[0];
		String tagName = (String) tag_data[1];
		String parentId = (String) tag_data[2];
		int count = Integer.valueOf((String) tag_data[3]);

		Tag tag = tagManager.getOrNew(keyName);
		tag.setTagName(tagName);
		tag.setParentId(parentId);
		tag.setCount(count);

		return tag;
	}
}
