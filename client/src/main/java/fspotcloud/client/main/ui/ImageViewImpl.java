package fspotcloud.client.main.ui;

import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.reveregroup.gwt.imagepreloader.FitImage;

import fspotcloud.client.main.view.api.ImageView;

public class ImageViewImpl extends Composite implements ImageView {

	private static final Logger log = Logger.getLogger(ImageViewImpl.class
			.getName());

	private static ImageViewImplUiBinder uiBinder = GWT
			.create(ImageViewImplUiBinder.class);

	interface ImageViewImplUiBinder extends UiBinder<Widget, ImageViewImpl> {
	}

	@UiField
	FitImage image;

	private ImageView.ImagePresenter presenter;

	@Inject
	public ImageViewImpl(@Assisted String location) {
		initWidget(uiBinder.createAndBindUi(this));
		image.ensureDebugId("image-view-" + location);
	}

	@Override
	public void setImageUrl(String url) {
		image.setUrl(url);
	}

	@UiHandler("image")
	public void imageClicked(ClickEvent event) {
		log.info("image clicked");
		if(event.isShiftKeyDown()){
			presenter.imageDoubleClicked();
		} else {
			this.presenter.imageClicked();
		}
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

	@Override
	public void setTooltip(String text) {
		asWidget().setTitle(text);
	}
}
