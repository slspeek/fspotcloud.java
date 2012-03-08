package com.googlecode.fspotcloud.client.place;

import com.googlecode.fspotcloud.client.place.api.Raster;
import com.googlecode.fspotcloud.client.place.api.RasterSetter;

public class RasterState implements Raster, RasterSetter {

	private int width;
	private int height;
	@Override
	public void setColumnCount(int width) {
		this.width = width;
		}

	@Override
	public void setRowCount(int height) {
		this.height = height;

	}

	@Override
	public int getRowCount() {
		return height;
	}

	@Override
	public int getColumnCount() {
		return width;
	}

}
