package fspotcloud.client.main.ui;

import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.MouseWheelEvent;
import com.google.gwt.event.dom.client.MouseWheelHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.inject.Inject;

import fspotcloud.client.main.view.api.ButtonPanelView;
import fspotcloud.client.main.view.api.ImagePanelView;
import fspotcloud.client.main.view.api.ImagePanelView.ImagePanelPresenter;
import fspotcloud.client.main.view.api.ImageRasterView;
import fspotcloud.client.main.view.api.SlideshowView;

public class ImagePanelViewImpl extends ResizeComposite implements
		ImagePanelView{

	private static final Logger log = Logger.getLogger(ImagePanelViewImpl.class
			.getName());

	private static ImageViewPanelImplUiBinder uiBinder = GWT
			.create(ImageViewPanelImplUiBinder.class);

	interface ImageViewPanelImplUiBinder extends
			UiBinder<DockLayoutPanel, ImagePanelViewImpl> {
	}

	final private ImageRasterView imageRasterView;
	final private SlideshowView slideshowView;
	final private ButtonPanelView buttonPanelView;
	@UiField
	DockLayoutPanel mainPanel;

	private ImagePanelPresenter presenter;
	
	@Inject
	public ImagePanelViewImpl(ImageRasterView imageRasterView,
			SlideshowView slideshowView,
			ButtonPanelView buttonPanelView) {
		this.buttonPanelView = buttonPanelView;
		this.imageRasterView = imageRasterView;
		this.slideshowView = slideshowView;
		initWidget(uiBinder.createAndBindUi(this));
		mainPanel.addStyleName("fsc-image-panel-view");
		imageRasterView.asWidget().addStyleName("fsc-image-raster-view");
		
	}

	@UiFactory
	public ImageRasterViewImpl getImageRasterView() {
		return (ImageRasterViewImpl) imageRasterView;
	}

	@UiFactory
	public SlideshowViewImpl getSlideshowView() {
		return (SlideshowViewImpl) slideshowView;
	}

	@UiFactory
	public ButtonPanelViewImpl getButtonPanelView() {
		return (ButtonPanelViewImpl) buttonPanelView;
	}

	@Override
	public void onResize() {
		log.info("ON RESIZE to super");
		super.onResize();
	}

	

	@Override
	public void setPresenter(ImagePanelPresenter presenter) {
		this.presenter = presenter;
		
	}
}
