package fspotcloud.shared.admin;

import java.util.Date;

import com.google.gwt.user.client.rpc.IsSerializable;

public class MetaDataInfo implements IsSerializable {

	private Date created;
	private int peerPhotoCount;
	private int tagCount;
	private Date peerLastSeen;
	private String instanceName;
	
	public MetaDataInfo() {
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
	

}
