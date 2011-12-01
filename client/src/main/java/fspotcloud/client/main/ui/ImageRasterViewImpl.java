package fspotcloud.client.main.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.MouseWheelEvent;
import com.google.gwt.event.dom.client.MouseWheelHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

import fspotcloud.client.main.view.api.ImageRasterView;
import fspotcloud.client.main.view.api.ImageView;
import fspotcloud.client.main.view.api.ImageViewFactory;

public class ImageRasterViewImpl extends ResizeComposite implements
		ImageRasterView, MouseWheelHandler {

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
	private LayoutPanel layout;
	private Label pagingLabel;

	private String pagingText = "Where am I?";

	@Inject
	public ImageRasterViewImpl(ImageViewFactory imageViewFactory) {
		this.imageViewFactory = imageViewFactory;
		initWidget(uiBinder.createAndBindUi(this));
		dockPanel.ensureDebugId("image-raster-view");
		dockPanel.addDomHandler(this, MouseWheelEvent.getType());
	}
	
	@Override
	public List<ImageView> buildRaster(int rowCount, int columnCount) {
		layout = new LayoutPanel();
		
		List<ImageView> result = new ArrayList<ImageView>();
		for (int row = 0; row < rowCount; row++) {
			for (int column = 0; column < columnCount; column++) {
				ImageView view = imageViewFactory.get(column + "x" + row);
				Widget asWidget = view.asWidget();
				layout.add(asWidget);
				layout.setWidgetTopHeight(asWidget, row * (100/(float)rowCount), Unit.PCT, 100/rowCount, Unit.PCT);
				layout.setWidgetLeftWidth(asWidget, column * (100/(float)columnCount) , Unit.PCT, 100/rowCount, Unit.PCT);
				result.add(view);
			}
		}
		pagingLabel = new Label(pagingText);
		layout.add(pagingLabel);
		layout.setWidgetBottomHeight(pagingLabel, 0, Unit.PT, 16, Unit.PT);
		layout.setWidgetRightWidth(pagingLabel, 0, Unit.PT, 10, Unit.PCT);
		dockPanel.clear();
		dockPanel.add(layout);
		return result;
	}
	

	@Override
	public void onResize() {
		if (presenter != null) {
			presenter.onResize();
		}
		super.onResize();
	}

	@Override
	public void setPresenter(ImageRasterPresenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public void onMouseWheel(MouseWheelEvent event) {
		if(event.isNorth()) {
			presenter.onMouseWheelNorth();
		} else {
			presenter.onMouseWheelSouth();
		}
	}

	@Override
	public void animate(int i) {
		layout.animate(i);
	}

	@Override
	public void setPagingText(String text) {
		this.pagingText = text;
		pagingLabel.setText(text);
	}
	
}
