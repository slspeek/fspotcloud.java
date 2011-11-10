package fspotcloud.shared.peer.rpc.actions;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class PhotoData implements Serializable {

	private static final long serialVersionUID = -4172714943981557358L;

	private String photoId;
	private String description;
	private Date date;
	private byte[] imageData;
	private byte[] thumbData;
	private List<String> tagList;

	public PhotoData(String photoId, String description, Date date,
			byte[] imageData, byte[] thumbData, List<String> tagList) {
		super();
		this.photoId = photoId;
		this.description = description;
		this.date = date;
		this.imageData = imageData;
		this.thumbData = thumbData;
		this.tagList = tagList;
	}

	public String getPhotoId() {
		return photoId;
	}

	public String getDescription() {
		return description;
	}

	public Date getDate() {
		return date;
	}

	public byte[] getImageData() {
		return imageData;
	}

	public byte[] getThumbData() {
		return thumbData;
	}

	public List<String> getTagList() {
		return tagList;
	}
}
