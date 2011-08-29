package fspotcloud.shared.dashboard.actions;

import net.customware.gwt.dispatch.shared.Action;

public class ImportTag implements Action<VoidResult> {

	ImportTag() {
		
	}
    private String tagId;
	
    public ImportTag(String tagId) {
		super();
		this.tagId = tagId;
	}

	public String getTagId() {
		return tagId;
	}
    
    
  
}