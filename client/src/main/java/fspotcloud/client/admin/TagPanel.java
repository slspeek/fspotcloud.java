package fspotcloud.client.admin;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FlexTable;

import fspotcloud.client.main.ui.TreePanel;
import fspotcloud.rpc.AdminServiceAsync;
import fspotcloud.rpc.TagServiceAsync;

public class TagPanel extends DockLayoutPanel {
	
	private final TagServiceAsync tagService;
	private final AdminServiceAsync adminService;
	private final TreePanel treePanel;
	private final FlexTable table = new FlexTable();
	 
	
	public TagPanel(TagServiceAsync tagService, AdminServiceAsync adminService,
			TreePanel treePanel) {
		super(Unit.PX);
		this.tagService = tagService;
		this.adminService = adminService;
		this.treePanel = treePanel;
		buildUI();
	}
 
	void buildUI() {
		addWest(treePanel, 300);
		table.setText(0, 0, "Label details");
		add(table);
	}
	
}
