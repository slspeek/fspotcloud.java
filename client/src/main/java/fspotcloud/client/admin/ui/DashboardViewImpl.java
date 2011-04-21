package fspotcloud.client.admin.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class DashboardViewImpl extends Composite {

	private static DashboardViewImplUiBinder uiBinder = GWT
			.create(DashboardViewImplUiBinder.class);

	interface DashboardViewImplUiBinder extends
			UiBinder<Widget, DashboardViewImpl> {
	}

	public DashboardViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
