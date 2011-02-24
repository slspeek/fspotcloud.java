package fspotcloud.client.main.ui;

import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasOneWidget;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

import fspotcloud.client.view.ImageView;

public class ImageViewImpl extends ResizeComposite implements ImageView {

	private static final Logger log = Logger.getLogger(ImageViewImpl.class
			.getName());
	
	private static ImageViewImplUiBinder uiBinder = GWT
			.create(ImageViewImplUiBinder.class);

	interface ImageViewImplUiBinder extends UiBinder<Widget, ImageViewImpl> {
	}

	@UiField
	HTMLPanel mainPanel;
	@UiField
	Image image;
	@UiField
	SimplePanel pagerViewPanel;
	@UiField
	SimplePanel slideshowViewPanel;

	
	public ImageViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		log.info("With extra styles created");
		mainPanel.addStyleName("fsc-image-view");
		image.addStyleName("fsc-image-view-image");
		pagerViewPanel.addStyleName("fsc-image-view-pager-container");
		slideshowViewPanel.addStyleName("fsc-image-view-slideshow-container");
	}
	
	@Override
	public void setImageUrl(String url) {
		image.setUrl(url);
	}

	@Override
	public HasOneWidget getPagerViewContainer() {
		return pagerViewPanel;
	}
	
	@Override
	public HasOneWidget getSlideshowViewContainer() {
		return slideshowViewPanel;
	}
}
