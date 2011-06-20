package fspotcloud.client.main.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

import fspotcloud.client.main.view.api.ImageRasterView;
import fspotcloud.client.main.view.api.ImageView;

public class ImageRasterViewImpl extends ResizeComposite implements ImageRasterView {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(ImageRasterViewImpl.class
			.getName());

	private static ImageRasterViewImplUiBinder uiBinder = GWT
			.create(ImageRasterViewImplUiBinder.class);

	interface ImageRasterViewImplUiBinder extends UiBinder<Widget, ImageRasterViewImpl> {
	}

	@UiField
	SimplePanel simplePanel;

	public ImageRasterViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		simplePanel.ensureDebugId("image-raster-view");
	}

	@Override
	public List<ImageView> buildRaster(int rowCount, int columnCount) {
		Grid grid = new Grid(rowCount, columnCount);
		simplePanel.setWidget(grid);
		List<ImageView> result = new ArrayList<ImageView>();
		for (int row = 0; row < rowCount; row++) {
			for (int column = 0; column < columnCount; column++) {
				ImageView view = new ImageViewImpl();
				grid.setWidget(row, column, view.asWidget());
				result.add(view);
			}
		}
		return result;
	}
	
}
