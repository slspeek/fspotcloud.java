package com.googlecode.fspotcloud.peer.handlers;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.server.SimpleActionHandler;
import net.customware.gwt.dispatch.shared.ActionException;
import net.customware.gwt.dispatch.shared.DispatchException;

import com.google.inject.Inject;

import com.googlecode.fspotcloud.peer.db.Data;
import com.googlecode.fspotcloud.shared.peer.rpc.actions.GetTagData;
import com.googlecode.fspotcloud.shared.peer.rpc.actions.TagDataResult;

public class GetTagDataHandler extends
		SimpleActionHandler<GetTagData, TagDataResult> {

	final private Data data;

	@Inject
	public GetTagDataHandler(Data data) {
		super();
		this.data = data;
	}

	@Override
	public TagDataResult execute(GetTagData action, ExecutionContext context)
			throws DispatchException {
		TagDataResult result;
		try {
			result = new TagDataResult(data.getTagData(action.getOffset(),action.getCount()));
		} catch (Exception e) {
			throw new ActionException(e);
		}
		return result;
	}

}
