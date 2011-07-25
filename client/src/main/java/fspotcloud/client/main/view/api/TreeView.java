package fspotcloud.client.main.view.api;

import com.google.gwt.user.cellview.client.TreeNode;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.view.client.TreeViewModel;

import fspotcloud.client.main.api.Initializable;
import fspotcloud.client.place.BasePlace;

public interface TreeView extends IsWidget {

	interface TreePresenter extends Initializable {
		void setPlace(BasePlace place);
	}

	void setTreeModel(TreeViewModel model);

	TreeNode getRootNode();

	void requestFocus();
}
