package fspotcloud.client.main.ui;

import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.Widget;
import com.reveregroup.gwt.imagepreloader.FitImage;

import fspotcloud.client.main.view.api.ImageView;

public class ImageViewImpl extends ResizeComposite implements ImageView {

	private static final Logger log = Logger.getLogger(ImageViewImpl.class
			.getName());

	private static int counter = 0;
	private static ImageViewImplUiBinder uiBinder = GWT
			.create(ImageViewImplUiBinder.class);

	interface ImageViewImplUiBinder extends UiBinder<Widget, ImageViewImpl> {
	}

	@UiField
	FitImage image;

	private ImageView.ImagePresenter presenter;

	public ImageViewImpl(String dim) {
		initWidget(uiBinder.createAndBindUi(this));
		image.ensureDebugId("image-view-" + dim);
	}

	@Override
	public void setImageUrl(String url) {
		log.info("About to setImage: " + url);
		image.setUrl(url);
	}

	@UiHandler("image")
	public void imageClicked(ClickEvent event) {
		log.info("image clicked");
		this.presenter.imageClicked();
	}

	@Override
	public void setPresenter(ImagePresenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public void setMaxWidth(int width) {
		this.image.setMaxWidth(width);
		
	}

	@Override
	public void setMaxHeight(int height) {
		this.image.setMaxHeight(height);
		
	}

}
