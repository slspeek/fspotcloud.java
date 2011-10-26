package fspotcloud.client.main.view.api;

import com.google.gwt.core.client.GWT;
import com.google.gwt.junit.client.GWTTestCase;

import fspotcloud.client.main.gin.AppGinjector;
import fspotcloud.client.main.view.api.ButtonPanelView.ButtonPanelPresenter;


public class GwtTestButtonPanelPresenterProvider extends GWTTestCase {

	@Override
	public String getModuleName() {
		return "fspotcloud.FSpotCloud";
	}
	
	public void testOne() throws Exception {
		final AppGinjector injector = GWT.create(AppGinjector.class);
		ButtonPanelPresenterProvider provider = injector.getButtonPanelPresenterProvider();
		assertNotNull(provider);
		ButtonPanelPresenter presenter = provider.get();
	}
	
	
}
