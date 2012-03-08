package fspotcloud.shared.photo;

import java.io.Serializable;
import java.util.Date;

public class PhotoInfo implements Serializable, Comparable<PhotoInfo> {

    private static final long serialVersionUID = -4084831085611916754L;
    private String id;
    private String description;
    private String exifData;
    private Date date;
    private int version;

    @SuppressWarnings("unused")
    private PhotoInfo() {
    }

    public PhotoInfo(String id, String description, Date date) {
        this.id = id;
        this.description = description;
        this.date = date;
        this.version = 1;
    }

    public PhotoInfo(String id, String description, Date date, int version) {
        this(id, description, date);
        this.version = version;
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
        }
        return this.id.equals(id);
    }

    public int compareTo(PhotoInfo o) {
        Date otherDate = o.getDate();
        final int dateComparison = this.date.compareTo(otherDate);
        if (dateComparison == 0) {
            String otherId = o.getId();
            return id.compareTo(otherId);
        } else {
            return dateComparison;
        }
    }

    public String toString() {
        return "PhotoInfo(" + id + ")";
    }

    public void setExifData(String exifData) {
        this.exifData = exifData;
    }

    public String getExifData() {
        return exifData;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getVersion() {
        return version;
    }
}
