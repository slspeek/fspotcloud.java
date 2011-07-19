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
import fspotcloud.client.main.TagServiceAsyncTestImpl;
import fspotcloud.client.main.shared.ApplicationEventFactory;
import fspotcloud.client.main.shared.ApplicationEventProviderFactory;
import fspotcloud.client.main.shared.NavigationEventFactory;
import fspotcloud.client.main.shared.NavigationEventProviderFactory;
import fspotcloud.client.main.shared.RasterEventFactory;
import fspotcloud.client.main.shared.RasterEventProviderFactory;
import fspotcloud.client.main.shared.SlideshowEventFactory;
import fspotcloud.client.main.shared.SlideshowEventProviderFactory;
import fspotcloud.client.main.ui.ImagePanelViewImpl;
import fspotcloud.client.main.ui.ImageRasterViewImpl;
import fspotcloud.client.main.ui.ImageViewImpl;
import fspotcloud.client.main.ui.PagerViewImpl;
import fspotcloud.client.main.ui.SlideshowViewImpl;
import fspotcloud.client.main.ui.TagViewImpl;
import fspotcloud.client.main.ui.TimerImpl;
import fspotcloud.client.main.ui.TreeViewImpl;
import fspotcloud.client.main.view.FullscreenImagePanelActivity;
import fspotcloud.client.main.view.ImagePanelActivity;
import fspotcloud.client.main.view.ImagePresenterImpl;
import fspotcloud.client.main.view.ImageRasterPresenterImpl;
import fspotcloud.client.main.view.MainWindowActivityMapper;
import fspotcloud.client.main.view.PagerPresenterImpl;
import fspotcloud.client.main.view.SlideshowPresenterImpl;
import fspotcloud.client.main.view.TagCell;
import fspotcloud.client.main.view.TreePresenterImpl;
import fspotcloud.client.main.view.TreeSelectionHandler;
import fspotcloud.client.main.view.api.EmbeddedImagePanelPresenterAssistedFactory;
import fspotcloud.client.main.view.api.FullscreenImagePanelPresenterAssistedFactory;
import fspotcloud.client.main.view.api.ImagePanelActivityFactory;
import fspotcloud.client.main.view.api.ImagePanelView;
import fspotcloud.client.main.view.api.ImagePresenterFactory;
import fspotcloud.client.main.view.api.ImageRasterPresenterFactory;
import fspotcloud.client.main.view.api.ImageRasterView;
import fspotcloud.client.main.view.api.ImageView;
import fspotcloud.client.main.view.api.ImageViewFactory;
import fspotcloud.client.main.view.api.PagerPresenterFactory;
import fspotcloud.client.main.view.api.PagerView;
import fspotcloud.client.main.view.api.SlideshowPresenterFactory;
import fspotcloud.client.main.view.api.SlideshowView;
import fspotcloud.client.main.view.api.TagPresenterFactory;
import fspotcloud.client.main.view.api.TagView;
import fspotcloud.client.main.view.api.TimerInterface;
import fspotcloud.client.main.view.api.TreeView;
import fspotcloud.client.main.view.factory.ImagePanelActivityFactoryImpl;
import fspotcloud.client.main.view.factory.TagPresenterFactoryImpl;
import fspotcloud.client.place.NavigatorImpl;
import fspotcloud.client.place.PlaceCalculator;
import fspotcloud.client.place.PlaceGoToImpl;
import fspotcloud.client.place.PlaceWhereImpl;
import fspotcloud.client.place.SlideshowImpl;
import fspotcloud.client.place.api.Navigator;
import fspotcloud.client.place.api.PlaceGoTo;
import fspotcloud.client.place.api.PlaceWhere;
import fspotcloud.client.place.api.Slideshow;
import fspotcloud.client.view.action.AllShortcuts;
import fspotcloud.client.view.action.ApplicationActionsImpl;
import fspotcloud.client.view.action.KeyDispatcherProvider;
import fspotcloud.client.view.action.NavigationActionsImpl;
import fspotcloud.client.view.action.RasterActionsImpl;
import fspotcloud.client.view.action.Shortcut;
import fspotcloud.client.view.action.SlideshowActionsImpl;
import fspotcloud.client.view.action.api.AllUserActions;
import fspotcloud.client.view.action.api.ApplicationActions;
import fspotcloud.client.view.action.api.NavigationActions;
import fspotcloud.client.view.action.api.RasterActions;
import fspotcloud.client.view.action.api.ShortcutAssistedFactory;
import fspotcloud.client.view.action.api.ShortcutHandler;
import fspotcloud.client.view.action.api.SlideshowActions;
import fspotcloud.client.view.action.api.UserAction;
import fspotcloud.rpc.TagServiceAsync;

public class FakeForGrapherAppModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(MainWindowActivityMapper.class);
		bind(DataManager.class).to(DataManagerImpl.class);
		bind(MVPSetup.class);
		bind(PlaceCalculator.class);
		bind(TagCell.class);
		bind(TagView.class).to(TagViewImpl.class);
		bind(ImagePanelView.class).to(ImagePanelViewImpl.class);
		bind(PlaceGoTo.class).to(PlaceGoToImpl.class);
		bind(PlaceWhere.class).to(PlaceWhereImpl.class);
		bind(PlaceController.class).toProvider(PlaceControllerProvider.class);
		bind(PlaceControllerProvider.class);
		bind(EventBus.class).to(SimpleEventBus.class);
		bind(KeyDispatcherProvider.class);
		bind(ShortcutHandler.class).toProvider(KeyDispatcherProvider.class);
		bind(SlideshowView.class).to(SlideshowViewImpl.class);
		bind(SlideshowView.SlideshowPresenter.class).to(
				SlideshowPresenterImpl.class);
		bind(PagerView.class).to(PagerViewImpl.class);
		bind(TimerInterface.class).to(TimerImpl.class);
		bind(TagPresenterFactory.class).to(TagPresenterFactoryImpl.class);
		bind(TreeView.TreePresenter.class).to(TreePresenterImpl.class);
		bind(SelectionChangeEvent.Handler.class).to(TreeSelectionHandler.class);
		bind(TreeView.class).to(TreeViewImpl.class);
		bind(ImagePanelActivityFactory.class).to(
				ImagePanelActivityFactoryImpl.class);
		bind(Navigator.class).to(NavigatorImpl.class);
		bind(Slideshow.class).to(SlideshowImpl.class);
		bind(ImageRasterView.class).to(ImageRasterViewImpl.class);
		install(new GinFactoryModuleBuilder().implement(
				PagerView.PagerPresenter.class, PagerPresenterImpl.class)
				.build(PagerPresenterFactory.class));
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
				FullscreenImagePanelActivity.class).build(
				FullscreenImagePanelPresenterAssistedFactory.class));

		install(new GinFactoryModuleBuilder().implement(
				ImagePanelView.ImagePanelPresenter.class,
				ImagePanelActivity.class).build(
				EmbeddedImagePanelPresenterAssistedFactory.class));

		bind(AllUserActions.class).to(AllShortcuts.class);

		bind(NavigationActions.class).to(NavigationActionsImpl.class);
		bind(RasterActions.class).to(RasterActionsImpl.class);
		bind(SlideshowActions.class).to(SlideshowActionsImpl.class);
		bind(ApplicationActions.class).to(ApplicationActionsImpl.class);
		bind(TagServiceAsync.class).to(TagServiceAsyncTestImpl.class);

		install(new GinFactoryModuleBuilder()
				.build(NavigationEventProviderFactory.class));
		install(new GinFactoryModuleBuilder()
				.build(SlideshowEventProviderFactory.class));
		install(new GinFactoryModuleBuilder()
				.build(RasterEventProviderFactory.class));
		install(new GinFactoryModuleBuilder()
				.build(ApplicationEventProviderFactory.class));

		install(new GinFactoryModuleBuilder()
				.build(NavigationEventFactory.class));
		install(new GinFactoryModuleBuilder()
				.build(SlideshowEventFactory.class));
		install(new GinFactoryModuleBuilder()
				.build(ApplicationEventFactory.class));
		install(new GinFactoryModuleBuilder().build(RasterEventFactory.class));

		install(new GinFactoryModuleBuilder().implement(UserAction.class,
				Shortcut.class).build(ShortcutAssistedFactory.class));

	}
}
