package fspotcloud.peer.handlers;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.server.SimpleActionHandler;
import net.customware.gwt.dispatch.shared.ActionException;
import net.customware.gwt.dispatch.shared.DispatchException;

import com.google.inject.Inject;

import fspotcloud.peer.db.Data;
import fspotcloud.shared.peer.rpc.actions.GetPhotoData;
import fspotcloud.shared.peer.rpc.actions.PhotoDataResult;

public class GetPhotoDataHandler extends
		SimpleActionHandler<GetPhotoData, PhotoDataResult> {

	final private Data data;

	@Inject
	public GetPhotoDataHandler(Data data) {
		super();
		this.data = data;
	}

	@Override
	public PhotoDataResult execute(GetPhotoData action, ExecutionContext context)
			throws DispatchException {
		PhotoDataResult result;
		try {
			result = new PhotoDataResult(data.getPhotoData(action.getImageSpecs(), action.getImageKeys()));
		} catch (Exception e) {
			throw new ActionException(e);
		}
		return result;
	}
}
