package fspotcloud.client.main.ui;

import java.util.logging.Logger;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DoubleClickEvent;
import com.google.gwt.event.dom.client.DoubleClickHandler;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.reveregroup.gwt.imagepreloader.FitImage;

import fspotcloud.client.main.view.api.ImageView;

public class DecoratedImageViewImpl extends VerticalPanel implements ImageView {

	private static final Logger log = Logger
			.getLogger(DecoratedImageViewImpl.class.getName());

	FitImage image;
	Label info;
	private ImageView.ImagePresenter presenter;

	@Inject
	public DecoratedImageViewImpl(@Assisted String location) {
		// super(Unit.PX);
		image = new FitImage();
		info = new Label();
		// asWidget().getElement().getStyle().setProperty("backgroundColor",
		// "red");
		image.ensureDebugId("image-view-" + location);
		add(image);
		add(info);
		image.addHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				imageClicked(event);

			}

		}, ClickEvent.getType());
		image.addHandler(new DoubleClickHandler() {

			@Override
			public void onDoubleClick(DoubleClickEvent event) {
				imageDoubleClicked(event);

			}

		}, DoubleClickEvent.getType());
		// setWidgetLeftRight(image, 1, Unit.EM, 1, Unit.EM); // Center panel
		// setWidgetTopBottom(image, 1, Unit.EM, 1, Unit.EM);
	}

	@Override
	public void setImageUrl(String url) {
		image.setUrl(url);
	}

	public void imageClicked(ClickEvent event) {
		log.info("image clicked");
		presenter.imageClicked();
	}

	public void imageDoubleClicked(DoubleClickEvent event) {
		log.info("image double clicked");
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
}
