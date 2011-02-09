package fspotcloud.shared.photo;

import java.io.Serializable;
import java.util.Date;

public class PhotoInfo implements Serializable, Comparable<PhotoInfo> {

	private static final long serialVersionUID = -4084831085611916754L;

	final private String id;
	final private String description;
	final private Date date;

	public PhotoInfo(String id, String description, Date date) {
		if (id == null|| date == null) throw new NullPointerException();
		System.out.println("PhotoInfo constrcu");
		this.id = id;
		this.description = description;
		this.date = date;
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
		System.out.println(this);
		System.out.println(date);
		return this.date.compareTo(date);
	}
	
	public String toString() {
		return "PhotoInfo("+id+", "+date+")";
	}
}

