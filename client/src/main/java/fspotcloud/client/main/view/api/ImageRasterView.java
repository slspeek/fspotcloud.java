package fspotcloud.client.main.view.api;

import java.util.List;

import com.google.gwt.user.client.ui.IsWidget;

public interface ImageRasterView extends IsWidget{
	interface ImageRasterPresenter {
		void init();
		int getWidth();
		int getHeight();
	}
	List<ImageView> buildRaster(int rowCount, int columnCount);
}
