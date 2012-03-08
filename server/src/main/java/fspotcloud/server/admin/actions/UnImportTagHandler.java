package fspotcloud.server.admin.actions;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.server.SimpleActionHandler;
import net.customware.gwt.dispatch.shared.ActionException;
import net.customware.gwt.dispatch.shared.DispatchException;

import com.google.inject.Inject;

import fspotcloud.server.control.task.actions.intern.DeletePhotos;
import fspotcloud.server.model.api.PeerDatabase;
import fspotcloud.server.model.api.PeerDatabases;
import fspotcloud.server.model.api.Tag;
import fspotcloud.server.model.api.Tags;
import fspotcloud.shared.dashboard.actions.UnImportTag;
import fspotcloud.shared.dashboard.actions.VoidResult;
import fspotcloud.shared.photo.PhotoInfo;
import com.googlecode.taskqueuedispatch.TaskQueueDispatch;
import fspotcloud.user.AdminPermission;

public class UnImportTagHandler extends SimpleActionHandler<UnImportTag, VoidResult> {

    private static final Logger log = Logger.getLogger(UnImportTagHandler.class.getName());
    final private Tags tagManager;
    final private TaskQueueDispatch dispatchAsync;
    final private PeerDatabases peerDatabases;
    private final AdminPermission adminPermission;

    @Inject
    public UnImportTagHandler(Tags tagManager, TaskQueueDispatch dispatchAsync,
            PeerDatabases peerDatabases, AdminPermission adminPermission) {
        super();
        this.tagManager = tagManager;
        this.dispatchAsync = dispatchAsync;
        this.peerDatabases = peerDatabases;
        this.adminPermission = adminPermission;

    }

    @Override
    public VoidResult execute(UnImportTag action, ExecutionContext context)
            throws DispatchException {
        log.info("Executing: " + action.getTagId());
        adminPermission.chechAdminPermission();
        try {
            String tagId = action.getTagId();
            Tag tag = tagManager.find(tagId);
            if (tag.isImportIssued()) {
                tag.setImportIssued(false);
                tagManager.save(tag);
            }
            List<PhotoInfo> infoList = new ArrayList<PhotoInfo>();
            infoList.addAll(tag.getCachedPhotoList());
            dispatchAsync.execute(new DeletePhotos(tag.getId(), infoList));
            clearTreeCache();

        } catch (Exception e) {
            throw new ActionException(e);
        }
        return new VoidResult();
    }

    private void clearTreeCache() {
        PeerDatabase peer = peerDatabases.get();
        if (peer.getCachedTagTree() != null) {
            peer.setCachedTagTree(null);
            peerDatabases.save(peer);
        }
    }
}