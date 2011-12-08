package fspotcloud.client.main.view;

import java.util.logging.Logger;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

import fspotcloud.client.main.shared.ZoomViewEvent;
import fspotcloud.client.main.ui.Resources;
import fspotcloud.client.main.view.api.ImageView;
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
	@SuppressWarnings("unused")
	final private Resources resources;

	@Inject
	public ImagePresenterImpl(@Assisted String tagId,
			@Assisted ImageView imageView, @Assisted boolean thumb, @Assisted PhotoInfo info,
			EventBus eventBus, Resources resources) {
		this.tagId =  tagId; 
		this.resources = resources;
		photoId = info.getId();
		this.imageView = imageView;
		this.thumb = thumb;
		this.eventBus = eventBus;
		this.info = info;
	}

	public void init() {
		imageView.setPresenter(this);
		setImage();
		imageView.hideLabelLater(4000);
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
			imageView.setDescription(date);
			String url = "/image?id=" + photoId;
			url += thumb ? "&thumb" : "";
			imageView.setImageUrl(url);
			imageView.adjustSize();
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
	public void setSelected() {
		imageView.setSelected();
	}

}
