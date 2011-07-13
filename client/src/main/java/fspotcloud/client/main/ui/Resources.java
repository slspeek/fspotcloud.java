package fspotcloud.client.main.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public interface Resources extends ClientBundle {
	
	Resources INSTANCE = GWT.create(Resources.class);
	
	@Source("images/Play.png")
	ImageResource playIcon();
}
