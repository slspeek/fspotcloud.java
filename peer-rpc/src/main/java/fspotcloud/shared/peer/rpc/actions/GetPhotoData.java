package fspotcloud.shared.peer.rpc.actions;

import java.io.Serializable;

import net.customware.gwt.dispatch.shared.Action;

public class GetPhotoData implements Action<PhotoDataResult>, Serializable {

	private static final long serialVersionUID = 3730836943695700527L;

	private String tagId;
	private String minKey;
	private int offset;
	private int count;
	private int bigWidth;
	private int bigHeight;
	private int thumbWidth;
	private int thumbHeigth;

	public GetPhotoData(String tagId, String minKey, int offset, int count,
			int bigWidth, int bigHeight, int thumbWidth, int thumbHeigth) {
		super();
		this.tagId = tagId;
		this.minKey = minKey;
		this.offset = offset;
		this.count = count;
		this.bigWidth = bigWidth;
		this.bigHeight = bigHeight;
		this.thumbWidth = thumbWidth;
		this.thumbHeigth = thumbHeigth;
	}

	public String getMinKey() {
		return minKey;
	}

	public String getTagId() {
		return tagId;
	}

	public int getOffset() {
		return offset;
	}

	public int getCount() {
		return count;
	}

	public int getBigWidth() {
		return bigWidth;
	}

	public int getBigHeight() {
		return bigHeight;
	}

	public int getThumbWidth() {
		return thumbWidth;
	}

	public int getThumbHeigth() {
		return thumbHeigth;
	}

}
