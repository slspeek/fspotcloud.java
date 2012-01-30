package fspotcloud.server.admin.actions;

import java.util.logging.Logger;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.server.SimpleActionHandler;
import net.customware.gwt.dispatch.shared.ActionException;
import net.customware.gwt.dispatch.shared.DispatchException;

import com.google.inject.Inject;

import fspotcloud.botdispatch.controller.dispatch.ControllerDispatchAsync;
import fspotcloud.server.control.callback.TagUpdateInstructionsCallback;
import fspotcloud.server.model.api.Tag;
import fspotcloud.server.model.api.Tags;
import fspotcloud.shared.dashboard.actions.ImportTag;
import fspotcloud.shared.dashboard.actions.VoidResult;
import fspotcloud.shared.peer.rpc.actions.GetTagUpdateInstructionsAction;
import fspotcloud.user.AdminPermission;

public class ImportTagHandler extends SimpleActionHandler<ImportTag, VoidResult> {

    protected static final Logger log = Logger.getLogger(ImportTagHandler.class.getName());
    final private Tags tagManager;
    final private ControllerDispatchAsync dispatchAsync;
    private final AdminPermission adminPermission;

    @Inject
    public ImportTagHandler(Tags tagManager, ControllerDispatchAsync dispatchAsync, AdminPermission adminPermission) {
        this.tagManager = tagManager;
        this.dispatchAsync = dispatchAsync;
        this.adminPermission = adminPermission;
    }

    @Override
    public VoidResult execute(ImportTag action, ExecutionContext context)
            throws DispatchException {
        adminPermission.chechAdminPermission();
        try {
            String tagId = action.getTagId();
            Tag tag = tagManager.getById(tagId);
            if (!tag.isImportIssued()) {
                tag.setImportIssued(true);
                tagManager.save(tag);
            }

            GetTagUpdateInstructionsAction peerAction = new GetTagUpdateInstructionsAction(
                    tagId, tag.getCachedPhotoList());
            TagUpdateInstructionsCallback callback = new TagUpdateInstructionsCallback(
                    tagId, null);
            log.info("before execute for tag: " + action.getTagId());
            dispatchAsync.execute(peerAction, callback);

        } catch (Exception e) {
            throw new ActionException(e);
        }
        return new VoidResult();
    }
}