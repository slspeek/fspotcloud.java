package fspotcloud.server.admin.integration;


import java.sql.SQLException;

import net.customware.gwt.dispatch.shared.DispatchException;
import fspotcloud.server.model.api.PeerDatabase;
import fspotcloud.shared.dashboard.actions.TagDeleteAll;
import fspotcloud.shared.peer.rpc.actions.GetPeerMetaData;
import fspotcloud.shared.peer.rpc.actions.PeerMetaDataResult;

public class PeerServerIntegrationTest extends PeerServerIntegrationBase {

	public void testImportAllTags() throws Exception {
		setPeerTestDatabase("photos.db");
		PeerDatabase peer = peers.get();
		peers.save(peer);
		synchronizePeer();
		verifyAllTagsAreLoaded();
	}

	private void verifyAllTagsAreLoaded() {
		assertTagsLoaded("1","2", "3", "4", "5");
	}

	public void testGetPeerMetaData() throws DispatchException, SQLException {
		setPeerTestDatabase("photos.db");
		PeerMetaDataResult result = dispatch.execute(new GetPeerMetaData());
		assertEquals(28, result.getPhotoCount());
		assertEquals(5, result.getTagCount());
	}

	public void testImportGlass() throws Exception {
		setPeerTestDatabase("photos.db");
		testImportAllTags();
		importTag("5");
		photos.getById("3");
	}

	public void testImportFurniture() throws Exception {
		testImportAllTags();
		importTag("1");
		verfiyFurnitureIsLoaded();
	}

	public void testImportFurnitureInTwoPhases() throws Exception {
		testImportAllTags();
		setPeerTestDatabase("photos_smaller.db");
		importTag("1");
		verfiyFurnitureFirstPhaseIsLoaded();
		setPeerTestDatabase("photos.db");
		synchronizePeer();
		verfiyFurnitureIsLoaded();
	}
	
	public void testUImportFurniture() throws Exception {
		testImportAllTags();
		setPeerTestDatabase("photos_smaller.db");
		importTag("1");
		verfiyFurnitureFirstPhaseIsLoaded();
		unImportTag("1");
		verifiyFurnitureFirstPhaseWasRemoved();
	}

	private void verifiyFurnitureFirstPhaseWasRemoved() {
		assertPhotosRemoved("12","13", "4", "5", "15");
	}


	public void testImportFurnitureInThreePhases() throws Exception {
		testImportAllTags();
		setPeerTestDatabase("photos_smaller.db");
		importTag("1");
		verfiyFurnitureFirstPhaseIsLoaded();
		setPeerTestDatabase("photos.db");
		synchronizePeer();
		verfiyFurnitureIsLoaded();
		setPeerTestDatabase("photos_smaller.db");
		synchronizePeer();
		verifyImagesWereRemoved();
		verfiyFurnitureFirstPhaseIsLoaded();
	}
	

	private void verifyImagesWereRemoved() {
		assertPhotosRemoved("7","6", "16", "14");
	}

	public void testRemovingOfTags() throws Exception {
		testImportAllTags();
		dispatch.execute(new TagDeleteAll());
		assertTagsRemoved("1", "2", "3", "4", "5");
	}
	
	private void verfiyFurnitureFirstPhaseIsLoaded() {
		assertPhotosLoaded("12","13", "4", "5", "15");
	}

	private void verfiyFurnitureIsLoaded() {
		assertPhotosLoaded("12","13", "4", "5", "15");
		assertPhotosLoaded("7","6", "16", "14");
	}
	
	public void testOverlapWithTagRemoval() throws Exception {
		testImportAllTags();
		importTag("2");
		assertComputersIsLoaded();
		importTag("4");
		assertPCLoaded();
		unImportTag("2");
		assertPCLoaded();
		unImportTag("4");
		assertPhotosRemoved("17", "18", "19","20","21","22","23","24","25","26","27","28","9","11");
	}

	private void assertPCLoaded() {
		assertPhotosLoaded("17", "18", "19","20","21","22","23","24","25","26","27","28","9","11");
	}
	
	private void assertComputersIsLoaded() {
		assertPhotosLoaded("17", "18", "19","20","21","22","23","24","25","26","27","28");
	}
}


