package fspotcloud.client.main.ui;

import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOverEvent;
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
	FocusPanel horizontalFocusPanel;
	@UiField
	FocusPanel verticalFocusPanel;
	@UiField
	FocusPanel treeFocusPanel;
		
	@UiField
	LayoutPanel mainPanel;
	static int ID;
	int id = ID++;
	@Inject
	public TagViewImpl(TreeView treeView,
			@Named("Main") ButtonPanelView buttonPanelView,
			ImageRasterView imageRasterView, TimerInterface timer) {
		this.timer = timer;
		this.treeView = treeView;
		this.buttonPanelView = buttonPanelView;
		this.imageRasterView = imageRasterView;
		initWidget(uiBinder.createAndBindUi(this));
		log.info("Created " +this);
	}

	@UiHandler("horizontalFocusPanel")
	public void infoHover(MouseOverEvent event) {
		log.info("horizontal mouse over");
		animateControlsIn(600);
		// hideLabelLater(3000);

	}

	@UiHandler("verticalFocusPanel")
	public void verticalMousePanel(MouseOverEvent event) {
		animateControlsIn(600);
		log.info("vertical mouse over");
	}
	
	@UiHandler("treeFocusPanel")
	public void treeOut(MouseOutEvent event) {
		animateControlsOut(1000);
		log.info("vertical mouse over");
	}
	

	public void animateControlsIn(int duration) {
		mainPanel.setWidgetBottomHeight(buttonPanelView, 0, Unit.CM, 10,
				Unit.PCT);
		mainPanel.setWidgetTopHeight(imageRasterView, 0, Unit.CM, 90, Unit.PCT);
		mainPanel
				.setWidgetRightWidth(imageRasterView, 0, Unit.CM, 78, Unit.PCT);
		mainPanel.setWidgetLeftWidth(treeFocusPanel, 0, Unit.PCT, 22, Unit.PCT);
		mainPanel.setWidgetTopHeight(treeFocusPanel, 0, Unit.PCT, 90, Unit.PCT);

		mainPanel.setWidgetBottomHeight(horizontalFocusPanel, 0, Unit.PCT, 0,
				Unit.PCT);
		mainPanel.setWidgetLeftWidth(verticalFocusPanel, 0, Unit.PCT, 0,
				Unit.PCT);

		mainPanel.animate(duration);

	}

	public void animateControlsOut(int duration) {
		mainPanel
				.setWidgetBottomHeight(buttonPanelView, 0, Unit.CM, 0, Unit.PX);
		mainPanel
				.setWidgetTopHeight(imageRasterView, 0, Unit.CM, 100, Unit.PCT);
		mainPanel.setWidgetRightWidth(imageRasterView, 0, Unit.CM, 100,
				Unit.PCT);
		mainPanel.setWidgetLeftWidth(treeFocusPanel, 0, Unit.PCT, 0, Unit.PCT);
		mainPanel.setWidgetTopHeight(treeFocusPanel, 0, Unit.PCT, 100, Unit.PCT);

		mainPanel.setWidgetBottomHeight(horizontalFocusPanel, 0, Unit.PCT, 10,
				Unit.PCT);
		mainPanel.setWidgetLeftWidth(verticalFocusPanel, 0, Unit.PCT, 10,
				Unit.PCT);

		mainPanel.animate(duration);

	}

	@Override
	public void hideLabelLater(final int duration) {
		timer.setRunnable(new Runnable() {

			@Override
			public void run() {
				animateControlsOut(1000);
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
	
	public String toString() {
		return "TagView:"+id;
	}
}
