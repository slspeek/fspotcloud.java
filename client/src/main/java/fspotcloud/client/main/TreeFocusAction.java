package fspotcloud.client.main;

import java.util.logging.Logger;

import com.google.inject.Inject;

import fspotcloud.client.main.ui.TagViewImpl;
import fspotcloud.client.main.view.api.TagView;
import fspotcloud.client.main.view.api.TreeView;

public class TreeFocusAction implements Runnable {
	private static final Logger log = Logger.getLogger(TreeFocusAction.class
			.getName());
	final private TreeView treeView;
	final private TagViewImpl tagView;
	@Inject
	public TreeFocusAction(TreeView treeView, TagView tagView) {
		this.treeView = treeView;
		this.tagView = (TagViewImpl) tagView;
		
	}

	@Override
	public void run() {
		log.info("Run: " +tagView);
		tagView.animateControlsIn(100);
		treeView.requestFocus();
	}

}
