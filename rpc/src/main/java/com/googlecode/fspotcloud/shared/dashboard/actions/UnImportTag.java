package com.googlecode.fspotcloud.shared.dashboard.actions;

import java.io.Serializable;
import net.customware.gwt.dispatch.shared.Action;

public class UnImportTag implements Action<VoidResult>, Serializable {

    UnImportTag() {
    }
    private String tagId;

    public UnImportTag(String tagId) {
        super();
        this.tagId = tagId;
    }

    public String getTagId() {
        return tagId;
    }
}