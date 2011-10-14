package fspotcloud.client.main.view.api;


import com.google.gwt.activity.shared.Activity;
import com.google.gwt.user.client.ui.IsWidget;

public interface TagView extends IsWidget {
	
	interface TagPresenter extends Activity {
	}
	
	void setPresenter(TagPresenter presenter);
}
