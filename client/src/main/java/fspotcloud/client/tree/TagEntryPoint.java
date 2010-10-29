package fspotcloud.client.tree;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootLayoutPanel;

public class TagEntryPoint implements EntryPoint {

	@Override
	public void onModuleLoad() {
		// TODO Auto-generated method stub
		TagView tagView = new TagView();
		RootLayoutPanel.get().add(tagView);
	}

}
