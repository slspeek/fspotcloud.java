package fspotcloud.server.admin.actions;

import java.util.logging.Logger;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.server.SimpleActionHandler;
import net.customware.gwt.dispatch.shared.ActionException;
import net.customware.gwt.dispatch.shared.DispatchException;

import com.google.inject.Inject;

import fspotcloud.botdispatch.model.api.Commands;
import fspotcloud.server.mapreduce.MapReduceInfo;
import fspotcloud.server.model.api.PeerDatabase;
import fspotcloud.server.model.api.PeerDatabases;
import fspotcloud.shared.admin.GetMetaDataResult;
import fspotcloud.shared.dashboard.actions.GetMetaData;

public class GetMetaDataHandler extends
		SimpleActionHandler<GetMetaData, GetMetaDataResult> {
	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(GetMetaDataHandler.class
			.getName());
	final private Commands commandManager;
	final private PeerDatabases defaultPeer;
	private MapReduceInfo mapreduceInfo;

	@Inject
	public GetMetaDataHandler(Commands commandManager,
			PeerDatabases defaultPeer, MapReduceInfo mapreduceInfo) {
		super();
		this.mapreduceInfo = mapreduceInfo;
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
			dataInfo.setDeletePhotosActive(mapreduceInfo.activeCount("Delete All Mapper") > 0);
			dataInfo.setCountPhotosActive(mapreduceInfo.activeCount("Entity Counter Mapper")> 0);
			dataInfo.setDeleteTagsActive(mapreduceInfo.activeCount("Delete All Tags Mappper")>0);
			dataInfo.setImportImagesActive(mapreduceInfo.activeCount("Image Data Import Mapper")>0);
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