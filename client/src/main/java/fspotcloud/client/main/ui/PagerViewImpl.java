package fspotcloud.client.main.ui;

import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasEnabled;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

import fspotcloud.client.main.view.api.PagerView;

public class PagerViewImpl extends Composite implements PagerView {
	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(PagerViewImpl.class
			.getName());

	private static PagerViewImplUiBinder uiBinder = GWT
			.create(PagerViewImplUiBinder.class);

	interface PagerViewImplUiBinder extends UiBinder<Widget, PagerViewImpl> {
	}

	private PagerPresenter presenter;

	@UiField
	HorizontalPanel mainPanel;
	@UiField
	PushButton firstButton;
	@UiField
	PushButton pageUpButton;
	@UiField
	PushButton prevButton;
	@UiField
	PushButton nextButton;
	@UiField
	PushButton pageDownButton;
	@UiField
	PushButton lastButton;

	@Inject
	public PagerViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		mainPanel.addStyleName("fsc-pager");
		mainPanel.ensureDebugId("pager-main-panel");
		nextButton.addStyleName("fsc-pager-next");
		nextButton.ensureDebugId("pager-next-button");
		lastButton.addStyleName("fsc-pager-last");
		lastButton.ensureDebugId("pager-last-button");
		firstButton.addStyleName("fsc-pager-first");
		firstButton.ensureDebugId("pager-first-button");
		prevButton.addStyleName("fsc-pager-previous");
		prevButton.ensureDebugId("pager-previous-button");
	}

	@UiHandler("firstButton")
	public void onFirstButtonClicked(ClickEvent event) {
		presenter.goEnd(true);
	}

	@UiHandler("nextButton")
	public void onNextButtonClicked(ClickEvent event) {
		presenter.go(true);
	}

	@UiHandler("prevButton")
	public void onPreviousButtonClicked(ClickEvent event) {
		presenter.go(false);
	}

	@UiHandler("lastButton")
	public void onLastButtonClicked(ClickEvent event) {
		presenter.goEnd(false);
	}
	
	@UiHandler("pageUpButton")
	public void onPageUpButtonClicked(ClickEvent event) {
		presenter.go(false);
	}

	@UiHandler("pageDownButton")
	public void onPageDownButtonClicked(ClickEvent event) {
		presenter.go(true);
	}

	@Override
	public HasEnabled getFirst() {
		return firstButton;
	}

	@Override
	public HasEnabled getLast() {
		return lastButton;
	}

	@Override
	public HasEnabled getNext() {
		return nextButton;
	}

	@Override
	public HasEnabled getPrevious() {
		return prevButton;
	}

	@Override
	public void setPresenter(PagerPresenter presenter) {
		this.presenter = presenter;

	}
}
