/**
 *
 */
package com.googlecode.fspotcloud.model.jpa.gae.photo;

import com.google.appengine.api.datastore.Blob;
import com.googlecode.fspotcloud.server.model.api.Photo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

/**
 * @author slspeek@gmail.com
 *
 */
@Entity
public class PhotoEntity implements Photo, Serializable {

    @Id
    private String id;
    private String description;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date date;
    private ArrayList<String> tagList = new ArrayList<String>();
    private Boolean imageLoaded = false;
    private Boolean thumbLoaded = false;
    private Boolean fullsizeLoaded = false;

    @Override
    public void setId(String name) {
        this.id = name;
    }

    @Override
    public String getId() {
        return id;
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
    @Basic
    private Blob image;
    @Lob
    private Blob thumb;

    @Override
    public void setThumb(byte[] thumb) {
        this.thumb = new Blob(thumb);
    }

    @Override
    public byte[] getThumb() {
        return thumb.getBytes();
    }

    @Override
    public void setImage(byte[] image) {
        this.image = new Blob(image);
    }

    @Override
    public byte[] getImage() {
        return image.getBytes();
    }
}
