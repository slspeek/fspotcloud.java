package fspotcloud.client.view;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.user.client.ui.HasOneWidget;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.TreeItem;

public interface TagView extends IsWidget {

	void setPresenter(TagPresenter presenter);

	public interface TagPresenter {
		void treeSelectionChanged(SelectionEvent<TreeItem> event);

		void reloadTree();
	}

	void setStatusText(String string);

	void setTreeModel(TreeItem root);

	TreeItem getTreeModel();

	void setSelectedItem(TreeItem item);

	HasOneWidget getImageViewContainer();

}
