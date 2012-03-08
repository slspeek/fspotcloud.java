package com.googlecode.fspotcloud.server.model.api;

import com.googlecode.fspotcloud.shared.photo.PhotoInfo;
import com.googlecode.simplejpadao.HasSetKey;
import java.io.Serializable;
import java.util.TreeSet;

public interface Tag extends HasSetKey<String>, Serializable {

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