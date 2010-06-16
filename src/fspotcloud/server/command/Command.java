package fspotcloud.server.command;

import java.util.Date;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class Command {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;

	@Persistent
	private String cmd;

	@Persistent
	private List<String> args;

	@Persistent
	private String argsString;

	@Persistent
	private Date ctime;

	/**
	 * @param key
	 *            the key to set
	 */
	public void setKey(Key key) {
		this.key = key;
	}

	/**
	 * @return the key
	 */
	public Key getKey() {
		return key;
	}

	/**
	 * @param cmd
	 *            the cmd to set
	 */
	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	/**
	 * @return the cmd
	 */
	public String getCmd() {
		return cmd;
	}

	/**
	 * @param args
	 *            the args to set
	 */
	public void setArgs(List<String> args) {
		this.args = args;
		setArgsString(String.valueOf(args));
	}

	/**
	 * @return the args
	 */
	public List<String> getArgs() {
		return args;
	}

	/**
	 * @param argsString
	 *            the argsString to set
	 */
	private void setArgsString(String argsString) {
		this.argsString = argsString;
	}

	/**
	 * @return the argsString
	 */
	private String getArgsString() {
		return argsString;
	}

	/**
	 * @param ctime
	 *            the ctime to set
	 */
	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	/**
	 * @return the ctime
	 */
	public Date getCtime() {
		return ctime;
	}

}
