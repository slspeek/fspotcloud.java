package com.googlecode.fspotcloud.server.admin.actions;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.server.SimpleActionHandler;
import net.customware.gwt.dispatch.shared.ActionException;
import net.customware.gwt.dispatch.shared.DispatchException;

import com.google.inject.Inject;
import com.googlecode.botdispatch.SerializableAsyncCallback;

import com.googlecode.botdispatch.controller.dispatch.ControllerDispatchAsync;
import com.googlecode.fspotcloud.server.control.callback.PeerMetaDataCallback;
import com.googlecode.fspotcloud.shared.dashboard.actions.SynchronizePeer;
import com.googlecode.fspotcloud.shared.dashboard.actions.VoidResult;
import com.googlecode.fspotcloud.shared.peer.rpc.actions.GetPeerMetaData;
import com.googlecode.fspotcloud.shared.peer.rpc.actions.PeerMetaDataResult;
import com.googlecode.fspotcloud.user.AdminPermission;

public class SynchronizePeerHandler extends SimpleActionHandler<SynchronizePeer, VoidResult> {

    final private ControllerDispatchAsync dispatch;
    private final AdminPermission adminPermission;

    @Inject
    public SynchronizePeerHandler(ControllerDispatchAsync dispatch, AdminPermission adminPermission) {
        super();
        this.dispatch = dispatch;
        this.adminPermission = adminPermission;
    }

    @Override
    public VoidResult execute(SynchronizePeer action, ExecutionContext context)
            throws DispatchException {
        adminPermission.chechAdminPermission();
        try {
            GetPeerMetaData metaAction = new GetPeerMetaData();
            SerializableAsyncCallback<PeerMetaDataResult> callback = new PeerMetaDataCallback(null, null);
            dispatch.execute(metaAction, callback);
        } catch (Exception e) {
            throw new ActionException(e);
        }
        return new VoidResult();
    }
}