package com.googlecode.fspotcloud.client.main;

import java.util.logging.Logger;

import com.google.inject.Inject;

import com.googlecode.fspotcloud.client.main.IGlobalShortcutController.Mode;
import com.googlecode.fspotcloud.client.main.ui.TagViewImpl;
import com.googlecode.fspotcloud.client.main.view.api.TagView;
import com.googlecode.fspotcloud.client.main.view.api.TreeView;
import com.googlecode.fspotcloud.client.place.api.Slideshow;

public class TreeFocusAction implements Runnable {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(TreeFocusAction.class
			.getName());
	final private TreeView treeView;
	final private TagViewImpl tagView;
	final private Slideshow slideshow;
	final private IGlobalShortcutController keyboard;
	
	@Inject
	public TreeFocusAction(TreeView treeView, TagView tagView,
			Slideshow slideshow, IGlobalShortcutController keyboard) {
		super();
		this.slideshow = slideshow;
		this.treeView = treeView;
		this.tagView = (TagViewImpl) tagView;
		this.keyboard = keyboard;
	}

	@Override
	public void run() {
		slideshow.stop();
		tagView.cancelHiding();
		tagView.animateControlsIn(100);
		treeView.requestFocus();
		keyboard.setMode(Mode.TREE_VIEW);
	}
	
}
