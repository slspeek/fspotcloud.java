package fspotcloud.shared.peer.rpc.actions;

import java.io.Serializable;

import net.customware.gwt.dispatch.shared.Result;

public class ImageDataResult implements Result, Serializable {

	private static final long serialVersionUID = 4865937115915320943L;
	private byte[] imageBinary;
	private String exif;

	public ImageDataResult(byte[] imageBinary, String exif) {
		super();
		this.imageBinary = imageBinary;
		this.exif = exif;
	}

	public byte[] getImageBinary() {
		return imageBinary;
	}

	public String getExif() {
		return exif;
	}

}
