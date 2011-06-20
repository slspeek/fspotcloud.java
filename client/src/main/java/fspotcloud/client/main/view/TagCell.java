package fspotcloud.client.main.view;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

import fspotcloud.shared.tag.TagNode;

public class TagCell extends AbstractCell<TagNode> {
	@Override
	public void render(com.google.gwt.cell.client.Cell.Context arg0,
			TagNode value, SafeHtmlBuilder sb) {
		sb.appendEscaped(value.getTagName());
	}
}
