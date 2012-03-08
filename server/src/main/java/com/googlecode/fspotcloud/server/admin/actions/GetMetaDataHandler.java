package com.googlecode.fspotcloud.server.admin.actions;

import java.util.logging.Logger;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.server.SimpleActionHandler;
import net.customware.gwt.dispatch.shared.ActionException;
import net.customware.gwt.dispatch.shared.DispatchException;

import com.google.inject.Inject;

import com.googlecode.botdispatch.model.api.Commands;
import com.googlecode.fspotcloud.server.model.api.PeerDatabase;
import com.googlecode.fspotcloud.server.model.api.PeerDatabases;
import com.googlecode.fspotcloud.shared.admin.GetMetaDataResult;
import com.googlecode.fspotcloud.shared.dashboard.actions.GetMetaData;
import com.googlecode.fspotcloud.user.AdminPermission;

public class GetMetaDataHandler extends SimpleActionHandler<GetMetaData, GetMetaDataResult> {

    @SuppressWarnings("unused")
    private static final Logger log = Logger.getLogger(GetMetaDataHandler.class.getName());
    final private Commands commandManager;
    final private PeerDatabases defaultPeer;
    final private AdminPermission adminPermission;

    @Inject
    public GetMetaDataHandler(Commands commandManager,
            PeerDatabases defaultPeer, AdminPermission adminPermission) {
        super();
        this.commandManager = commandManager;
        this.defaultPeer = defaultPeer;
        this.adminPermission = adminPermission;
    }

    @Override
    public GetMetaDataResult execute(GetMetaData action,
            ExecutionContext context) throws DispatchException {
        adminPermission.chechAdminPermission();
        GetMetaDataResult dataInfo = new GetMetaDataResult();
        try {
            PeerDatabase peerDatabase = defaultPeer.get();
            dataInfo.setInstanceName(peerDatabase.getPeerName());
            dataInfo.setPeerLastSeen(peerDatabase.getPeerLastContact());
            dataInfo.setPeerPhotoCount(peerDatabase.getPeerPhotoCount());
            dataInfo.setPhotoCount(peerDatabase.getPhotoCount());
            dataInfo.setTagCount(peerDatabase.getTagCount());
            dataInfo.setPendingCommandCount(getPendingCommandCount());
        } catch (Exception e) {
            throw new ActionException(e);
        }
        return dataInfo;
    }

    private int getPendingCommandCount() {
        int result = commandManager.getCountUnderAThousend();
        return result;
    }
}