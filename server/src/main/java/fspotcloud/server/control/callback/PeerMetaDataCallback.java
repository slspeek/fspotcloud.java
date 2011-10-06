package fspotcloud.server.control.callback;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

import fspotcloud.server.control.task.DataScheduler;
import fspotcloud.server.control.task.DataSchedulerFactory;
import fspotcloud.server.model.api.PeerDatabase;
import fspotcloud.server.model.api.PeerDatabases;
import fspotcloud.shared.peer.rpc.actions.PeerMetaDataResult;

public class PeerMetaDataCallback implements AsyncCallback<PeerMetaDataResult>, Serializable {

	private static final long serialVersionUID = 1851403859917750767L;
	@Inject
	transient private PeerDatabases defaultPeer;
	@Inject
	transient private DataSchedulerFactory dataSchedulerFactory;

	public PeerMetaDataCallback(PeerDatabases defaultPeer,
			DataSchedulerFactory dataSchedulerFactory) {
		super();
		this.defaultPeer = defaultPeer;
		this.dataSchedulerFactory = dataSchedulerFactory;
	}

	@Override
	public void onSuccess(PeerMetaDataResult result) {
		int count = result.getPhotoCount();
		int tagCount = result.getTagCount();
		PeerDatabase p = defaultPeer.get();
		int previousCount = p.getPeerPhotoCount();
		DataScheduler photoScheduler = dataSchedulerFactory.get("Photo");
		photoScheduler.scheduleDataImport(previousCount,count - previousCount);
		DataScheduler tagScheduler = dataSchedulerFactory.get("Tag");
		tagScheduler.scheduleDataImport(0, tagCount);
		p.setPeerPhotoCount(count);
		p.setTagCount(tagCount);
		defaultPeer.save(p);
	}
	
	@Override
	public void onFailure(Throwable caught) {

	}



}
