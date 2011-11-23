package fspotcloud.taskqueuedispatch;


final public class NullCallback<T> implements
		SerializableAsyncCallback<T> {
	
	private static final long serialVersionUID = -1088918879456763579L;

	@Override
	public void onFailure(Throwable caught) {
		
	}

	@Override
	public void onSuccess(T result) {
		// TODO Auto-generated method stub
		
	}
}