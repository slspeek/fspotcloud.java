package fspotcloud.client.main.ui;

import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

import fspotcloud.client.view.ImageView;
import fspotcloud.client.view.ImagePanelView;
import fspotcloud.client.view.PagerView;
import fspotcloud.client.view.SlideshowView;

public class ImagePanelViewImpl extends ResizeComposite implements
		ImagePanelView {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(ImagePanelViewImpl.class
			.getName());

	private static ImageViewPanelImplUiBinder uiBinder = GWT
			.create(ImageViewPanelImplUiBinder.class);

	interface ImageViewPanelImplUiBinder extends
			UiBinder<Widget, ImagePanelViewImpl> {
	}

	final private ImageView imageView;
	final private PagerView pagerView;
	final private SlideshowView slideshowView;

	@Inject
	public ImagePanelViewImpl(ImageView imageView, PagerView pagerView,
			SlideshowView slideshowView) {
		this.imageView = imageView;
		this.pagerView = pagerView;
		this.slideshowView = slideshowView;
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	@UiFactory
	public ImageViewImpl getImageView() {
		return (ImageViewImpl) imageView;
	}
	
	@UiFactory
	public PagerViewImpl getPagerView() {
		return (PagerViewImpl) pagerView;
	}
	
	@UiFactory 
	public SlideshowViewImpl getSlideshowView(){
		return (SlideshowViewImpl) slideshowView;
	}

}
