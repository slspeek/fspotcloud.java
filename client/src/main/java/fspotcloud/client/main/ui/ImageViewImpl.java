package fspotcloud.client.main.ui;

import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.MouseMoveEvent;
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
import fspotcloud.client.main.view.api.TimerInterface;

public class ImageViewImpl extends ResizeComposite implements ImageView {

	private static final Logger log = Logger.getLogger(ImageViewImpl.class
			.getName());

	private static ImageViewImplUiBinder uiBinder = GWT
			.create(ImageViewImplUiBinder.class);

	interface ImageViewImplUiBinder extends
			UiBinder<LayoutPanel, ImageViewImpl> {
	}

	private final TimerInterface timer;
	@UiField
	Label info;
	@UiField
	FitImage image;
	@UiField
	LayoutPanel layout;

	private ImageView.ImagePresenter presenter;

	@Inject
	public ImageViewImpl(@Assisted String location, TimerInterface timer) {
		this.timer = timer;
		initWidget(uiBinder.createAndBindUi(this));
		image.ensureDebugId("image-view-" + location);
		image.setVisible(false);
		image.addFitImageLoadHandler(new FitImageLoadHandler() {

			@Override
			public void imageLoaded(FitImageLoadEvent event) {
				image.setVisible(true);

			}
		});
		layout.animate(500);
	}

	@Override
	public void setImageUrl(final String url) {
		image.setUrl(url);
	}

	@UiHandler("image")
	public void imageClicked(ClickEvent event) {
		log.info("image clicked");
		this.presenter.imageClicked();
	}

	@UiHandler("image")
	public void infoHover(MouseMoveEvent event) {
		layout.setWidgetBottomHeight(info, 0, Unit.CM, 16, Unit.PX);
		layout.animate(500);
		hideLabelLater(3000);
	}

	public void hideLabelLater(final int duration) {
		timer.setRunnable(new Runnable() {
			@Override
			public void run() {
				layout.setWidgetBottomHeight(info, 0, Unit.CM, 0, Unit.PX);
				layout.animate(500);

			}
		});
		timer.schedule(duration);
	}

	@Override
	public void setPresenter(ImagePresenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public void setMaxWidth(int width) {
		if (width > 0) {
			this.image.setMaxWidth(width);
		}
	}

	@Override
	public void setMaxHeight(int height) {
		if (height > 0) {
			this.image.setMaxHeight(height);
		}
	}

	@Override
	public void setDescription(String text) {
		info.setText(text);
	}

}
