package fspotcloud.client.main.ui;

import java.util.logging.Level;
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

import fspotcloud.client.view.PagerView;

public class PagerViewImpl extends Composite implements PagerView {
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
	PushButton prevButton;
	@UiField
	PushButton nextButton;
	@UiField
	PushButton lastButton;

	@Inject
	public PagerViewImpl() {
		//log.info("Constructing PagerView");
		initWidget(uiBinder.createAndBindUi(this));
		mainPanel.addStyleName("fsc-pager");
		nextButton.setAccessKey('n');
		nextButton.addStyleName("fsc-pager-next");
		lastButton.setAccessKey('l');
		lastButton.addStyleName("fsc-pager-last");
		firstButton.setAccessKey('0');
		firstButton.addStyleName("fsc-pager-first");
		prevButton.addStyleName("fsc-pager-previous");
	}

	@UiHandler("firstButton")
	public void onFirstButtonClicked(ClickEvent event) {
		try {
			presenter.goEnd(true);
		} catch (Exception e) {
			log.log(Level.SEVERE, "Uncaught exception", e);
		}
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

	@Override
	public void setPagerPresenter(PagerPresenter pagerPresenter) {
		this.presenter = pagerPresenter;
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
}