package fspotcloud.client.view;

import com.google.gwt.user.client.ui.HasOneWidget;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.view.client.TreeViewModel;

public interface TagView extends IsWidget {

	void setStatusText(String string);

	void setTreeModel(TreeViewModel model);

	HasOneWidget getImageViewContainer();

}
