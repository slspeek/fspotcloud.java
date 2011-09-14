package fspotcloud.client.main.gin;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.inject.Singleton;

import fspotcloud.client.data.DataManager;
import fspotcloud.client.data.DataManagerImpl;
import fspotcloud.client.demo.DemoStep;
import fspotcloud.client.demo.DemoStepFactory;
import fspotcloud.client.demo.ShortcutDemoStep;
import fspotcloud.client.main.MVPSetup;
import fspotcloud.client.main.ui.ButtonPanelViewImpl;
import fspotcloud.client.main.ui.HelpPopup;
import fspotcloud.client.main.ui.ImagePanelViewImpl;
import fspotcloud.client.main.ui.ImageRasterViewImpl;
import fspotcloud.client.main.ui.ImageViewImpl;
import fspotcloud.client.main.ui.LoadNewLocationImpl;
import fspotcloud.client.main.ui.SlideshowViewImpl;
import fspotcloud.client.main.ui.TagViewImpl;
import fspotcloud.client.main.ui.TimerImpl;
import fspotcloud.client.main.ui.TreeViewImpl;
import fspotcloud.client.main.view.ButtonPanelPresenterImpl;
import fspotcloud.client.main.view.ImagePanelActivity;
import fspotcloud.client.main.view.ImagePresenterImpl;
import fspotcloud.client.main.view.ImageRasterActivity;
import fspotcloud.client.main.view.ImageRasterPresenterImpl;
import fspotcloud.client.main.view.MainWindowActivityMapper;
import fspotcloud.client.main.view.SlideshowPresenterImpl;
import fspotcloud.client.main.view.TagCell;
import fspotcloud.client.main.view.TreePresenterImpl;
import fspotcloud.client.main.view.TreeSelectionHandler;
import fspotcloud.client.main.view.UserButtonPresenterImpl;
import fspotcloud.client.main.view.api.ButtonPanelPresenterFactory;
import fspotcloud.client.main.view.api.ButtonPanelView;
import fspotcloud.client.main.view.api.ImagePanelActivityFactory;
import fspotcloud.client.main.view.api.ImagePanelPresenterAssistedFactory;
import fspotcloud.client.main.view.api.ImagePanelView;
import fspotcloud.client.main.view.api.ImagePresenterFactory;
import fspotcloud.client.main.view.api.ImageRasterActivityFactory;
import fspotcloud.client.main.view.api.ImageRasterPresenterFactory;
import fspotcloud.client.main.view.api.ImageRasterView;
import fspotcloud.client.main.view.api.ImageView;
import fspotcloud.client.main.view.api.ImageViewFactory;
import fspotcloud.client.main.view.api.LoadNewLocation;
import fspotcloud.client.main.view.api.PopupView;
import fspotcloud.client.main.view.api.SlideshowPresenterFactory;
import fspotcloud.client.main.view.api.SlideshowView;
import fspotcloud.client.main.view.api.TagPresenterFactory;
import fspotcloud.client.main.view.api.TagView;
import fspotcloud.client.main.view.api.TimerInterface;
import fspotcloud.client.main.view.api.TreeSelectionHandlerInterface;
import fspotcloud.client.main.view.api.TreeView;
import fspotcloud.client.main.view.api.UserButtonPresenterFactory;
import fspotcloud.client.main.view.api.UserButtonView;
import fspotcloud.client.main.view.api.UserButtonViewFactory;
import fspotcloud.client.main.view.factory.ImagePanelActivityFactoryImpl;
import fspotcloud.client.main.view.factory.TagPresenterFactoryImpl;
import fspotcloud.client.main.view.factory.UserButtonViewFactoryImpl;
import fspotcloud.client.place.NavigatorImpl;
import fspotcloud.client.place.PlaceCalculator;
import fspotcloud.client.place.PlaceGoToImpl;
import fspotcloud.client.place.PlaceWhereImpl;
import fspotcloud.client.place.SlideshowImpl;
import fspotcloud.client.place.api.Navigator;
import fspotcloud.client.place.api.PlaceGoTo;
import fspotcloud.client.place.api.PlaceWhere;
import fspotcloud.client.place.api.Slideshow;
import fspotcloud.client.view.action.KeyDispatcherProvider;
import fspotcloud.client.view.action.api.LoadNewLocationActionFactory;
import fspotcloud.client.view.action.api.ShortcutHandler;

public class AppModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(MainWindowActivityMapper.class).in(Singleton.class);
		bind(DataManager.class).to(DataManagerImpl.class).in(Singleton.class);
		bind(MVPSetup.class).in(Singleton.class);
		bind(PlaceCalculator.class);
		bind(TagCell.class);
		bind(TagView.class).to(TagViewImpl.class);
		bind(ImagePanelView.class).to(ImagePanelViewImpl.class);
		bind(PlaceGoTo.class).to(PlaceGoToImpl.class);
		bind(PlaceWhere.class).to(PlaceWhereImpl.class);
		bind(PlaceController.class).toProvider(PlaceControllerProvider.class);
		bind(PlaceControllerProvider.class).in(Singleton.class);
		bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);
		bind(KeyDispatcherProvider.class).in(Singleton.class);
		bind(ShortcutHandler.class).toProvider(KeyDispatcherProvider.class);
		bind(SlideshowView.class).to(SlideshowViewImpl.class);
		bind(SlideshowView.SlideshowPresenter.class).to(
				SlideshowPresenterImpl.class);
		bind(TimerInterface.class).to(TimerImpl.class);
		bind(TagPresenterFactory.class).to(TagPresenterFactoryImpl.class);
		bind(TreeView.TreePresenter.class).to(TreePresenterImpl.class).in(
				Singleton.class);
		bind(SelectionChangeEvent.Handler.class).to(TreeSelectionHandler.class)
				.in(Singleton.class);
		bind(TreeView.class).to(TreeViewImpl.class).in(Singleton.class);
		bind(ImagePanelActivityFactory.class).to(
				ImagePanelActivityFactoryImpl.class);
		bind(Navigator.class).to(NavigatorImpl.class).in(Singleton.class);
		bind(Slideshow.class).to(SlideshowImpl.class).in(Singleton.class);
		bind(ImageRasterView.class).to(ImageRasterViewImpl.class);
		install(new GinFactoryModuleBuilder().implement(
				SlideshowView.SlideshowPresenter.class,
				SlideshowPresenterImpl.class).build(
				SlideshowPresenterFactory.class));
		install(new GinFactoryModuleBuilder().implement(
				ImageRasterView.ImageRasterPresenter.class,
				ImageRasterPresenterImpl.class).build(
				ImageRasterPresenterFactory.class));
		install(new GinFactoryModuleBuilder().implement(
				ImageView.ImagePresenter.class, ImagePresenterImpl.class)
				.build(ImagePresenterFactory.class));
		install(new GinFactoryModuleBuilder().implement(ImageView.class,
				ImageViewImpl.class).build(ImageViewFactory.class));

		install(new GinFactoryModuleBuilder().implement(DemoStep.class,
				ShortcutDemoStep.class).build(DemoStepFactory.class));

		install(new GinFactoryModuleBuilder().implement(
				ImagePanelView.ImagePanelPresenter.class,
				ImagePanelActivity.class).build(
				ImagePanelPresenterAssistedFactory.class));
		bind(UserButtonViewFactory.class).to(UserButtonViewFactoryImpl.class);
		install(new GinFactoryModuleBuilder().implement(
				UserButtonView.UserButtonPresenter.class,
				UserButtonPresenterImpl.class).build(
				UserButtonPresenterFactory.class));
		install(new GinFactoryModuleBuilder().implement(
				ButtonPanelView.ButtonPanelPresenter.class,
				ButtonPanelPresenterImpl.class).build(
				ButtonPanelPresenterFactory.class));
		bind(ButtonPanelView.class).to(ButtonPanelViewImpl.class);
		bind(TreeSelectionHandlerInterface.class)
				.to(TreeSelectionHandler.class);
		install(new GinFactoryModuleBuilder()
				.build(LoadNewLocationActionFactory.class));
		bind(LoadNewLocation.class).to(LoadNewLocationImpl.class);

		install(new GinFactoryModuleBuilder().implement(
				ImageRasterView.ImageRasterPresenter.class,
				ImageRasterActivity.class).build(
				ImageRasterActivityFactory.class));

		bind(PopupView.class).to(HelpPopup.class);
	}
}
