package fspotcloud.botdispatch.model.api;

import java.util.Date;
import java.util.List;

import com.google.appengine.api.datastore.Key;

public interface Command {

	/**
	 * @return the key
	 */
	public abstract Key getKey();

	/**
	 * @param cmd
	 *            the cmd to set
	 */
	public abstract void setCmd(String cmd);

	/**
	 * @return the cmd
	 */
	public abstract String getCmd();

	/**
	 * @param args
	 *            the args to set
	 */
	public abstract void setArgs(List<String> args);

	/**
	 * @return the args
	 */
	public abstract List<String> getArgs();

	/**
	 * @return the ctime
	 */
	public abstract Date getCtime();

}