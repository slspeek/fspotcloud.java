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
    private TreeSet<PhotoInfo> cachedPhotoList = new TreeSet<PhotoInfo>();

    @Override
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
    public void setCachedPhotoList(TreeSet<PhotoInfo> cachedPhotoList) {
        this.cachedPhotoList = cachedPhotoList;
    }

    @Override
    public TreeSet<PhotoInfo> getCachedPhotoList() {
        return cachedPhotoList;
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
}
