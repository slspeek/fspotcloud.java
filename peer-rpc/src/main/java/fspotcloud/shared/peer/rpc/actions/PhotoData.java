package fspotcloud.shared.peer.rpc.actions;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class PhotoData implements Serializable {
	
	private static final long serialVersionUID = -4172714943981557358L;
	
	private String photoId;
	private String desscription;
	private Date date;
	private List<String> tagList;
	public PhotoData(String photoId, String desscription, Date date,
			List<String> tagList) {
		super();
		this.photoId = photoId;
		this.desscription = desscription;
		this.date = date;
		this.tagList = tagList;
	}
	public String getPhotoId() {
		return photoId;
	}
	public String getDesscription() {
		return desscription;
	}
	public Date getDate() {
		return date;
	}
	public List<String> getTagList() {
		return tagList;
	}
}
