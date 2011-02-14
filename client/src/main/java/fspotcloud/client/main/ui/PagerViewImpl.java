package fspotcloud.client.main.ui;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

import fspotcloud.client.view.PagerView;

public class PagerViewImpl extends Composite implements PagerView {
	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(PagerViewImpl.class
			.getName());
	
	private static PagerViewImplUiBinder uiBinder = GWT
			.create(PagerViewImplUiBinder.class);

	interface PagerViewImplUiBinder extends UiBinder<Widget, PagerViewImpl> {
	}

	final private PagerPresenter presenter;
	@UiField
	PushButton firstButton;
	@UiField
	PushButton prevButton;
	@UiField
	PushButton nextButton;
	@UiField
	PushButton lastButton;

	@Inject 
	public PagerViewImpl(PagerView.PagerPresenter presenter) {
		this.presenter = presenter;
		log.info("Constructing PagerView");
		initWidget(uiBinder.createAndBindUi(this));
		nextButton.setAccessKey('n');
		lastButton.setAccessKey('l');
		firstButton.setAccessKey('0');
	}

	@UiHandler("firstButton")
	public void onFirstButtonClicked(ClickEvent event) {
		try {
			presenter.goFirst();
		} catch(Exception e) {
			log.log(Level.SEVERE, "Uncaught exception", e);
		}
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

	@Override
	public PagerPresenter getPagerPresenter() {
		return presenter;
	}
}
