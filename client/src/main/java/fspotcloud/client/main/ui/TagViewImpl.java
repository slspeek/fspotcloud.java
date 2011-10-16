package fspotcloud.client.main.ui;

import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.name.Named;

import fspotcloud.client.main.view.api.ButtonPanelView;
import fspotcloud.client.main.view.api.ImageRasterView;
import fspotcloud.client.main.view.api.TagView;
import fspotcloud.client.main.view.api.TimerInterface;
import fspotcloud.client.main.view.api.TreeView;

public class TagViewImpl extends Composite implements TagView {

	private static final Logger log = Logger.getLogger(TagViewImpl.class
			.getName());
	private static TagViewImplUiBinder uiBinder = GWT
			.create(TagViewImplUiBinder.class);

	interface TagViewImplUiBinder extends UiBinder<Widget, TagViewImpl> {
	}

	private final TreeView treeView;
	private final ButtonPanelView buttonPanelView;
	@UiField
	ImageRasterView imageRasterView;
	private final TimerInterface timer;
	private TagPresenter presenter;

	@UiField
	FocusPanel focusPanel;
	@UiField
	LayoutPanel mainPanel;

	@Inject
	public TagViewImpl(TreeView treeView, @Named("Main") ButtonPanelView buttonPanelView,
			ImageRasterView imageRasterView, TimerInterface timer) {
		this.timer = timer;
		this.treeView = treeView;
		this.buttonPanelView = buttonPanelView;
		this.imageRasterView = imageRasterView;
		initWidget(uiBinder.createAndBindUi(this));
		treeView.asWidget().addStyleName("fsc-tag-tree-container");
		hideLabelLater(3000);
	}

	@UiHandler("focusPanel")
	public void infoHover(MouseMoveEvent event) {
		log.info("image mouse move");
		// presenter.imageDoubleClicked();
		mainPanel.setWidgetBottomHeight(buttonPanelView, 0, Unit.CM, 10, Unit.PCT);
		mainPanel.setWidgetTopHeight(imageRasterView, 0, Unit.CM, 90, Unit.PCT);
		mainPanel.setWidgetRightWidth(imageRasterView, 0, Unit.CM, 78, Unit.PCT);
		mainPanel.setWidgetLeftWidth(treeView, 0, Unit.PCT, 22, Unit.PCT);
		// layout.setWidgetLeftRight(info, 25, Unit.PCT, 25, Unit.PCT);
		mainPanel.animate(500);
		hideLabelLater(3000);

	}

	public void hideLabelLater(final int duration) {
		timer.setRunnable(new Runnable() {

			@Override
			public void run() {
				mainPanel.setWidgetBottomHeight(buttonPanelView, 0, Unit.CM, 0, Unit.PX);
				mainPanel.setWidgetTopHeight(imageRasterView, 0, Unit.CM, 100, Unit.PCT);
				mainPanel.setWidgetRightWidth(imageRasterView, 0, Unit.CM, 100, Unit.PCT);
				mainPanel.setWidgetLeftWidth(treeView, 0, Unit.PCT, 0, Unit.PCT);
				// mainPanel.setWidgetLeftRight(info, 25, Unit.PCT, 25, Unit.PCT);
				mainPanel.animate(500);

			}
		});
		timer.schedule(duration);
	}
	
	
	
	
	@UiFactory
	public TreeViewImpl getView() {
		return (TreeViewImpl) treeView;
	}

	@UiFactory
	public ButtonPanelViewImpl getButtonView() {
		return (ButtonPanelViewImpl) buttonPanelView;
	}

	@UiFactory
	public ImageRasterViewImpl getImageRasterView() {
		return (ImageRasterViewImpl) imageRasterView;
	}

	@Override
	public void setPresenter(TagPresenter presenter) {
		this.presenter = presenter;
	}
}
