package fspotcloud.shared.photo;

import java.io.Serializable;
import java.util.Date;

public class PhotoInfo implements Serializable, Comparable<PhotoInfo> {

	private static final long serialVersionUID = -4084831085611916754L;

	private String id;
	private String description;
	private String exifData;
	private Date date;

	@SuppressWarnings("unused")
	private PhotoInfo() {
		
	}
	
	public PhotoInfo(String id, String description, Date date) {
		this.id = id;
		this.description = description;
		this.date = date;
	}
	
	public PhotoInfo(String id, String description, Date date, String exifData) {
		this(id, description, date);
		this.exifData = exifData;
	}

	public Date getDate() {
		return date;
	}

	public String getDescription() {
		return description;
	}

	public String getId() {
		return id;
	}

	public int hashCode() {
		return id.hashCode();
	}

	public boolean equals(Object o) {
		String id = null;
		if (o instanceof PhotoInfo) {
			PhotoInfo photo = (PhotoInfo) o;
			id = photo.getId();
		} else if (o instanceof String) {
			id = (String) o;
		}
		return this.id.equals(id);
	}

	@Override
	public int compareTo(PhotoInfo o) {
		Date date = o.getDate();
		return this.date.compareTo(date);
	}

	public String toString() {
		return "PhotoInfo(" + id + ", " + (date)+ ")";
	}

	public void setExifData(String exifData) {
		this.exifData = exifData;
	}

	public String getExifData() {
		return exifData;
	}
}
