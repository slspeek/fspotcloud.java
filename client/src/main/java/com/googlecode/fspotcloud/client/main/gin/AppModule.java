/*
 * Copyright 2010-2012 Steven L. Speek.
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 *
 */
package com.googlecode.fspotcloud.client.main.gin;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.view.client.SelectionChangeEvent;

import com.google.inject.Singleton;
import com.google.inject.name.Names;

import com.googlecode.fspotcloud.client.data.DataManager;
import com.googlecode.fspotcloud.client.data.DataManagerImpl;
import com.googlecode.fspotcloud.client.demo.DemoStep;
import com.googlecode.fspotcloud.client.demo.DemoStepFactory;
import com.googlecode.fspotcloud.client.demo.ShortcutDemoStep;
import com.googlecode.fspotcloud.client.main.GlobalShortcutControllerFactory;
import com.googlecode.fspotcloud.client.main.IGlobalShortcutController;
import com.googlecode.fspotcloud.client.main.MVPSetup;
import com.googlecode.fspotcloud.client.main.ui.ButtonPanelViewImpl;
import com.googlecode.fspotcloud.client.main.ui.HelpPopup;
import com.googlecode.fspotcloud.client.main.ui.ImageRasterViewImpl;
import com.googlecode.fspotcloud.client.main.ui.ImageViewImpl;
import com.googlecode.fspotcloud.client.main.ui.LoadNewLocationImpl;
import com.googlecode.fspotcloud.client.main.ui.SingleImageViewImpl;
import com.googlecode.fspotcloud.client.main.ui.SlideshowViewImpl;
import com.googlecode.fspotcloud.client.main.ui.TagViewImpl;
import com.googlecode.fspotcloud.client.main.ui.TimerImpl;
import com.googlecode.fspotcloud.client.main.ui.TreeViewImpl;
import com.googlecode.fspotcloud.client.main.view.ImagePresenterImpl;
import com.googlecode.fspotcloud.client.main.view.ImageRasterPresenterImpl;
import com.googlecode.fspotcloud.client.main.view.MainWindowActivityMapper;
import com.googlecode.fspotcloud.client.main.view.SlideshowControlsPresenter;
import com.googlecode.fspotcloud.client.main.view.TagCell;
import com.googlecode.fspotcloud.client.main.view.TreePresenterImpl;
import com.googlecode.fspotcloud.client.main.view.TreeSelectionHandler;
import com.googlecode.fspotcloud.client.main.view.UserButtonPresenterImpl;
import com.googlecode.fspotcloud.client.main.view.api.ButtonPanelView;
import com.googlecode.fspotcloud.client.main.view.api.ImagePresenterFactory;
import com.googlecode.fspotcloud.client.main.view.api.ImageRasterPresenterFactory;
import com.googlecode.fspotcloud.client.main.view.api.ImageRasterView;
import com.googlecode.fspotcloud.client.main.view.api.ImageView;
import com.googlecode.fspotcloud.client.main.view.api.ImageViewFactory;
import com.googlecode.fspotcloud.client.main.view.api.LoadNewLocation;
import com.googlecode.fspotcloud.client.main.view.api.PopupView;
import com.googlecode.fspotcloud.client.main.view.api.SingleImageView;
import com.googlecode.fspotcloud.client.main.view.api.SingleViewActivityFactory;
import com.googlecode.fspotcloud.client.main.view.api.SlideshowView;
import com.googlecode.fspotcloud.client.main.view.api.TagPresenterFactory;
import com.googlecode.fspotcloud.client.main.view.api.TagView;
import com.googlecode.fspotcloud.client.main.view.api.TimerInterface;
import com.googlecode.fspotcloud.client.main.view.api.TreeSelectionHandlerInterface;
import com.googlecode.fspotcloud.client.main.view.api.TreeView;
import com.googlecode.fspotcloud.client.main.view.api.UserButtonPresenterFactory;
import com.googlecode.fspotcloud.client.main.view.api.UserButtonView;
import com.googlecode.fspotcloud.client.main.view.api.UserButtonViewFactory;
import com.googlecode.fspotcloud.client.main.view.factory.ButtonPanelPresenterProvider;
import com.googlecode.fspotcloud.client.main.view.factory.SingleImageViewActivityFactoryImpl;
import com.googlecode.fspotcloud.client.main.view.factory.SlideshowControlsPresenterProvider;
import com.googlecode.fspotcloud.client.main.view.factory.SlideshowPresenterFactoryImpl;
import com.googlecode.fspotcloud.client.main.view.factory.TagPresenterFactoryImpl;
import com.googlecode.fspotcloud.client.main.view.factory.UserButtonViewFactoryImpl;
import com.googlecode.fspotcloud.client.place.NavigatorImpl;
import com.googlecode.fspotcloud.client.place.PlaceCalculator;
import com.googlecode.fspotcloud.client.place.PlaceGoToImpl;
import com.googlecode.fspotcloud.client.place.PlaceWhereImpl;
import com.googlecode.fspotcloud.client.place.SlideshowImpl;
import com.googlecode.fspotcloud.client.place.api.Navigator;
import com.googlecode.fspotcloud.client.place.api.PlaceGoTo;
import com.googlecode.fspotcloud.client.place.api.PlaceWhere;
import com.googlecode.fspotcloud.client.place.api.Slideshow;
import com.googlecode.fspotcloud.client.view.action.KeyDispatcherProvider;
import com.googlecode.fspotcloud.client.view.action.api.LoadNewLocationActionFactory;
import com.googlecode.fspotcloud.client.view.action.api.ShortcutHandler;


public class AppModule extends AbstractGinModule {
    @Override
    protected void configure() {
        bind(MainWindowActivityMapper.class).in(Singleton.class);
        bind(DataManager.class).to(DataManagerImpl.class).in(Singleton.class);
        bind(MVPSetup.class).in(Singleton.class);
        bind(TagCell.class);
        bind(TagView.class).to(TagViewImpl.class).in(Singleton.class);
        bind(TreeView.class).to(TreeViewImpl.class).in(Singleton.class);
        bind(ImageRasterView.class).to(ImageRasterViewImpl.class);
        bind(SingleViewActivityFactory.class)
            .to(SingleImageViewActivityFactoryImpl.class);
        bind(SingleImageView.class).to(SingleImageViewImpl.class);

        bind(PlaceCalculator.class);
        bind(PlaceGoTo.class).to(PlaceGoToImpl.class);
        bind(PlaceWhere.class).to(PlaceWhereImpl.class);
        bind(PlaceController.class).toProvider(PlaceControllerProvider.class);
        bind(PlaceControllerProvider.class).in(Singleton.class);
        bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);
        bind(KeyDispatcherProvider.class).in(Singleton.class);
        bind(ShortcutHandler.class).toProvider(KeyDispatcherProvider.class);
        bind(SlideshowView.class).to(SlideshowViewImpl.class).in(
            Singleton.class);
        bind(TimerInterface.class).to(TimerImpl.class);
        bind(Navigator.class).to(NavigatorImpl.class).in(Singleton.class);
        bind(Slideshow.class).to(SlideshowImpl.class).in(Singleton.class);
        bind(TagPresenterFactory.class).to(TagPresenterFactoryImpl.class);
        bind(TreeView.TreePresenter.class).to(TreePresenterImpl.class)
            .in(Singleton.class);
        bind(SelectionChangeEvent.Handler.class).to(TreeSelectionHandler.class)
            .in(Singleton.class);
        bind(IGlobalShortcutController.class)
            .toProvider(GlobalShortcutControllerFactory.class)
            .in(Singleton.class);
        bind(SlideshowView.SlideshowPresenter.class)
            .toProvider(SlideshowPresenterFactoryImpl.class);
        install(
            new GinFactoryModuleBuilder().implement(
                ImageRasterView.ImageRasterPresenter.class,
                ImageRasterPresenterImpl.class)
                                         .build(
                ImageRasterPresenterFactory.class));
        install(
            new GinFactoryModuleBuilder().implement(
                ImageView.ImagePresenter.class, ImagePresenterImpl.class)
                                         .build(ImagePresenterFactory.class));
        install(
            new GinFactoryModuleBuilder().implement(
                ImageView.class, ImageViewImpl.class)
                                         .build(ImageViewFactory.class));

        install(
            new GinFactoryModuleBuilder().implement(
                DemoStep.class, ShortcutDemoStep.class)
                                         .build(DemoStepFactory.class));

        bind(UserButtonViewFactory.class).to(UserButtonViewFactoryImpl.class);
        install(
            new GinFactoryModuleBuilder().implement(
                UserButtonView.UserButtonPresenter.class,
                UserButtonPresenterImpl.class)
                                         .build(
                UserButtonPresenterFactory.class));

        bind(SlideshowControlsPresenter.class)
            .toProvider(SlideshowControlsPresenterProvider.class);

        bind(ButtonPanelView.ButtonPanelPresenter.class)
            .toProvider(ButtonPanelPresenterProvider.class);
        bind(ButtonPanelView.class).annotatedWith(Names.named("Main"))
            .to(ButtonPanelViewImpl.class).in(Singleton.class);
        bind(ButtonPanelView.class).annotatedWith(Names.named("Slideshow"))
            .to(ButtonPanelViewImpl.class).in(Singleton.class);
        bind(TreeSelectionHandlerInterface.class).to(
            TreeSelectionHandler.class);
        install(
            new GinFactoryModuleBuilder().build(
                LoadNewLocationActionFactory.class));
        bind(LoadNewLocation.class).to(LoadNewLocationImpl.class);

        bind(PopupView.class).to(HelpPopup.class);
    }
}
