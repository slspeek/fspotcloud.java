package fspotcloud.server.control.reciever;

import javax.jdo.PersistenceManager;

import fspotcloud.server.tag.Tag;
import fspotcloud.server.util.PMF;

public class TagReciever {
	public int recieveTags(Object[] list) {
		for (Object tag : list) {
			Object[] tag_as_array = (Object[]) tag;
			recieveTag(tag_as_array);
		}
		return 0;
	}

	private int recieveTag(Object[] tag_data) {
		String keyName = (String) tag_data[0];
		String tagName = (String) tag_data[1];
		String desc = (String) tag_data[2];
		String parentId = (String) tag_data[3];
		int count = (Integer) tag_data[4];

		Tag tag = new Tag();
		tag.setName(keyName);
		tag.setTagName(tagName);
		tag.setDescription(desc);
		tag.setParentId(parentId);
		tag.setCount(count);

		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			pm.makePersistent(tag);
		} finally {
			pm.close();
		}
		return 0;
	}
}
