package fspotcloud.client.view;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.user.client.ui.HasOneWidget;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.view.client.TreeViewModel;

public interface TagView extends IsWidget {
	
	interface TagPresenter extends Activity {
		void setPlace(TagViewingPlace place);

		void reloadTree();
	}

	void setStatusText(String string);

	void setTreeModel(TreeViewModel model);

	HasOneWidget getImageViewContainer();

}
