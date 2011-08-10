package fspotcloud.client.main.view.api;

import java.util.List;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.user.client.ui.IsWidget;

import fspotcloud.client.main.api.Initializable;

public interface ImageRasterView extends IsWidget {
	interface ImageRasterPresenter extends Initializable, Activity {
		int getWidth();

		int getHeight();

		void onResize();
	}

	List<ImageView> buildRaster(int rowCount, int columnCount);

	void setPresenter(ImageRasterPresenter presenter);
}
