package fspotcloud.taskqueuedispatch;

import java.util.List;

import javax.inject.Inject;

import fspotcloud.taskqueuedispatch.SerializableAsyncCallback;

public class TestCallback implements SerializableAsyncCallback<TestResult> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 232324L;
	
	@Inject
	transient List results;

	@Override
	public void onFailure(Throwable caught) {

	}

	@Override
	public void onSuccess(TestResult result) {
		String message = (String)result.getMessage(); 
		results.add(message);
	}

}