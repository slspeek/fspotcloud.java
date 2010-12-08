package fspotcloud.client.main;

import java.util.List;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.TreeItem;

public interface TagView extends IsWidget {

	public void setPresenter(TagPresenter presenter);
	
	public interface TagPresenter {
		public void treeSelectionChanged(SelectionEvent<TreeItem> event);
		public void reloadTree();
	}

	public void setImageList(List<String> imageList);

	public void setStatusText(String string);
	
	public void setTreeModel(TreeItem root);
	
	public TreeItem getTreeModel();

	public void setSelectedItem(TreeItem item);
	
}
