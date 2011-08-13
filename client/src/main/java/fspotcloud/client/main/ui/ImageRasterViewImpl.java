package fspotcloud.client.main.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

import fspotcloud.client.main.view.api.ImageRasterView;
import fspotcloud.client.main.view.api.ImageView;
import fspotcloud.client.main.view.api.ImageViewFactory;

public class ImageRasterViewImpl extends ResizeComposite implements
		ImageRasterView {

	@SuppressWarnings("unused")
	private static final Logger log = Logger
			.getLogger(ImageRasterViewImpl.class.getName());

	private static ImageRasterViewImplUiBinder uiBinder = GWT
			.create(ImageRasterViewImplUiBinder.class);

	interface ImageRasterViewImplUiBinder extends
			UiBinder<Widget, ImageRasterViewImpl> {
	}

	@UiField
	DockLayoutPanel dockPanel;
	private final ImageViewFactory imageViewFactory;
	private ImageRasterView.ImageRasterPresenter presenter;

	@Inject
	public ImageRasterViewImpl(ImageViewFactory imageViewFactory) {
		this.imageViewFactory = imageViewFactory;
		initWidget(uiBinder.createAndBindUi(this));
		dockPanel.ensureDebugId("image-raster-view");
	}
	
	@Override
	public List<ImageView> buildRaster(int rowCount, int columnCount) {
		Grid grid = new Grid(rowCount, columnCount);
		
		List<ImageView> result = new ArrayList<ImageView>();
		for (int row = 0; row < rowCount; row++) {
			for (int column = 0; column < columnCount; column++) {
				ImageView view = imageViewFactory.get(column + "x" + row);
				grid.setWidget(row, column, view.asWidget());
				result.add(view);
			}
		}
		dockPanel.clear();
		dockPanel.add(grid);
		log.info("At end of buildraster: size: ("
				+ getOffsetWidth()+ ", "
				+ getOffsetWidth() + ")");
		log.info("At end of buildraster: simple panel size: ("
				+ dockPanel.getOffsetWidth() + ", "
				+ dockPanel.getOffsetHeight() + ")");
		return result;
	}
	

	@Override
	public void onResize() {
		log.info("Current size: (yes debug) " + getOffsetWidth());
		if (presenter != null) {
			presenter.onResize();
		}
		super.onResize();
	}

	@Override
	public void setPresenter(ImageRasterPresenter presenter) {
		this.presenter = presenter;
	}
	
}
