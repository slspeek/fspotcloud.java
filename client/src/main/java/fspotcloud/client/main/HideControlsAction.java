package fspotcloud.client.main;

import java.util.logging.Logger;

import com.google.inject.Inject;

import fspotcloud.client.main.IGlobalShortcutController.Mode;
import fspotcloud.client.main.ui.TagViewImpl;
import fspotcloud.client.main.view.api.TagView;

public class HideControlsAction implements Runnable {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(HideControlsAction.class
			.getName());
	final private TagViewImpl tagView;
	private final IGlobalShortcutController keyboard;
	
	@Inject
	public HideControlsAction(TagView tagView, IGlobalShortcutController keyboard
			) {
		super();
		this.tagView = (TagViewImpl) tagView;
		this.keyboard = keyboard;
	}

	@Override
	public void run() {
		tagView.animateControlsOut(0);
		keyboard.setMode(Mode.TAG_VIEW);
	}
	
}
