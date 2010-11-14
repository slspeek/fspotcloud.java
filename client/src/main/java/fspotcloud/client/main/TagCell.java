package fspotcloud.client.main;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

import fspotcloud.shared.tag.TagNode;

public class TagCell extends AbstractCell<TagNode> {

	@Override
	public void render(TagNode value, Object key, SafeHtmlBuilder sb) {
		sb.appendEscaped(value.getTagName());
	}
}
