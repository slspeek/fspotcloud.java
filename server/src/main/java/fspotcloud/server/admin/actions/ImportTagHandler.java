package fspotcloud.server.admin.actions;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.server.SimpleActionHandler;
import net.customware.gwt.dispatch.shared.ActionException;
import net.customware.gwt.dispatch.shared.DispatchException;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import fspotcloud.server.control.task.photoimport.PhotoImportScheduler;
import fspotcloud.server.model.api.Tag;
import fspotcloud.server.model.api.Tags;
import fspotcloud.shared.dashboard.actions.ImportTag;
import fspotcloud.shared.dashboard.actions.VoidResult;

public class ImportTagHandler extends
		SimpleActionHandler<ImportTag, VoidResult> {

	final private Tags tagManager;
	final private PhotoImportScheduler scheduler;

	@Inject
	public ImportTagHandler(Tags tagManager,
			@Named("default") PhotoImportScheduler scheduler) {
		super();
		this.tagManager = tagManager;
		this.scheduler = scheduler;
	}

	@Override
	public VoidResult execute(ImportTag action, ExecutionContext context)
			throws DispatchException {
		try {
			String tagId = action.getTagId();
			Tag tag = tagManager.getById(tagId);
			tag.setImportIssued(true);
			tagManager.save(tag);
			scheduler.schedulePhotoImport(tagId, "", 0, tag.getCount());
		} catch (Exception e) {
			throw new ActionException(e);
		}
		return new VoidResult();
	}
}