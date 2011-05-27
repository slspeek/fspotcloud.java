package fspotcloud.server.model.peerdatabase;

import java.util.Date;
import java.util.List;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import fspotcloud.server.model.api.PeerDatabase;
import fspotcloud.shared.tag.TagNode;

/**
 * Represents a whole F-Spot instance
 * and stores application state
 * 
 */
@PersistenceCapable(detachable="true")
public class PeerDatabaseDO implements PeerDatabase {
	@PrimaryKey
	private String name;
	
	@Persistent
	private int count;
	
	@Persistent
	private int tagCount;
	
	@Persistent(serialized = "true")
	private  List<TagNode> cachedTagTree;
	
	@Persistent
	private String peerName;
	
	@Persistent
	private Date peerLastContact;

	public Date getPeerLastContact() {
		return peerLastContact;
	}

	public void setPeerLastContact(Date peerLastContact) {
		this.peerLastContact = peerLastContact;
	}

	/* (non-Javadoc)
	 * @see fspotcloud.server.model.peerdatabase.PeerDatabase#getName()
	 */
	public String getName() {
		return name;
	}

	/* (non-Javadoc)
	 * @see fspotcloud.server.model.peerdatabase.PeerDatabase#setName(java.lang.String)
	 */
	public void setName(String name) {
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see fspotcloud.server.model.peerdatabase.PeerDatabase#getCount()
	 */
	public int getCount() {
		return count;
	}

	/* (non-Javadoc)
	 * @see fspotcloud.server.model.peerdatabase.PeerDatabase#setCount(int)
	 */
	public void setCount(int count) {
		this.count = count;
	}


	/* (non-Javadoc)
	 * @see fspotcloud.server.model.peerdatabase.PeerDatabase#setPeerName(java.lang.String)
	 */
	public void setPeerName(String peerName) {
		this.peerName = peerName;
	}

	/* (non-Javadoc)
	 * @see fspotcloud.server.model.peerdatabase.PeerDatabase#getPeerName()
	 */
	public String getPeerName() {
		return peerName;
	}

	/* (non-Javadoc)
	 * @see fspotcloud.server.model.peerdatabase.PeerDatabase#setCachedTagTree(java.util.List)
	 */
	public void setCachedTagTree(List<TagNode> cachedTagTree) {
		this.cachedTagTree = cachedTagTree;
	}

	/* (non-Javadoc)
	 * @see fspotcloud.server.model.peerdatabase.PeerDatabase#getCachedTagTree()
	 */
	public List<TagNode> getCachedTagTree() {
		return cachedTagTree;
	}

	/* (non-Javadoc)
	 * @see fspotcloud.server.model.peerdatabase.PeerDatabase#setTagCount(int)
	 */
	public void setTagCount(int tagCount) {
		this.tagCount = tagCount;
	}

	/* (non-Javadoc)
	 * @see fspotcloud.server.model.peerdatabase.PeerDatabase#getTagCount()
	 */
	public int getTagCount() {
		return tagCount;
	}

}
