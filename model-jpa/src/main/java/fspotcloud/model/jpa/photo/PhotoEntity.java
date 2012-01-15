/**
 *
 */
package fspotcloud.model.jpa.photo;

import fspotcloud.server.model.api.Blob;
import fspotcloud.server.model.api.Photo;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.*;
import org.apache.commons.codec.binary.Base64;
import org.hibernate.annotations.Type;

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
    byte[] image;
    transient private Blob thumb;// = new Blob(new byte[0]);

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
        this.thumb = new Blob(thumb);
    }

    @Override
    public byte[] getThumb() {
        if (thumb == null) {
            return new byte[0];
        }
        return thumb.getBytes();
    }

    @Override
    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public byte[] getImage() {
        if (image == null) {
            System.out.println("NULL AGAIN");
            return new byte[0];
        }
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
