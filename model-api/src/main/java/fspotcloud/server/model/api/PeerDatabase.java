package fspotcloud.server.model.api;

import java.util.Date;
import java.util.List;

import fspotcloud.shared.tag.TagNode;

public interface PeerDatabase {

	String getName();

	void setName(String name);
	
	void setPhotoCount(long photoCount);

	long getPhotoCount();

	int getPeerPhotoCount();

	void setPeerPhotoCount(int count);

	void setPeerName(String peerName);

	String getPeerName();

	void setCachedTagTree(List<TagNode> cachedTagTree);

	List<TagNode> getCachedTagTree();

	void setTagCount(int tagCount);

	int getTagCount();

	Date getPeerLastContact();

	void setPeerLastContact(Date date);

	void touchPeerContact();

	String getThumbDimension();
	
	void setThumbDimension(String dim);
	
	String getImageDimension();
	
	void setImageDimension(String dim);

}