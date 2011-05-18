package fspotcloud.client.main.ui;

import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.Widget;
import com.reveregroup.gwt.imagepreloader.FitImage;

import fspotcloud.client.view.ImageView;

public class ImageViewImpl extends ResizeComposite implements ImageView {

	private static final Logger log = Logger.getLogger(ImageViewImpl.class
			.getName());

	private static ImageViewImplUiBinder uiBinder = GWT
			.create(ImageViewImplUiBinder.class);

	interface ImageViewImplUiBinder extends UiBinder<Widget, ImageViewImpl> {
	}

	@UiField
	FitImage image;

	public ImageViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		image.ensureDebugId("image-view");
		int width = (int) (Window.getClientWidth() * 0.7);
		int height = (int) (Window.getClientHeight() * 0.7);
		image.setMaxSize(width, height);
	}

	@Override
	public void setImageUrl(String url) {
		log.info("About to setImage: " + url);
		image.setUrl(url);
	}
}
