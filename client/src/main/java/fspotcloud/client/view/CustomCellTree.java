package fspotcloud.client.view;

import com.google.gwt.user.cellview.client.CellTree;
import com.google.gwt.view.client.TreeViewModel;

import fspotcloud.shared.tag.TagNode;

public class CustomCellTree extends CellTree {

	public CustomCellTree(TreeViewModel viewModel, TagNode rootValue) {
		super(viewModel, rootValue);
	}

}
