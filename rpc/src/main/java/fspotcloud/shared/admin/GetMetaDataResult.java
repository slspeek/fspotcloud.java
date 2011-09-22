package fspotcloud.shared.admin;

import java.util.Date;

import net.customware.gwt.dispatch.shared.Result;

import com.google.gwt.user.client.rpc.IsSerializable;

public class GetMetaDataResult implements IsSerializable, Result {

	private Date created;
	private int peerPhotoCount;
	private int tagCount;
	private int pendingCommandCount;
	private Date peerLastSeen;
	private String instanceName;
	private long photoCount;
	private Date photosLastCounted;
	private boolean deletePhotosActive;
	private boolean deleteTagsActive;
	private boolean importImagesActive;
	private boolean countPhotosActive;

	public long getPhotoCount() {
		return photoCount;
	}

	public void setPhotoCount(long photoCount) {
		this.photoCount = photoCount;
	}

	public Date getPhotosLastCounted() {
		return photosLastCounted;
	}

	public void setPhotosLastCounted(Date photosLastCounted) {
		this.photosLastCounted = photosLastCounted;
	}

	public GetMetaDataResult() {
		created = new Date();
	}

	public Date getCreated() {
		return created;
	}

	public int getPeerPhotoCount() {
		return peerPhotoCount;
	}

	public void setPeerPhotoCount(int peerPhotoCount) {
		this.peerPhotoCount = peerPhotoCount;
	}

	public int getTagCount() {
		return tagCount;
	}

	public void setTagCount(int tagCount) {
		this.tagCount = tagCount;
	}

	public Date getPeerLastSeen() {
		return peerLastSeen;
	}

	public void setPeerLastSeen(Date peerLastSeen) {
		this.peerLastSeen = peerLastSeen;
	}

	public String getInstanceName() {
		return instanceName;
	}

	public void setInstanceName(String instanceName) {
		this.instanceName = instanceName;
	}

	public void setPendingCommandCount(int pendingCommandCount) {
		this.pendingCommandCount = pendingCommandCount;
	}

	public int getPendingCommandCount() {
		return pendingCommandCount;
	}

	public boolean isDeletePhotosActive() {
		return deletePhotosActive;
	}

	public void setDeletePhotosActive(boolean deletePhotosActive) {
		this.deletePhotosActive = deletePhotosActive;
	}

	public boolean isDeleteTagsActive() {
		return deleteTagsActive;
	}

	public void setDeleteTagsActive(boolean deleteTagsActive) {
		this.deleteTagsActive = deleteTagsActive;
	}

	public boolean isImportImagesActive() {
		return importImagesActive;
	}

	public void setImportImagesActive(boolean importImagesActive) {
		this.importImagesActive = importImagesActive;
	}

	public void setCountPhotosActive(boolean countPhotosActive) {
		this.countPhotosActive = countPhotosActive;
	}

	public boolean isCountPhotosActive() {
		return countPhotosActive;
	}
}
