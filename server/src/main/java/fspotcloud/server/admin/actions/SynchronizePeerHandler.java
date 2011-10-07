package fspotcloud.server.admin.actions;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.server.SimpleActionHandler;
import net.customware.gwt.dispatch.shared.ActionException;
import net.customware.gwt.dispatch.shared.DispatchException;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

import fspotcloud.botdispatch.controller.dispatch.ControllerDispatchAsync;
import fspotcloud.server.control.callback.PeerMetaDataCallback;
import fspotcloud.shared.dashboard.actions.SynchronizePeer;
import fspotcloud.shared.dashboard.actions.VoidResult;
import fspotcloud.shared.peer.rpc.actions.GetPeerMetaData;
import fspotcloud.shared.peer.rpc.actions.PeerMetaDataResult;

public class SynchronizePeerHandler extends
		SimpleActionHandler<SynchronizePeer, VoidResult> {

	final private ControllerDispatchAsync dispatch;

	@Inject
	public SynchronizePeerHandler(ControllerDispatchAsync dispatch) {
		super();
		this.dispatch = dispatch;
	}
	
	@Override
	public VoidResult execute(SynchronizePeer action, ExecutionContext context)
			throws DispatchException {
		try {
			GetPeerMetaData metaAction = new GetPeerMetaData();
			AsyncCallback<PeerMetaDataResult> callback = new PeerMetaDataCallback(null, null);
			dispatch.execute(metaAction, callback);
		} catch (Exception e) {
			throw new ActionException(e);
		}
		return new VoidResult();
	}

}