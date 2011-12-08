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
	private final Resources resources;

	@Inject
	public ImageViewImpl(@Assisted String location, TimerInterface timer, Resources resources) {
		this.timer = timer;
		this.resources = resources;
		initWidget(uiBinder.createAndBindUi(this));
		init(location);
	}

	private void init(String location) {
		image.ensureDebugId("image-view-" + location);
		image.setVisible(false);
		image.addFitImageLoadHandler(new FitImageLoadHandler() {

			@Override
			public void imageLoaded(FitImageLoadEvent event) {
				image.setVisible(true);
				log.info("Should be Visible");
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
	public void setDescription(String text) {
		info.setText(text);
	}

	@Override
	public void setSelected() {
		setStyleName(resources.style().selectedImage());
	}

	@Override
	public void onResize() {
		image.setMaxSize(getOffsetWidth(), getOffsetHeight());
		super.onResize();
	}

	@Override
	public void adjustSize() {
		log.info("Called adjust size");
		onResize();
	}

	
}
