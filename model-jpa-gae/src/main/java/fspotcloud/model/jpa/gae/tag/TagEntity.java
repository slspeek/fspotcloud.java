/**
 *
 */
package fspotcloud.model.jpa.gae.tag;

import com.google.appengine.api.datastore.Blob;
import fspotcloud.server.model.api.Tag;
import fspotcloud.shared.photo.PhotoInfo;
import java.io.Serializable;
import java.util.TreeSet;
import javax.persistence.*;
import org.apache.commons.lang.SerializationUtils;

/**
 * Represents a Label in F-Spot
 *
 */
@Entity
public class TagEntity implements Serializable, Tag {

    @Id
    private String id;
    @Basic
    private String tagName;
    @Basic
    private String parentId;
    @Basic
    private String parent;
    @Basic
    private int count;
    @Basic
    private boolean importIssued = false;
    @Basic
    private Blob cachedPhotoList = new Blob(SerializationUtils.serialize(new TreeSet<PhotoInfo>()));

    public TagEntity() {
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public void setParent(String parent) {
        this.parent = parent;
    }

    @Override
    public String getParent() {
        return parent;
    }

    @Override
    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    @Override
    public String getTagName() {
        return tagName;
    }

    @Override
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @Override
    public String getParentId() {
        return parentId;
    }

    @Override
    public void setImportIssued(boolean importIssued) {
        this.importIssued = importIssued;
    }

    @Override
    public boolean isImportIssued() {
        return importIssued;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setCachedPhotoList(TreeSet<PhotoInfo> cachedPhotoList) {
        this.cachedPhotoList = new Blob(SerializationUtils.serialize(cachedPhotoList));
    }

    @Override
    public TreeSet<PhotoInfo> getCachedPhotoList() {
        return (TreeSet<PhotoInfo>) SerializationUtils.deserialize(cachedPhotoList.getBytes());
    }
}
