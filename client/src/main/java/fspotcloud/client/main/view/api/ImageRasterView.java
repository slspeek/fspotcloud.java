package fspotcloud.client.main.view.api;

import java.util.List;

public interface ImageRasterView {
	interface ImageRasterPresenter {
	}
	List<ImageView> buildRaster(int rowCount, int columnCount);
}
