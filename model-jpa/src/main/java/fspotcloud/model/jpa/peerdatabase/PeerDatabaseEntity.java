package fspotcloud.model.jpa.peerdatabase;

import fspotcloud.server.model.api.PeerDatabase;
import fspotcloud.shared.tag.TagNode;
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
public class PeerDatabaseEntity implements PeerDatabase, Serializable {

    transient private static final long serialVersionUID = -4842421992073164575L;
    @Id
    private String name;
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
    @Lob
    byte[] cachedTagTreeData;
    transient private ArrayList<TagNode> cachedTagTree = null;
    @Basic
    private String thumbDimension = "512x384";
    @Basic
    private String imageDimension = "1024x768";

    public PeerDatabaseEntity() {
    }

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
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
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
    public void setCachedTagTree(List<TagNode> cachedTagTree) {
        if (cachedTagTree == null) {
            this.cachedTagTree = null;
        } else {
            this.cachedTagTree = new ArrayList<TagNode>(cachedTagTree);
            this.cachedTagTreeData = SerializationUtils.serialize(this.cachedTagTree);
        }
    }

    @Override
    public List<TagNode> getCachedTagTree() {
        if (cachedTagTree == null && cachedTagTreeData != null) {
            cachedTagTree = (ArrayList<TagNode>) SerializationUtils.deserialize(cachedTagTreeData);
        }
        return cachedTagTree;
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

    public void setPhotoCount(long photoCount) {
        this.photoCount = photoCount;
    }

    public long getPhotoCount() {
        return photoCount;
    }
}
