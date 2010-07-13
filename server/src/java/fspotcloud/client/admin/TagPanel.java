package fspotcloud.client.admin;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.Tree;

import fspotcloud.client.tree.TagServiceAsync;

public class TagPanel extends DockLayoutPanel {
	
	private final TagServiceAsync tagService;
	private final AdminServiceAsync adminService;
	

	public TagPanel(TagServiceAsync tagService, AdminServiceAsync adminService) {
		super(Unit.PX);
		this.tagService = tagService;
		this.adminService = adminService;
	}
 
	void buildUI() {
	
	}
	
}
