package com.googlecode.fspotcloud.peer.handlers;

import java.net.URL;

import junit.framework.TestCase;
import net.customware.gwt.dispatch.shared.DispatchException;

import com.google.common.collect.ImmutableList;

import com.googlecode.fspotcloud.peer.db.Data;
import com.googlecode.fspotcloud.shared.peer.rpc.actions.GetPhotoData;
import com.googlecode.fspotcloud.shared.peer.rpc.actions.ImageSpecs;
import com.googlecode.fspotcloud.shared.peer.rpc.actions.PhotoData;
import com.googlecode.fspotcloud.shared.peer.rpc.actions.PhotoDataResult;

public class GetPhotoDataHandlerTest extends TestCase {

    private Data data;
    private GetPhotoDataHandler handler;
    private GetPhotoData action;

    protected void setUp() throws Exception {
        super.setUp();
        System.setProperty("photo.dir.original", "//home/steven/Photos");
        System.setProperty("photo.dir.override", "" + System.getProperty("user.dir") + "/src/test/resources/Photos");
        URL testDatabase = ClassLoader.getSystemResource("photos.db");
        String path = testDatabase.getPath();
        data = new Data("jdbc:sqlite:" + path);
        handler = new GetPhotoDataHandler(data);
        action = new GetPhotoData(new ImageSpecs(1, 1, 1, 1), ImmutableList.of("3"));
    }

    public void testExecute() throws DispatchException {
        PhotoDataResult result = handler.execute(action, null);
        PhotoData photoData = result.getPhotoDataList().get(0);
        assertEquals("3", photoData.getPhotoId());
    }
}
