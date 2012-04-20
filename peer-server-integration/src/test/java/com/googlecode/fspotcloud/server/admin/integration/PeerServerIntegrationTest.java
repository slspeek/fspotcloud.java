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
package com.googlecode.fspotcloud.server.admin.integration;

import com.google.common.testing.TearDown;

import com.google.guiceberry.testng.TestNgGuiceBerry;

import com.google.gwt.user.client.rpc.AsyncCallback;

import com.googlecode.botdispatch.SerializableAsyncCallback;
import com.googlecode.botdispatch.controller.dispatch.ControllerDispatchAsync;

import com.googlecode.fspotcloud.peer.db.Data;
import com.googlecode.fspotcloud.server.model.api.PeerDatabase;
import com.googlecode.fspotcloud.server.model.api.PeerDatabases;
import com.googlecode.fspotcloud.server.model.api.Photos;
import com.googlecode.fspotcloud.server.model.api.Tags;
import com.googlecode.fspotcloud.shared.dashboard.actions.*;
import com.googlecode.fspotcloud.shared.peer.rpc.actions.GetPeerMetaData;
import com.googlecode.fspotcloud.shared.peer.rpc.actions.PeerMetaDataResult;

import net.customware.gwt.dispatch.server.Dispatch;
import net.customware.gwt.dispatch.shared.DispatchException;
import static org.testng.AssertJUnit.*;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;

import java.lang.reflect.Method;

import java.sql.SQLException;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;


public class PeerServerIntegrationTest {
    static final Logger log = Logger.getLogger(
            PeerServerIntegrationTest.class.getName());
    @Inject
    Photos photos;
    @Inject
    protected Tags tags;
    @Inject
    ControllerDispatchAsync controller;
    @Inject
    PeerDatabases peers;
    @Inject
    Data data;
    @Inject
    Dispatch dispatch;
    @Inject
    PhotoAssert photoInfo;
    @Inject
    TagAssert tagInfo;
    private TearDown toTearDown;

    @BeforeMethod
    public void setUp(Method m) throws SQLException {
        // Make this the call to TestNgGuiceBerry.setUp as early as possible
        toTearDown = TestNgGuiceBerry.setUp(this, m, EmptyGuiceBerryEnv.class);
        setUpPeer();
    }


    private void setUpPeer() throws SQLException {
        setPeerTestDatabase("photos.db");
    }


    @AfterMethod
    public void tearDown() throws Exception {
        tags.deleteBulk(1000);
        photos.deleteBulk(1000);
        // Make this the call to TestNgGuiceBerry.tearDown as late as possible
        toTearDown.tearDown();
    }


    @Test
    public void testImportAllTags() throws Exception {
        setUpPeer();

        PeerDatabase peer = peers.get();
        peers.save(peer);
        synchronizePeer();
        verifyAllTagsAreLoaded();
    }


    private void verifyAllTagsAreLoaded() {
        tagInfo.assertTagsLoaded("1", "2", "3", "4", "5");
    }


    @Test
    public void testGetPeerMetaData() throws DispatchException, SQLException {
        setUpPeer();

        PeerMetaDataResult result = dispatch.execute(new GetPeerMetaData());
        assertEquals(28, result.getPhotoCount());
        assertEquals(5, result.getTagCount());
    }


    @Test
    public void testImportGlass() throws Exception {
        setUpPeer();
        testImportAllTags();
        importTag("5");
        photos.find("3");
    }


    @Test
    public void testImportFurniture() throws Exception {
        testImportAllTags();
        importTag("1");
        verfiyFurnitureIsLoaded();
    }


    @Test
    public void testImportFurnitureInTwoPhases() throws Exception {
        testImportAllTags();
        setPeerTestDatabase("photos_smaller.db");
        importTag("1");
        verfiyFurnitureFirstPhaseIsLoaded();
        setUpPeer();
        synchronizePeer();
        verfiyFurnitureIsLoaded();
    }


    @Test
    public void testUImportFurniture() throws Exception {
        testImportAllTags();
        setPeerTestDatabase("photos_smaller.db");
        importTag("1");
        verfiyFurnitureFirstPhaseIsLoaded();
        unImportTag("1");
        verifiyFurnitureFirstPhaseWasRemoved();
    }


    @Test
    public void shouldRemoveSomeFurniture() throws Exception {
        testImportAllTags();
        importTag("1");
        verfiyFurnitureIsLoaded();
        setPeerTestDatabase("photos_smaller.db");
        synchronizePeer();
        verfiyFurnitureFirstPhaseIsLoaded();
        verifyImagesWereRemoved();
    }


    private void verifiyFurnitureFirstPhaseWasRemoved() {
        photoInfo.assertPhotosRemoved("12", "13", "4", "5", "15");
    }


    @Test
    public void testImportFurnitureInThreePhases() throws Exception {
        testImportAllTags();
        setPeerTestDatabase("photos_smaller.db");
        importTag("1");
        verfiyFurnitureFirstPhaseIsLoaded();
        setUpPeer();
        synchronizePeer();
        verfiyFurnitureIsLoaded();
        setPeerTestDatabase("photos_smaller.db");
        synchronizePeer();
        //verifyImagesWereRemoved();
        photoInfo.assertPhotosRemoved("6");
        verfiyFurnitureFirstPhaseIsLoaded();
    }


    private void verifyImagesWereRemoved() {
        photoInfo.assertPhotosRemoved("7", "6", "16", "14");
    }


    @Test
    public void testRemovingOfTags() throws Exception {
        testImportAllTags();
        importTag("2");
        assertComputersIsLoaded();
        importTag("4");
        assertPCLoaded();
        dispatch.execute(new TagDeleteAll());
        assertTrue(photoInfo.isEmpty());
        tagInfo.assertTagsRemoved("1", "2", "3", "4", "5");
    }


    private void verfiyFurnitureFirstPhaseIsLoaded() {
        photoInfo.assertPhotosLoaded("12", "13", "4", "5", "15");
    }


    private void verfiyFurnitureIsLoaded() {
        photoInfo.assertPhotosLoaded("12", "13", "4", "5", "15");
        photoInfo.assertPhotosLoaded("7", "6", "16", "14");
    }


    @Test
    public void testOverlapWithTagRemoval() throws Exception {
        testImportAllTags();
        importTag("2");
        assertComputersIsLoaded();
        importTag("4");
        assertPCLoaded();
        unImportTag("2");
        assertPCLoaded();
        unImportTag("4");
        photoInfo.assertPhotosRemoved(
            "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27",
            "28", "9", "11");
    }


    private void assertPCLoaded() {
        photoInfo.assertPhotosLoaded(
            "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27",
            "28", "9", "11");
    }


    private void assertComputersIsLoaded() {
        photoInfo.assertPhotosLoaded(
            "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27",
            "28");
    }


    protected void setPeerTestDatabase(String db) throws SQLException {
        String basedir = (new File(".")).getAbsolutePath();
        File testDatabase = new File(
                basedir + "./peer/src/test/resources/" + db);
        String path = testDatabase.getPath();
        log.info("DBPath " + path);

        if (data != null) {
            data.setJDBCUrl("jdbc:sqlite:" + path);
        }
    }


    protected void synchronizePeer() {
        controller.execute(
            new SynchronizePeer(),
            new SerializableAsyncCallback<VoidResult>() {
                @Override
                public void onFailure(Throwable caught) {
                    log.log(Level.SEVERE, "On fail ", caught);
                }


                @Override
                public void onSuccess(VoidResult result) {
                    log.info("On success");
                }
            });
    }


    protected void importTag(String tagId) {
        controller.execute(
            new ImportTag(tagId, 0),
            new SerializableAsyncCallback<VoidResult>() {
                @Override
                public void onFailure(Throwable caught) {
                    log.info("On fail " + caught);
                }


                @Override
                public void onSuccess(VoidResult result) {
                    log.info("On success");
                }
            });
    }


    protected void unImportTag(String tagId) {
        controller.execute(
            new UnImportTag(tagId),
            new SerializableAsyncCallback<VoidResult>() {
                @Override
                public void onFailure(Throwable caught) {
                    log.info("On fail " + caught);
                }


                @Override
                public void onSuccess(VoidResult result) {
                    log.info("On success");
                }
            });
    }
}
