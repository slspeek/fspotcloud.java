package fspotcloud.server.actions;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.server.SimpleActionHandler;
import net.customware.gwt.dispatch.shared.DispatchException;

import com.google.inject.Inject;

import fspotcloud.server.model.api.Commands;
import fspotcloud.server.model.api.PeerDatabase;
import fspotcloud.server.model.api.PeerDatabases;
import fspotcloud.shared.actions.GetMetaData;
import fspotcloud.shared.admin.GetMetaDataResult;

public class GetMetaDataHandler extends SimpleActionHandler<GetMetaData, GetMetaDataResult> {


	final private Commands commandManager;
	final private PeerDatabases defaultPeer;
	
	@Inject
	private GetMetaDataHandler(Commands commandManager,
			PeerDatabases defaultPeer) {
		super();
		this.commandManager = commandManager;
		this.defaultPeer = defaultPeer;
	}


	@Override
	public GetMetaDataResult execute(GetMetaData action,
			ExecutionContext context) throws DispatchException {
		PeerDatabase peerDatabase = defaultPeer.get();
		GetMetaDataResult dataInfo = new GetMetaDataResult();
		dataInfo.setInstanceName(peerDatabase.getPeerName());
		dataInfo.setPeerLastSeen(peerDatabase.getPeerLastContact());
		dataInfo.setPeerPhotoCount(peerDatabase.getPeerPhotoCount());
		dataInfo.setPhotoCount(peerDatabase.getPhotoCount());
		dataInfo.setTagCount(peerDatabase.getTagCount());
		dataInfo.setPendingCommandCount(getPendingCommandCount());
		return dataInfo;
	}

		
	private int getPendingCommandCount() {
		int result = commandManager.getCountUnderAThousend();
		return result;
	}

    
}