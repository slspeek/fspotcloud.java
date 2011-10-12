package fspotcloud.peer.handlers;

import java.sql.SQLException;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.server.SimpleActionHandler;
import net.customware.gwt.dispatch.shared.ActionException;
import net.customware.gwt.dispatch.shared.DispatchException;

import com.google.inject.Inject;

import fspotcloud.peer.db.Data;
import fspotcloud.shared.peer.rpc.actions.GetPeerMetaData;
import fspotcloud.shared.peer.rpc.actions.PeerMetaDataResult;

public class GetPeerMetaDataHandler extends SimpleActionHandler<GetPeerMetaData, PeerMetaDataResult> {

	final private Data data;
	
	@Inject
	public GetPeerMetaDataHandler(Data data) {
		super();
		this.data = data;
	}
	@Override
	public PeerMetaDataResult execute(GetPeerMetaData action,
			ExecutionContext context) throws DispatchException {
		PeerMetaDataResult result;
		try {
			Object[] resultArray= data.getMetaData();
			result = new PeerMetaDataResult((Integer)resultArray[1], (Integer)resultArray[0]);
		} catch (SQLException e) {
			throw new ActionException(e);
		}
		return result;
	}

}
