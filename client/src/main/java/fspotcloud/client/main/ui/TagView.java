package fspotcloud.client.main.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

public class TagView extends Composite {

	private static TagViewUiBinder uiBinder = GWT.create(TagViewUiBinder.class);

	interface TagViewUiBinder extends UiBinder<Widget, TagView> {
	}

	@UiField
	DockLayoutPanel mainPanel;
	
	@UiField
	FlowPanel statusPanel;
	
	

	public TagView() {
		initWidget(uiBinder.createAndBindUi(this));
	}


}
