package fspotcloud.client.main;

import fspotcloud.client.main.api.Initializable;

public interface IGlobalShortcutController extends Initializable {
	enum Mode {
		TREE_VIEW,TAG_VIEW, SLIDESHOW, ABOUT
	}

	void setMode(Mode mode);
}