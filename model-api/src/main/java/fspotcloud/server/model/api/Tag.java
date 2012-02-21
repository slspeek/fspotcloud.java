package fspotcloud.server.model.api;

import fspotcloud.shared.photo.PhotoInfo;
import fspotcloud.simplejpadao.HasSetId;
import java.util.TreeSet;

public interface Tag extends HasSetId {

    void setParent(String parent);

    String getParent();

    void setCount(int count);

    int getCount();

    void setTagName(String tagName);

    String getTagName();

    void setParentId(String parentId);

    String getParentId();

    void setCachedPhotoList(TreeSet<PhotoInfo> cachedPhotoList);

    TreeSet<PhotoInfo> getCachedPhotoList();

    void setImportIssued(boolean importIssued);

    boolean isImportIssued();
}