package fspotcloud.client.main.view.api;


import com.google.gwt.activity.shared.Activity;
import com.google.gwt.user.client.ui.IsWidget;

import fspotcloud.client.main.api.Initializable;

public interface TagView extends IsWidget {
	
	interface TagPresenter extends Activity, Initializable {
	}
	
	void setPresenter(TagPresenter presenter);
}
