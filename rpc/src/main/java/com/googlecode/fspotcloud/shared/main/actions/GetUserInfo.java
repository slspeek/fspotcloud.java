package com.googlecode.fspotcloud.shared.main.actions;

import net.customware.gwt.dispatch.shared.Action;

public class GetUserInfo implements Action<UserInfo> {
     
    private String destinationUrl;

    public GetUserInfo(){}
    
    public GetUserInfo(String destinationUrl) {
        this.destinationUrl = destinationUrl;
    }

    public String getDestinationUrl() {
        return destinationUrl;
    }
}