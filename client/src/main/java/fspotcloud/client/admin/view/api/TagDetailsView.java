package fspotcloud.client.admin.view.api;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.IsWidget;

public interface TagDetailsView extends IsWidget {

	interface TagDetailsPresenter extends Activity {
		void importTag();

		void init();
	}

	HasText getTagNameValue();

	HasText getTagDescriptionValue();

	HasText getTagImportIssuedValue();

	HasText getTagLoadedImagesCountValue();

	HasText getTagImageCountValue();

	void setPresenter(TagDetailsPresenter presenter);

}
