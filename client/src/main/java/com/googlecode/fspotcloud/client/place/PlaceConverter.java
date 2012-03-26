package com.googlecode.fspotcloud.client.place;

public class PlaceConverter {

	private String tagId;
	

	private String photoId;
	private int columnCount;
	private int rowCount;
	
	public PlaceConverter(BasePlace delegate) {
		this.tagId = delegate.getTagId();
		this.photoId = delegate.getPhotoId();
		this.columnCount = delegate.getColumnCount();
		this.rowCount = delegate.getRowCount();
	}
	
	public BasePlace getNewPlace() {
		return new BasePlace(tagId, photoId, columnCount, rowCount);
	}
	
	void setTagId(String tagId) {
		this.tagId = tagId;
	}
	
	public void setPhotoId(String photoId) {
		this.photoId = photoId;
	}

	public void setColumnCount(int columnCount) {
		this.columnCount = columnCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

}