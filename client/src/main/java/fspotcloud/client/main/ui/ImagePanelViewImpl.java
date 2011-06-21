package fspotcloud.client.main.ui;

import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.inject.Inject;

import fspotcloud.client.main.view.api.ImagePanelView;
import fspotcloud.client.main.view.api.ImageRasterView;
import fspotcloud.client.main.view.api.PagerView;
import fspotcloud.client.main.view.api.SlideshowView;

public class ImagePanelViewImpl extends ResizeComposite implements
		ImagePanelView {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(ImagePanelViewImpl.class
			.getName());

	private static ImageViewPanelImplUiBinder uiBinder = GWT
			.create(ImageViewPanelImplUiBinder.class);

	interface ImageViewPanelImplUiBinder extends
			UiBinder<DockLayoutPanel, ImagePanelViewImpl> {
	}

	final private ImageRasterView imageRasterView;
	final private PagerView pagerView;
	final private SlideshowView slideshowView;
	@UiField
	DockLayoutPanel mainPanel;
	@UiField
	HTMLPanel buttonPanel;

	@Inject
	public ImagePanelViewImpl(ImageRasterView imageRasterView, PagerView pagerView,
			SlideshowView slideshowView) {
		this.imageRasterView = imageRasterView;
		this.pagerView = pagerView;
		this.slideshowView = slideshowView;
		initWidget(uiBinder.createAndBindUi(this));
		mainPanel.addStyleName("fsc-image-panel-view");
		buttonPanel.addStyleName("fsc-image-button-panel");
	}

	@UiFactory
	public ImageRasterViewImpl getImageRasterView() {
		return (ImageRasterViewImpl) imageRasterView;
	}

	@UiFactory
	public PagerViewImpl getPagerView() {
		return (PagerViewImpl) pagerView;
	}

	@UiFactory
	public SlideshowViewImpl getSlideshowView() {
		return (SlideshowViewImpl) slideshowView;
	}

	public void setSize(int width, int height) {
		//mainPanel.setPixelSize(width, height);
	}
}
