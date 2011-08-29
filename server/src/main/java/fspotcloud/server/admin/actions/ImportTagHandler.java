package fspotcloud.server.admin.actions;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.server.SimpleActionHandler;
import net.customware.gwt.dispatch.shared.ActionException;
import net.customware.gwt.dispatch.shared.DispatchException;

import com.google.inject.Inject;

import fspotcloud.server.model.api.PeerDatabase;
import fspotcloud.server.model.api.PeerDatabases;
import fspotcloud.server.model.api.Tag;
import fspotcloud.server.model.api.Tags;
import fspotcloud.shared.dashboard.actions.ImportTag;
import fspotcloud.shared.dashboard.actions.VoidResult;

public class ImportTagHandler extends
		SimpleActionHandler<ImportTag, VoidResult> {

	final private Tags tagManager;
	final private PeerDatabases defaultPeer;

	@Inject
	public ImportTagHandler(Tags tagManager, PeerDatabases defaultPeer) {
		super();
		this.tagManager = tagManager;
		this.defaultPeer = defaultPeer;
	}

	@Override
	public VoidResult execute(ImportTag action, ExecutionContext context)
			throws DispatchException {
		try {
			String tagId = action.getTagId();
			Tag tag = tagManager.getById(tagId);
			tag.setImportIssued(true);
			tagManager.save(tag);
			PeerDatabase pd = defaultPeer.get();
			pd.getCachedImportedTags().add(tagId);
			defaultPeer.save(pd);
		} catch (Exception e) {
			throw new ActionException(e);
		}
		return new VoidResult();
	}
}