/**
 *
 */
package fspotcloud.server.model.tag;

import java.io.Serializable;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import fspotcloud.server.model.api.Tag;
import fspotcloud.shared.photo.PhotoInfo;

/**
 * Represents a Label in F-Spot
 *
 */
@PersistenceCapable(detachable = "true")
public class TagDO implements Tag, Serializable {

    @PrimaryKey
    private String id;
    @Persistent
    private String tagName;
    @Persistent
    private String parentId;
    @Persistent
    private String parent;
    @Persistent
    private int count;
    @Persistent
    private boolean importIssued = false;
    @Persistent(serialized = "true")
    private SortedSet<PhotoInfo> cachedPhotoList = new TreeSet<PhotoInfo>();

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getParent() {
        return parent;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getTagName() {
        return tagName;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setCachedPhotoList(SortedSet<PhotoInfo> cachedPhotoList) {
        this.cachedPhotoList = cachedPhotoList;
    }

    public SortedSet<PhotoInfo> getCachedPhotoList() {
        return cachedPhotoList;
    }

    public void setImportIssued(boolean importIssued) {
        this.importIssued = importIssued;
    }

    public boolean isImportIssued() {
        return importIssued;
    }

    @Override
    public String getId() {
        return id;
    }
}
