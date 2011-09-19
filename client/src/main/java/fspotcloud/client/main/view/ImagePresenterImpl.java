package fspotcloud.client.main.view;

import java.util.logging.Logger;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

import fspotcloud.client.main.shared.ZoomViewEvent;
import fspotcloud.client.main.view.api.ImageView;
import fspotcloud.client.main.view.api.PopupView;
import fspotcloud.shared.photo.PhotoInfo;

public class ImagePresenterImpl implements ImageView.ImagePresenter {
	private static final Logger log = Logger.getLogger(ImagePresenterImpl.class
			.getName());

	final private ImageView imageView;
	final private String tagId;
	final private String photoId;
	final private boolean thumb;
	final private EventBus eventBus;
	final private PhotoInfo info;
	final private PopupView popupView;

	@Inject
	public ImagePresenterImpl(@Assisted("maxWidth") int maxWidth,
			@Assisted("maxHeight") int maxHeight, @Assisted String tagId,
			@Assisted ImageView imageView, @Assisted boolean thumb, @Assisted PhotoInfo info,
			EventBus eventBus, PopupView popupView) {
		this.tagId =  tagId; 
		this.popupView = popupView;
		photoId = info.getId();
		this.imageView = imageView;
		this.thumb = thumb;
		this.eventBus = eventBus;
		this.info = info;
		setMaxWidth(maxWidth);
		setMaxHeight(maxHeight);
	}

	public void init() {
		imageView.setPresenter(this);
		setImage();
	}

	public void setImage() {
		if (photoId != null) {
			String date;
			if(thumb) {
				date = DateTimeFormat.getFormat(PredefinedFormat.DATE_MEDIUM).format(info.getDate());
			} else {
				date = DateTimeFormat.getFormat(PredefinedFormat.DATE_FULL).format(info.getDate()) + " " +
				DateTimeFormat.getFormat(PredefinedFormat.TIME_MEDIUM).format(info.getDate()) ;
			}
			imageView.setTooltip(date);
			String url = "/image?id=" + photoId;
			url += thumb ? "&thumb" : "";
			imageView.setImageUrl(url);
		} else {
			log.warning("No photoId defined for tagId:  " + tagId);
		}
	}

	@Override
	public void imageClicked() {
		log.info("about to fire zoom event");
		eventBus.fireEvent(new ZoomViewEvent(tagId, photoId));
	}
	@Override
	public void imageDoubleClicked() {
		String exif = info.getExifData();
		if (exif != null) {
			exif.replaceAll("\n", "<br>");
		}
		popupView.setText(exif);
		popupView.center();
		popupView.show();
	}

	@Override
	public void setMaxWidth(int width) {
		imageView.setMaxWidth(width);
	}

	@Override
	public void setMaxHeight(int height) {
		imageView.setMaxHeight(height);
	}

	@Override
	public void setVisible(boolean visible) {
		imageView.setVisible(visible);
	}
}
