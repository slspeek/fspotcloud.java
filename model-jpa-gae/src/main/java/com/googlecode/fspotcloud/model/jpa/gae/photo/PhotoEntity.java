/*
 * Copyright 2010-2012 Steven L. Speek.
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 *
 */
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
 * DOCUMENT ME!
 *
 * @author slspeek@gmail.com
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
    @Basic
    private Blob image;
    @Lob
    private Blob thumb;

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
