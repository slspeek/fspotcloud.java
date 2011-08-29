package fspotcloud.server.admin.actions;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.server.SimpleActionHandler;
import net.customware.gwt.dispatch.shared.ActionException;
import net.customware.gwt.dispatch.shared.DispatchException;

import com.google.inject.Inject;

import fspotcloud.server.model.api.Commands;
import fspotcloud.server.model.api.PeerDatabase;
import fspotcloud.server.model.api.PeerDatabases;
import fspotcloud.shared.admin.GetMetaDataResult;
import fspotcloud.shared.dashboard.actions.GetMetaData;

public class GetMetaDataHandler extends
		SimpleActionHandler<GetMetaData, GetMetaDataResult> {

	final private Commands commandManager;
	final private PeerDatabases defaultPeer;

	@Inject
	public GetMetaDataHandler(Commands commandManager,
			PeerDatabases defaultPeer) {
		super();
		this.commandManager = commandManager;
		this.defaultPeer = defaultPeer;
	}

	@Override
	public GetMetaDataResult execute(GetMetaData action,
			ExecutionContext context) throws DispatchException {
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