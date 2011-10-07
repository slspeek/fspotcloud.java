package fspotcloud.shared.peer.rpc.actions;

import java.io.Serializable;

import net.customware.gwt.dispatch.shared.Action;


public class GetPhotoData  implements Action<PhotoDataResult>, Serializable {

	private static final long serialVersionUID = 3730836943695700527L;

	private int offset;
	private int count;
	
	public GetPhotoData(int offset, int count) {
		super();
		this.offset = offset;
		this.count = count;
	}
	
	public int getOffset() {
		return offset;
	}
	public int getCount() {
		return count;
	}
}
