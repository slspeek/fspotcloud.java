package com.googlecode.fspotcloud.server.control.task.actions.intern;

import com.googlecode.fspotcloud.shared.dashboard.actions.VoidResult;
import com.googlecode.fspotcloud.shared.photo.PhotoInfo;
import java.io.Serializable;
import java.util.List;

import net.customware.gwt.dispatch.shared.Action;

public class DeletePhotos implements Action<VoidResult>, Serializable {

    private static final long serialVersionUID = -8353337263892135688L;
    private final String tagId;
    private final List<PhotoInfo> toBoDeleted;

    public DeletePhotos(String tagId, List<PhotoInfo> toBoDeleted) {
        super();
        this.tagId = tagId;
        this.toBoDeleted = toBoDeleted;
    }

    public String getTagId() {
        return tagId;
    }

    public List<PhotoInfo> getToBoDeleted() {
        return toBoDeleted;
    }
}