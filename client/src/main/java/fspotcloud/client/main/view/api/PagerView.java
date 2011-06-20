package fspotcloud.client.main.view.api;

import com.google.gwt.user.client.ui.HasEnabled;
import com.google.gwt.user.client.ui.IsWidget;

public interface PagerView extends IsWidget {

	interface PagerPresenter {

		void goEnd(boolean first);

		void go(boolean forward);
	}

	void setPresenter(PagerPresenter presenter);

	HasEnabled getFirst();

	HasEnabled getLast();

	HasEnabled getPrevious();

	HasEnabled getNext();
}
