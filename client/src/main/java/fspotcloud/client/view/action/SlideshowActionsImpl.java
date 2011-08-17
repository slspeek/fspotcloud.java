package fspotcloud.client.view.action;

import java.util.Arrays;
import java.util.List;

import com.google.gwt.resources.client.ImageResource;
import com.google.inject.Inject;

import fspotcloud.client.main.shared.SlideshowEvent;
import fspotcloud.client.main.shared.SlideshowEventProviderFactory;
import fspotcloud.client.main.ui.Resources;
import fspotcloud.client.view.action.api.UserActionFactory;
import fspotcloud.client.view.action.api.SlideshowActions;
import fspotcloud.client.view.action.api.UserAction;

public class SlideshowActionsImpl extends ActionsFactory implements
		SlideshowActions {

	public UserAction SLIDESHOW_START;
	public UserAction SLIDESHOW__END;
	public UserAction SLIDESHOW_SLOWER;
	public UserAction SLIDESHOW_FASTER;
	private List<UserAction> all;

	final private SlideshowEventProviderFactory slideshow;

	@Inject
	public SlideshowActionsImpl(UserActionFactory shortcutFactory,
			SlideshowEventProviderFactory slideshow, Resources resources) {
		super(shortcutFactory, resources);
		this.slideshow = slideshow;
		init();
	}

	private void init() {
		SLIDESHOW_START = createSlideshow("play", "Play", 'S', (int) 'G',
				"Start slideshow", resources.playIcon(), SlideshowEvent.ActionType.START);
		SLIDESHOW__END = createSlideshow("stop", "Stop", 'Q', null,
				"Stop slideshow", resources.stopIcon(), SlideshowEvent.ActionType.STOP);
		SLIDESHOW_SLOWER = createSlideshow("slower", "Slower", 'U', null,
				"Makes the slideshow go slower", resources.slowerIcon(),
				SlideshowEvent.ActionType.SLOWER);
		SLIDESHOW_FASTER = createSlideshow("faster", "Faster", 'I', null,
				"Makes the slideshow go faster", resources.fasterIcon(),
				SlideshowEvent.ActionType.FASTER);
		all = Arrays.asList(SLIDESHOW_FASTER, SLIDESHOW_START, SLIDESHOW__END,
				SLIDESHOW_SLOWER);
	}

	public UserAction createSlideshow(String id, String caption, int key,
			Integer altKey, String description, ImageResource icon,
			SlideshowEvent.ActionType actionType) {
		return create(id, caption, key, altKey, description, icon,
				slideshow.get(actionType));
	}

	@Override
	public List<UserAction> allActions() {
		return all;
	}

	@Override
	public UserAction startSlideshow() {
		return SLIDESHOW_START;

	}

	@Override
	public UserAction stopSlideshow() {
		return SLIDESHOW__END;
	}

	@Override
	public UserAction slower() {
		return SLIDESHOW_SLOWER;
	}

	@Override
	public UserAction faster() {
		return SLIDESHOW_FASTER;
	}

	@Override
	public String getDescription() {
		return "Slideshow";
	}

}
