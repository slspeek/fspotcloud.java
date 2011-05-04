package fspotcloud.client.view;


import com.google.gwt.activity.shared.Activity;
import com.google.gwt.user.cellview.client.TreeNode;
import com.google.gwt.user.client.ui.HasOneWidget;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.view.client.TreeViewModel;

public interface TagView extends IsWidget {
	
	interface TagPresenter extends Activity {
		void init();
	}

	void setStatusText(String string);
	
	HasOneWidget getImageViewContainer();
}
