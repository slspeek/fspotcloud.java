package fspotcloud.botdispatch.controller.callback;

public interface ControllerHook {
	void preprocess(long id, byte[] result);
}
