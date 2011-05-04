package fspotcloud.client.view;

import com.google.gwt.user.cellview.client.TreeNode;
import com.google.gwt.view.client.TreeViewModel;

public interface TreeView {

	interface TreePresenter {
		void setPlace(BasePlace place);
		void init();
	}
	
	void setTreeModel(TreeViewModel model);
	
	TreeNode getRootNode();
}
