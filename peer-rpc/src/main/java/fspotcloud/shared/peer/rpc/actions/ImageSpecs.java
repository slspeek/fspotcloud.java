package fspotcloud.shared.peer.rpc.actions;

import java.io.Serializable;

public class ImageSpecs implements Serializable {

	private static final long serialVersionUID = 5812879917430846998L;
	final private int width;
	final private int height;
	final private int thumbWidth;
	final private int thumbHeight;
	public ImageSpecs(int width, int height, int thumbWidth, int thumbHeight) {
		super();
		this.width = width;
		this.height = height;
		this.thumbWidth = thumbWidth;
		this.thumbHeight = thumbHeight;
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public int getThumbWidth() {
		return thumbWidth;
	}
	public int getThumbHeight() {
		return thumbHeight;
	}
	
}
