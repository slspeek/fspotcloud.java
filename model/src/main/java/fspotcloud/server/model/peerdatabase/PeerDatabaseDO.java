package fspotcloud.server.model.peerdatabase;

import java.util.Date;
import java.util.List;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import fspotcloud.server.model.api.PeerDatabase;
import fspotcloud.shared.tag.TagNode;

/**
 * Represents a whole F-Spot instance and stores application state
 * 
 */
@PersistenceCapable(detachable = "true")
public class PeerDatabaseDO implements PeerDatabase {
	@PrimaryKey
	private String name;

	@Persistent
	private int peerPhotoCount;
	
	@Persistent
	private int tagCount;
	
	@Persistent
	private long photoCount;

	@Persistent(serialized = "true")
	private List<TagNode> cachedTagTree;
	
	@Persistent(serialized = "true")
	private List<String> cachedImportedTags;
	
	@Persistent
	private String thumbDimension = "512x384";

	@Persistent
	private String imageDimension = "1024x768";

	public String getThumbDimension() {
		return thumbDimension;
	}

	public void setThumbDimension(String thumbDimension) {
		this.thumbDimension = thumbDimension;
	}

	public String getImageDimension() {
		return imageDimension;
	}

	public void setImageDimension(String imageDimension) {
		this.imageDimension = imageDimension;
	}

	public List<String> getCachedImportedTags() {
		return cachedImportedTags;
	}

	public void setCachedImportedTags(List<String> cachedImportedTags) {
		this.cachedImportedTags = cachedImportedTags;
	}

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPeerPhotoCount(int count) {
		this.peerPhotoCount = count;
	}

	public void setPeerName(String peerName) {
		this.peerName = peerName;
	}

	public String getPeerName() {
		return peerName;
	}

	public void setCachedTagTree(List<TagNode> cachedTagTree) {
		this.cachedTagTree = cachedTagTree;
	}

	public List<TagNode> getCachedTagTree() {
		return cachedTagTree;
	}

	public void setTagCount(int tagCount) {
		this.tagCount = tagCount;
	}

	public int getTagCount() {
		return tagCount;
	}

	@Override
	public void touchPeerContact() {
		setPeerLastContact(new Date());
	}

	@Override
	public int getPeerPhotoCount() {
		return peerPhotoCount;
	}

	public void setPhotoCount(long photoCount) {
		this.photoCount = photoCount;
	}

	public long getPhotoCount() {
		return photoCount;
	}
}
