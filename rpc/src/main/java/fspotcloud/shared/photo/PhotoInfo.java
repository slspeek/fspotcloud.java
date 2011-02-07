package fspotcloud.shared.photo;

import java.io.Serializable;
import java.util.Date;

import com.google.common.base.Objects;

public class PhotoInfo implements Serializable,
		Comparable<PhotoInfo> {

	private static final long serialVersionUID = -4084831085611916754L;

	private String id;
	private String description;
	private Date date;

	public PhotoInfo(String id, String description, Date date) {
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

	public void setDate(Date date) {
		this.date = date;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public int hashCode() {
		return Objects.hashCode(id, date);
	}

	public boolean equals(Object o) {
		if (o instanceof PhotoInfo) {
			PhotoInfo photo = (PhotoInfo) o;
			return Objects.equal(this.id, photo.getId())
					&& Objects.equal(this.date, photo.getDate());
		} else {
			return false;
		}
	}

	@Override
	public int compareTo(PhotoInfo o) {
		Date date = o.getDate();
		return this.date.compareTo(date);
	}
}
