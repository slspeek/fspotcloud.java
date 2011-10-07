package fspotcloud.peer.handlers;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.server.SimpleActionHandler;
import net.customware.gwt.dispatch.shared.ActionException;
import net.customware.gwt.dispatch.shared.DispatchException;

import com.google.inject.Inject;

import fspotcloud.peer.db.Data;
import fspotcloud.shared.peer.rpc.actions.GetImageData;
import fspotcloud.shared.peer.rpc.actions.ImageDataResult;

public class GetImageDataHandler extends SimpleActionHandler<GetImageData, ImageDataResult> {

	final private Data data;
	
	@Inject
	public GetImageDataHandler(Data data) {
		super();
		this.data = data;
	}

	@Override
	public ImageDataResult execute(GetImageData action, ExecutionContext context)
			throws DispatchException {
		ImageDataResult result;
		try {
			Object[] resultArray= data.getImageData(action.getPhotoId(), action.getWidth(), action.getHeight(), action.getType());
			result = new ImageDataResult((byte[])resultArray[2], (String)resultArray[1]);
		} catch (Exception e) {
			throw new ActionException(e);
		}
		return result;
	}
	
	
}
