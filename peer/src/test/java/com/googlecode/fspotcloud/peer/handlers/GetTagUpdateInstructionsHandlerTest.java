package com.googlecode.fspotcloud.peer.handlers;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;

import junit.framework.TestCase;
import net.customware.gwt.dispatch.shared.DispatchException;
import com.googlecode.fspotcloud.peer.db.Data;
import com.googlecode.fspotcloud.shared.peer.rpc.actions.GetTagUpdateInstructionsAction;
import com.googlecode.fspotcloud.shared.peer.rpc.actions.PhotoRemovedFromTag;
import com.googlecode.fspotcloud.shared.peer.rpc.actions.PhotoUpdate;
import com.googlecode.fspotcloud.shared.peer.rpc.actions.TagUpdateInstructionsResult;
import com.googlecode.fspotcloud.shared.photo.PhotoInfo;

public class GetTagUpdateInstructionsHandlerTest extends TestCase {

	/** Tag "5" Glass contains only image "3" */
	
	private Data data;
	GetTagUpdateInstructionsHandler handler;
	GetTagUpdateInstructionsAction action;

	protected void setUp() throws Exception {
		super.setUp();
		URL testDatabase = ClassLoader.getSystemResource("photos.db");
		String path = testDatabase.getPath();
		data = new Data("jdbc:sqlite:" +path);
		handler = new GetTagUpdateInstructionsHandler(data);
		action = new GetTagUpdateInstructionsAction("5", new TreeSet<PhotoInfo>());
	}


	public void testExecute() throws DispatchException {
		
		TagUpdateInstructionsResult result = handler.execute(action, null);
		List<PhotoUpdate> updates = result.getToBoUpdated();
		assertEquals(1, updates.size());
		assertEquals("3",result.getToBoUpdated().get(0).getPhotoId());
		
	}
	
	public void testExecuteAllReadyImported() throws DispatchException {
		PhotoInfo info = new PhotoInfo("3","",new Date(0));
		TreeSet<PhotoInfo> set = new TreeSet<PhotoInfo>();
		set.add(info);
		action = new GetTagUpdateInstructionsAction("5", set);
		TagUpdateInstructionsResult result = handler.execute(action, null);
		List<PhotoUpdate> updates = result.getToBoUpdated();
		assertEquals(0, updates.size());
	}
	
	public void testExecuteFurniture() throws DispatchException {
		PhotoInfo info = new PhotoInfo("3","",new Date(0));
		TreeSet<PhotoInfo> set = new TreeSet<PhotoInfo>();
		set.add(info);
		action = new GetTagUpdateInstructionsAction("1", set);
		TagUpdateInstructionsResult result = handler.execute(action, null);
		List<PhotoUpdate> updates = result.getToBoUpdated();
		assertEquals(9, updates.size());
	}
	
	public void testExecuteRemoved() throws DispatchException {
		PhotoInfo info = new PhotoInfo("7","",new Date(0));
		TreeSet<PhotoInfo> set = new TreeSet<PhotoInfo>();
		set.add(info);
		action = new GetTagUpdateInstructionsAction("5", set);
		TagUpdateInstructionsResult result = handler.execute(action, null);
		
		List<PhotoRemovedFromTag> toBoRemovedFromTag = result.getToBoRemovedFromTag();
		assertEquals(1, toBoRemovedFromTag.size());
	}

}
