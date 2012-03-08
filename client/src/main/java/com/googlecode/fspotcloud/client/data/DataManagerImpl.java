package com.googlecode.fspotcloud.client.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.googlecode.fspotcloud.rpc.TagServiceAsync;
import com.googlecode.fspotcloud.shared.tag.TagNode;


public class DataManagerImpl implements DataManager {
	private static final Logger log = Logger.getLogger(DataManagerImpl.class
			.getName());

	private final TagServiceAsync tagService;
	private final IndexingUtil indexingUtil;
	private boolean isCalled = false;
	private List<Runnable> queue = new ArrayList<Runnable>();
	private Runnable callbackHook = new Runnable() {

		@Override
		public void run() {
			for (Runnable task : queue) {
				task.run();
			}
			queue.clear();
		}
	};

	private List<TagNode> tagTreeData = null;
	private List<TagNode> adminTagTreeData = null;
	private final Map<String, TagNode> tagNodeIndex = new HashMap<String, TagNode>();
	private final Map<String, TagNode> adminTagNodeIndex = new HashMap<String, TagNode>();

	@Inject
	public DataManagerImpl(TagServiceAsync tagService) {
		this.tagService = tagService;
		this.indexingUtil = new IndexingUtil();
	}

	public void getTagNode(final String id,
			final AsyncCallback<TagNode> callback) {
		if (tagTreeData != null) {
			TagNode node = tagNodeIndex.get(id);	
			callback.onSuccess(node);
		} else {
			if (!isCalled) {
				getTagTree(new AsyncCallback<List<TagNode>>() {
					@Override
					public void onFailure(Throwable arg0) {
						callback.onFailure(arg0);
					}

					@Override
					public void onSuccess(List<TagNode> arg0) {
						callback.onSuccess(tagNodeIndex.get(id));
					}
				});
			} else {
				queue.add(new Runnable() {
					@Override
					public void run() {
						callback.onSuccess(tagNodeIndex.get(id));
					}
				});
			}
		}
	}

	public void getAdminTagNode(final String id,
			final AsyncCallback<TagNode> callback) {
		TagNode node = adminTagNodeIndex.get(id);
		if (node != null) {
			callback.onSuccess(node);
		} else {
			getAdminTagTree(new AsyncCallback<List<TagNode>>() {
				@Override
				public void onFailure(Throwable arg0) {
					callback.onFailure(arg0);
				}

				@Override
				public void onSuccess(List<TagNode> arg0) {
					callback.onSuccess(adminTagNodeIndex.get(id));
				}
			});

		}
	}

	public void getTagTree(final AsyncCallback<List<TagNode>> callback) {
		if (tagTreeData != null) {
			callback.onSuccess(tagTreeData);
		} else {
			queue.add(new Runnable() {
				@Override
				public void run() {
					callback.onSuccess(tagTreeData);
				}
			});
			if (!isCalled) {
				isCalled = true;
				tagService.loadTagTree(new AsyncCallback<List<TagNode>>() {

					public void onFailure(Throwable caught) {
						callback.onFailure(caught);
					}

					public void onSuccess(List<TagNode> result) {
						tagTreeData = result;
						indexingUtil.rebuildTagNodeIndex(tagNodeIndex,
								tagTreeData);
						callbackHook.run();
						log.info("Hook ran !");
					}
				});
			}
		}
	}

	public void getAdminTagTree(final AsyncCallback<List<TagNode>> callback) {
		if (adminTagTreeData != null) {
			callback.onSuccess(adminTagTreeData);
		} else {
			tagService.loadAdminTagTree(new AsyncCallback<List<TagNode>>() {
				public void onFailure(Throwable caught) {
					log.warning(caught.getLocalizedMessage());
					callback.onFailure(caught);
				}

				public void onSuccess(List<TagNode> adminTagTreeData) {
					indexingUtil.rebuildTagNodeIndex(adminTagNodeIndex,
							adminTagTreeData);
					callback.onSuccess(adminTagTreeData);
				}
			});
		}
	}
}
