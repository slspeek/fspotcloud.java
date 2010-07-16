package fspotcloud.server.control.reciever;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;

import com.google.inject.Inject;

import fspotcloud.server.model.tag.Tag;

public class TagReciever {
	
	private PersistenceManager pm;

	@Inject
	public TagReciever(PersistenceManager pm) {
		this.pm = pm;
	}
	
	public int recieveTagData(Object[] list) {
		List<Tag> tagList = new ArrayList<Tag>();
		for (Object tag : list) {
			Object[] tag_as_array = (Object[]) tag;
			tagList.add(recieveTag(tag_as_array));
		}
		pm.makePersistentAll(tagList);
		return 0;
	}

	private Tag recieveTag(Object[] tag_data) {
		String keyName = (String) tag_data[0];
		String tagName = (String) tag_data[1];
		String parentId = (String) tag_data[2];
		int count = Integer.valueOf((String) tag_data[3]);

		Tag tag = new Tag();
		tag.setName(keyName);
		tag.setTagName(tagName);
		tag.setParentId(parentId);
		tag.setCount(count);

		return tag;
	}
}
