package fspotcloud.client.main.ui;

import com.reveregroup.gwt.imagepreloader.FitImage;

import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasOneWidget;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

import fspotcloud.client.view.ImageView;

public class ImageViewImpl extends ResizeComposite implements ImageView {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(ImageViewImpl.class
			.getName());
	
	private static ImageViewImplUiBinder uiBinder = GWT
			.create(ImageViewImplUiBinder.class);

	interface ImageViewImplUiBinder extends UiBinder<Widget, ImageViewImpl> {
	}

	@UiField
	HTMLPanel mainPanel;
	@UiField
	FitImage image;
	@UiField
	SimplePanel pagerViewPanel;
	@UiField
	SimplePanel slideshowViewPanel;

	
	public ImageViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		//log.info("Created");
		mainPanel.addStyleName("fsc-image-view");
		mainPanel.ensureDebugId("fcs");
		image.addStyleName("fsc-image-view-image");
		image.ensureDebugId("fcs");
		pagerViewPanel.addStyleName("fsc-image-view-pager-container");
		pagerViewPanel.ensureDebugId("fsc");
		slideshowViewPanel.addStyleName("fsc-image-view-slideshow-container");
		slideshowViewPanel.ensureDebugId("fsc");
		int width = Window.getClientWidth();
		int height = Window.getClientHeight();
		image.setMaxSize(width, height);
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
