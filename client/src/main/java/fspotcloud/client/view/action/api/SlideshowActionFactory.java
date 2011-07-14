package fspotcloud.client.view.action.api;

import fspotcloud.client.view.action.SlideshowAction;

public interface SlideshowActionFactory {
	SlideshowAction get(int actionType);
}
