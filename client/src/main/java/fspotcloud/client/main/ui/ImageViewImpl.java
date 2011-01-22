package fspotcloud.client.main.ui;

import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.Widget;

import fspotcloud.client.main.ImageView;

public class ImageViewImpl extends ResizeComposite implements ImageView {

	private static final Logger log = Logger.getLogger(ImageViewImpl.class
			.getName());
	private static ImageViewImplUiBinder uiBinder = GWT
			.create(ImageViewImplUiBinder.class);

	interface ImageViewImplUiBinder extends UiBinder<Widget, ImageViewImpl> {
	}

	private ImageView.ImagePresenter presenter;

	@UiField
	HTMLPanel mainPanel;
	@UiField
	Image image;
	@UiField
	PushButton firstButton;
	@UiField
	PushButton prevButton;
	@UiField
	PushButton startButton;
	@UiField
	PushButton nextButton;
	@UiField
	PushButton lastButton;

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

	@UiHandler("firstButton")
	public void onFirstButtonClicked(ClickEvent event) {
		presenter.goFirst();
	}

	@UiHandler("startButton")
	public void onStartButtonClicked(ClickEvent event) {
		presenter.toggleSlideshow();
	}

	@UiHandler("nextButton")
	public void onNextButtonClicked(ClickEvent event) {
		presenter.goForward();
	}

	@UiHandler("prevButton")
	public void onPreviousButtonClicked(ClickEvent event) {
		log.info("Prev button pressed");
		presenter.goBackward();
	}

	@UiHandler("lastButton")
	public void onLastButtonClicked(ClickEvent event) {
		presenter.goLast();
	}

	@Override
	public void setSlideshowButtonCaption(String caption) {
		startButton.setText(caption);
		startButton.setTitle(caption);
	}
}
