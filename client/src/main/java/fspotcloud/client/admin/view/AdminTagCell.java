package fspotcloud.client.admin.view;

import java.util.logging.Logger;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SimpleHtmlSanitizer;

import fspotcloud.client.main.view.TagCell;
import fspotcloud.shared.tag.TagNode;

public class AdminTagCell  extends AbstractCell<TagNode> {
	
	final static private Logger log = Logger.getLogger(TagCell.class.getName());
	@Override
	public void render(com.google.gwt.cell.client.Cell.Context arg0,
			TagNode value, SafeHtmlBuilder sb) {
		log.info(""+value.isImportIssued());
		if (value.isImportIssued()) {
			SafeHtml snippetHtml = SimpleHtmlSanitizer.sanitizeHtml("<i>" + value.getTagName() +"</i>");
			log.info(snippetHtml.asString());
			sb.append(snippetHtml);
		} else {
			sb.appendEscaped(value.getTagName());
		}
	}
}