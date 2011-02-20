package fspotcloud.client.main.ui;

import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
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

	@SuppressWarnings("unused")
	private ImageView.ImagePresenter presenter;

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
	}
	@Override
	public void setPresenter(ImagePresenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public void setImageUrl(String url) {
		image.setUrl(url);
	}

	@Override
	public AcceptsOneWidget getPagerViewContainer() {
		return pagerViewPanel;
	}
	
	@Override
	public AcceptsOneWidget getSlideshowViewContainer() {
		return pagerViewPanel;
	}
}
