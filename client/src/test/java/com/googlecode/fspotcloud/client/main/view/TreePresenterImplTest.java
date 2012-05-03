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
package com.googlecode.fspotcloud.client.main.view;

import com.google.gwt.view.client.SingleSelectionModel;
import com.google.gwt.view.client.TreeViewModel;

import com.googlecode.fspotcloud.client.data.DataManager;
import com.googlecode.fspotcloud.client.data.DataManagerImpl;
import com.googlecode.fspotcloud.client.main.DispatchAsyncTestImpl;
import com.googlecode.fspotcloud.client.main.view.api.TreeSelectionHandlerInterface;
import com.googlecode.fspotcloud.client.main.view.api.TreeView;
import com.googlecode.fspotcloud.shared.tag.TagNode;

import junit.framework.TestCase;

import org.jmock.Expectations;
import org.jmock.Mockery;


public class TreePresenterImplTest extends TestCase {
    Mockery context;
    TreeView.TreePresenter presenter;
    TreeSelectionHandlerInterface handler;
    TreeView treeView;
    DataManager dataManager = new DataManagerImpl(new DispatchAsyncTestImpl());
    SingleSelectionModel<TagNode> model = new SingleSelectionModel<TagNode>();

    @Override
    protected void setUp() throws Exception {
        context = new Mockery();
        handler = context.mock(TreeSelectionHandlerInterface.class);
        treeView = context.mock(TreeView.class);
        super.setUp();
    }


    public void testConstructor() {
        presenter = new TreePresenterImpl(
                treeView, dataManager, model, handler);
        assertNotNull(presenter);
    }


    public void testInit() {
        testConstructor();
        context.checking(
            new Expectations() {

                {
                    oneOf(handler).setSelectionModel(with(model));
                    oneOf(treeView).setTreeModel(
                        with(any(TreeViewModel.class)));
                }
            });
        presenter.init();
        context.assertIsSatisfied();
    }

    //	public void testSetPlace() {
    //		testConstructor();
    //		BasePlace place = new BasePlace("1", "1");
    //		presenter.setPlace(place);
    //	}
}
