package fspotcloud.client.main.view;

import java.util.logging.Logger;

import com.google.gwt.i18n.client.NumberFormat;
import com.google.inject.Inject;

import fspotcloud.client.main.shared.SlideshowStatusEvent;
import fspotcloud.client.main.view.api.SlideshowView;

public class SlideshowPresenterImpl implements
		SlideshowView.SlideshowPresenter, SlideshowStatusEvent.Handler {
	private static final Logger log = Logger
			.getLogger(SlideshowPresenterImpl.class.getName());

	final private SlideshowView slideshowView;
	final private NumberFormat formatter = NumberFormat.getDecimalFormat();
	@Inject
	public SlideshowPresenterImpl(SlideshowView slideshowView
			) {
		
		this.slideshowView = slideshowView;
		log.info("Created");
	}

	
	public void redraw(float delay, boolean running) {
		slideshowView.setLabelText(formatter.format(delay) + " seconds. ");
		if (running) {
			slideshowView.asWidget().addStyleDependentName("running");
		} else {
			slideshowView.asWidget().removeStyleDependentName("running");
		}
	}

	@Override
	public void onEvent(SlideshowStatusEvent e) {
		redraw(e.getDelay(), e.isRunning());
	}
}
