package fspotcloud.server.model.api;

import java.util.Date;
import java.util.List;

import fspotcloud.shared.tag.TagNode;

public interface PeerDatabase {

	public abstract String getName();

	public abstract void setName(String name);

	public abstract int getCount();

	public abstract void setCount(int count);

	/**
	 * @param peerName the peerName to set
	 */
	public abstract void setPeerName(String peerName);

	/**
	 * @return the peerName
	 */
	public abstract String getPeerName();

	/**
	 * @param cachedTagTree the cachedTagTree to set
	 */
	public abstract void setCachedTagTree(List<TagNode> cachedTagTree);

	/**
	 * @return the cachedTagTree
	 */
	public abstract List<TagNode> getCachedTagTree();

	/**
	 * @param tagCount the tagCount to set
	 */
	public abstract void setTagCount(int tagCount);

	/**
	 * @return the tagCount
	 */
	public abstract int getTagCount();
	
	Date getPeerLastContact();
	
	void setPeerLastContact(Date date);
	

	void touchPeerContact();

}