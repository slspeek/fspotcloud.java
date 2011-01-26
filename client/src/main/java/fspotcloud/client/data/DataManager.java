package fspotcloud.client.data;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import fspotcloud.shared.tag.TagNode;

public interface DataManager {

	public abstract TagNode getTagNode(String id);

	public abstract void getTagTree(final AsyncCallback<List<TagNode>> callback);

}