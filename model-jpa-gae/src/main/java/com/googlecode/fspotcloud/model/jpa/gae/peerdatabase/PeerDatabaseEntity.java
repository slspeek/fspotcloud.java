package com.googlecode.fspotcloud.model.jpa.gae.peerdatabase;

import com.google.appengine.api.datastore.Blob;
import com.googlecode.fspotcloud.server.model.api.PeerDatabase;
import com.googlecode.fspotcloud.shared.tag.TagNode;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import org.apache.commons.lang.SerializationUtils;

/**
 * Represents a whole F-Spot instance and stores application state
 *
 */
@Entity
public class PeerDatabaseEntity implements PeerDatabase, Serializable
{

    transient private static final long serialVersionUID = -4842421992073164575L;
    @Id
    private String id;
    @Basic
    private String peerName;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date peerLastContact;
    @Basic
    private int peerPhotoCount;
    @Basic
    private int tagCount;
    @Basic
    private long photoCount;
    @Basic
    private String thumbDimension = "512x384";
    @Basic
    private String imageDimension = "1024x768";
    
   
    @Override
    public String getThumbDimension() {
        return thumbDimension;
    }

    @Override
    public void setThumbDimension(String thumbDimension) {
        this.thumbDimension = thumbDimension;
    }

    @Override
    public String getImageDimension() {
        return imageDimension;
    }

    @Override
    public void setImageDimension(String imageDimension) {
        this.imageDimension = imageDimension;
    }

    @Override
    public Date getPeerLastContact() {
        return peerLastContact;
    }

    @Override
    public void setPeerLastContact(Date peerLastContact) {
        this.peerLastContact = peerLastContact;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public void setPeerPhotoCount(int count) {
        this.peerPhotoCount = count;
    }

    @Override
    public void setPeerName(String peerName) {
        this.peerName = peerName;
    }

    @Override
    public String getPeerName() {
        return peerName;
    }

    @Override
    public void setTagCount(int tagCount) {
        this.tagCount = tagCount;
    }

    @Override
    public int getTagCount() {
        return tagCount;
    }

    @Override
    public void touchPeerContact() {
        setPeerLastContact(new Date());
    }

    @Override
    public int getPeerPhotoCount() {
        return peerPhotoCount;
    }

    @Override
    public void setPhotoCount(long photoCount) {
        this.photoCount = photoCount;
    }

    @Override
    public long getPhotoCount() {
        return photoCount;
    }
    
    @Basic
    Blob cachedTagTreeData = null;
    transient private ArrayList<TagNode> cachedTagTree = null;

    public PeerDatabaseEntity() {
    }

    @Override
    public void setCachedTagTree(List<TagNode> newTagTree) {
        if (newTagTree == null) {
            this.cachedTagTreeData = null;
            this.cachedTagTree = null;
        } else {
            this.cachedTagTree = new ArrayList<TagNode>(newTagTree);
            this.cachedTagTreeData = new Blob(SerializationUtils.serialize(this.cachedTagTree));
        }
    }

    @Override
    public List<TagNode> getCachedTagTree() {
        if (cachedTagTree == null && cachedTagTreeData != null) {
            cachedTagTree = (ArrayList<TagNode>) SerializationUtils.deserialize(cachedTagTreeData.getBytes());
        }
        return cachedTagTree;
    }
}
