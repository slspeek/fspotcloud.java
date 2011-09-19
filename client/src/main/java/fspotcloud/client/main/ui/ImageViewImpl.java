package fspotcloud.client.main.ui;

import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.DoubleClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.reveregroup.gwt.imagepreloader.FitImage;
import com.reveregroup.gwt.imagepreloader.FitImageLoadEvent;
import com.reveregroup.gwt.imagepreloader.FitImageLoadHandler;

import fspotcloud.client.main.view.api.ImageView;

public class ImageViewImpl extends ResizeComposite implements ImageView {

	private static final Logger log = Logger.getLogger(ImageViewImpl.class
			.getName());

	private static ImageViewImplUiBinder uiBinder = GWT
			.create(ImageViewImplUiBinder.class);

	interface ImageViewImplUiBinder extends
			UiBinder<LayoutPanel, ImageViewImpl> {
	}

	@UiField
	Label info;
	@UiField
	FitImage image;
	@UiField
	LayoutPanel layout;

	private ImageView.ImagePresenter presenter;

	@Inject
	public ImageViewImpl(@Assisted String location) {
		initWidget(uiBinder.createAndBindUi(this));
		image.ensureDebugId("image-view-" + location);
		image.setVisible(false);
		image.addFitImageLoadHandler(new FitImageLoadHandler() {
			
			@Override
			public void imageLoaded(FitImageLoadEvent event) {
				image.setVisible(true);
				
			}
		});
	}

	@Override
	public void setImageUrl(String url) {
		image.setUrl(url);
	}

	@UiHandler("image")
	public void imageClicked(ClickEvent event) {
		log.info("image clicked");
		this.presenter.imageClicked();
	}

	@UiHandler("info")
	public void infoClicked(DoubleClickEvent event) {
		log.info("label clicked");
		presenter.imageDoubleClicked();

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
		info.setText(text);
	}

	@Override
	public void onResize() {
		log.info("Onresize");
		super.onResize();
	}

	public void setVisible(boolean visible) {
		//image.setVisible(visible);
	}
}
