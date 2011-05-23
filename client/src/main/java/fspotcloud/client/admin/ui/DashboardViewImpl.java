package fspotcloud.client.admin.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasOneWidget;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

import fspotcloud.client.admin.view.api.DashboardView;
import fspotcloud.client.admin.view.api.GlobalActionsView;
import fspotcloud.client.main.ui.TreeViewImpl;
import fspotcloud.client.main.view.api.TreeView;

public class DashboardViewImpl extends Composite implements DashboardView{

	private static DashboardViewImplUiBinder uiBinder = GWT
			.create(DashboardViewImplUiBinder.class);

	interface DashboardViewImplUiBinder extends
			UiBinder<Widget, DashboardViewImpl> {
	}
	
	@UiField
	SimplePanel tagDetailsViewContainer;
	
	GlobalActionsView globalActionsView;
	TreeView treeView;
	
	@Inject
	public DashboardViewImpl(TreeView treeView, GlobalActionsView globalActionsView) {
		this.treeView = treeView;
		this.globalActionsView = globalActionsView;
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	@UiFactory
	public GlobalActionsView getGlobalActionsView() {
		return globalActionsView;
	}

	@Override
	public HasOneWidget getTagDetailsContainer() {
		return tagDetailsViewContainer;
	}
	
	@UiFactory
	public TreeViewImpl getTreeView() {
		return (TreeViewImpl) treeView;
	}
}
