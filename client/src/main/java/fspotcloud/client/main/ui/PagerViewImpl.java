package fspotcloud.client.main.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.Widget;

import fspotcloud.client.view.PagerView;

public class PagerViewImpl extends Composite implements PagerView {

	private static PagerViewImplUiBinder uiBinder = GWT
			.create(PagerViewImplUiBinder.class);

	interface PagerViewImplUiBinder extends UiBinder<Widget, PagerViewImpl> {
	}

	private PagerPresenter presenter;
	@UiField
	PushButton firstButton;
	@UiField
	PushButton prevButton;
	@UiField
	PushButton startButton;
	@UiField
	PushButton nextButton;
	@UiField
	PushButton lastButton;

	public PagerViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		startButton.setAccessKey('s');
		nextButton.setAccessKey('n');
		lastButton.setAccessKey('l');
		firstButton.setAccessKey('0');
	}

	@Override
	public void setPresenter(PagerPresenter presenter) {
		this.presenter = presenter;
	}
	
	@UiHandler("firstButton")
	public void onFirstButtonClicked(ClickEvent event) {
		presenter.goFirst();
	}

	@UiHandler("nextButton")
	public void onNextButtonClicked(ClickEvent event) {
		presenter.goForward();
	}

	@UiHandler("prevButton")
	public void onPreviousButtonClicked(ClickEvent event) {
		presenter.goBackward();
	}

	@UiHandler("lastButton")
	public void onLastButtonClicked(ClickEvent event) {
		presenter.goLast();
	}
}
