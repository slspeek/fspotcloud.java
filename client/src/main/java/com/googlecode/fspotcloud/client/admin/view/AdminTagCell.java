package com.googlecode.fspotcloud.client.admin.view;

import java.util.logging.Logger;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

import com.googlecode.fspotcloud.client.main.ui.Resources;
import com.googlecode.fspotcloud.client.main.view.TagCell;
import com.googlecode.fspotcloud.shared.tag.TagNode;

public class AdminTagCell extends AbstractCell<TagNode> {

	final static private Logger log = Logger.getLogger(TagCell.class.getName());

	final private Resources resources;
	
	
	public AdminTagCell(Resources resources) {
		this.resources = resources;
	}

	public interface MyTemplates extends SafeHtmlTemplates {
		@Template("<span style=\"color: green\" class=\"{1}\">{0}</span>")
		SafeHtml message(String message, String style);
	}

	private static final MyTemplates TEMPLATES = GWT.create(MyTemplates.class);

	@Override
	public void render(com.google.gwt.cell.client.Cell.Context arg0,
			TagNode value, SafeHtmlBuilder sb) {
		log.info("" + value.isImportIssued());
		if (value.isImportIssued()) {
			SafeHtml snippetHtml = TEMPLATES.message(value.getTagName(), resources.style().importedTag());
			log.info(snippetHtml.asString());
			sb.append(snippetHtml);
		} else {
			sb.appendEscaped(value.getTagName());
		}
	}
}