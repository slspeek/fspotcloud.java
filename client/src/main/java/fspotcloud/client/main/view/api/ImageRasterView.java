package fspotcloud.client.main.view.api;

import java.util.List;

public interface ImageRasterView {
	interface ImageRasterPresenter {
		void init();
	}
	List<ImageView> buildRaster(int rowCount, int columnCount);
}
