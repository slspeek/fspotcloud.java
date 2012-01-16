/**
 *
 */
package fspotcloud.model.jpa.photo;

import fspotcloud.server.model.api.Photo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Temporal;

/**
 * @author slspeek@gmail.com
 *
 */
@Entity
public class PhotoEntity implements Photo, Serializable {

    @Id
    private String name;
    private String description;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date date;
    private ArrayList<String> tagList;
    private Boolean imageLoaded = false;
    private Boolean thumbLoaded = false;
    private Boolean fullsizeLoaded = false;
    @Lob
    private byte[] image;
    @Lob
    private byte[] thumb;

    @Override
    public void setId(String name) {
        this.name = name;
    }

    @Override
    public String getId() {
        return name;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setTagList(List<String> tagList) {
        this.tagList = new ArrayList(tagList);
    }

    @Override
    public List<String> getTagList() {
        return tagList;
    }

    @Override
    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public Date getDate() {
        return date;
    }

    @Override
    public void setThumb(byte[] thumb) {
        this.thumb = thumb;
    }

    @Override
    public byte[] getThumb() {
        return thumb;
    }

    @Override
    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public byte[] getImage() {
        return image;
    }

    @Override
    public void setImageLoaded(Boolean imageLoaded) {
        this.imageLoaded = imageLoaded;
    }

    @Override
    public Boolean isImageLoaded() {
        return imageLoaded;
    }

    @Override
    public void setThumbLoaded(Boolean thumbLoaded) {
        this.thumbLoaded = thumbLoaded;
    }

    @Override
    public Boolean isThumbLoaded() {
        return thumbLoaded;
    }

    @Override
    public void setFullsizeLoaded(Boolean fullsizeLoaded) {
        this.fullsizeLoaded = fullsizeLoaded;
    }

    @Override
    public Boolean isFullsizeLoaded() {
        return fullsizeLoaded;
    }

    @Override
    public void setExifData(String data) {
    }

    @Override
    public String getExifData() {
        return null;
    }
}
