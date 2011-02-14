package fspotcloud.client.main.ui;

import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

import fspotcloud.client.view.ImageView;
import fspotcloud.client.view.PagerView;

public class ImageViewImpl extends ResizeComposite implements ImageView {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(ImageViewImpl.class
			.getName());
	
	private static ImageViewImplUiBinder uiBinder = GWT
			.create(ImageViewImplUiBinder.class);

	interface ImageViewImplUiBinder extends UiBinder<Widget, ImageViewImpl> {
	}

	@SuppressWarnings("unused")
	private ImageView.ImagePresenter presenter;

	@UiField
	HTMLPanel mainPanel;
	@UiField
	Image image;
	
	final private PagerView pagerView;

	@Inject
	public ImageViewImpl(PagerView pagerView) {
		this.pagerView = pagerView;
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	@UiFactory 
	PagerView getPagerView() {
		log.info("GetPagetView");
		return pagerView;
		 
	}
	
	public PagerView.PagerPresenter getPagerPresenter() {
		return pagerView.getPagerPresenter();
	}
	
	@Override
	public void setPresenter(ImagePresenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public void setImageUrl(String url) {
		image.setUrl(url);
	}
}
