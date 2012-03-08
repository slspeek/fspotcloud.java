package fspotcloud.server.control.callback;

import java.io.Serializable;
import java.util.logging.Logger;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

import fspotcloud.server.control.task.actions.intern.TagImportAction;
import fspotcloud.server.model.api.PeerDatabase;
import fspotcloud.server.model.api.PeerDatabases;
import fspotcloud.shared.dashboard.actions.VoidResult;
import fspotcloud.shared.peer.rpc.actions.PeerMetaDataResult;
import com.googlecode.taskqueuedispatch.NullCallback;
import com.googlecode.taskqueuedispatch.TaskQueueDispatch;

public class PeerMetaDataCallback implements AsyncCallback<PeerMetaDataResult>, Serializable {
final static private Logger log = Logger.getLogger(PeerMetaDataCallback.class.getName());
	private static final long serialVersionUID = 1851403859917750767L;
	@Inject
	transient private PeerDatabases defaultPeer;
	@Inject
	transient private TaskQueueDispatch  dispatchAsync;
	
	public PeerMetaDataCallback(PeerDatabases defaultPeer,
			TaskQueueDispatch dispatchAsync) {
		super();
		this.defaultPeer = defaultPeer;
		this.dispatchAsync = dispatchAsync;
	}

	@Override
	public void onSuccess(PeerMetaDataResult result) {
		log.info("On success: " + result);
		int count = result.getPhotoCount();
		int tagCount = result.getTagCount();
		PeerDatabase p = defaultPeer.get();
		dispatchAsync.execute(new TagImportAction(0, tagCount), new NullCallback<VoidResult>());
		p.setPeerPhotoCount(count);
		p.setTagCount(tagCount);
		defaultPeer.save(p);
	}
	
	@Override
	public void onFailure(Throwable caught) {

	}



}
