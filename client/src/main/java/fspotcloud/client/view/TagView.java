package fspotcloud.client.view;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.user.client.ui.HasOneWidget;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.view.client.TreeViewModel;

public interface TagView extends IsWidget {
	
	interface TagPresenter extends Activity {
		void setPlace(BasePlace place);
		void reloadTree();
		void init();
	}

	void setStatusText(String string);

	void setTreeModel(TreeViewModel model);

	HasOneWidget getImageViewContainer();

}
