package fspotcloud.server.model.peerdatabase;

import java.util.List;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import fspotcloud.shared.tag.TagNode;

/**
 * Represents a whole F-Spot instance
 * and stores application state
 * 
 */
@PersistenceCapable
public class PeerDatabase {
	@PrimaryKey
	private String name;
	
	@Persistent
	private int count;
	
	@Persistent(serialized = "true")
	private  List<TagNode> cachedTagTree;
	
	@Persistent
	private String peerName;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}


	/**
	 * @param peerName the peerName to set
	 */
	public void setPeerName(String peerName) {
		this.peerName = peerName;
	}

	/**
	 * @return the peerName
	 */
	public String getPeerName() {
		return peerName;
	}

	/**
	 * @param cachedTagTree the cachedTagTree to set
	 */
	public void setCachedTagTree(List<TagNode> cachedTagTree) {
		this.cachedTagTree = cachedTagTree;
	}

	/**
	 * @return the cachedTagTree
	 */
	public List<TagNode> getCachedTagTree() {
		return cachedTagTree;
	}

}
