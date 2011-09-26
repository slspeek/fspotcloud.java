package fspotcloud.botdispatch.model.command;

import java.util.Date;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

import fspotcloud.botdispatch.model.api.Command;

@PersistenceCapable
public class CommandDO implements Command {
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

	public CommandDO() {
		setCtime(new Date());
	}
	/**
	 * @param key
	 *            the key to set
	 */
	public void setKey(Key key) {
		this.key = key;
	}

	/* (non-Javadoc)
	 * @see fspotcloud.botdispatch.model.command.Command#getKey()
	 */
	public Key getKey() {
		return key;
	}

	/* (non-Javadoc)
	 * @see fspotcloud.botdispatch.model.command.Command#setCmd(java.lang.String)
	 */
	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	/* (non-Javadoc)
	 * @see fspotcloud.botdispatch.model.command.Command#getCmd()
	 */
	public String getCmd() {
		return cmd;
	}

	/* (non-Javadoc)
	 * @see fspotcloud.botdispatch.model.command.Command#setArgs(java.util.List)
	 */
	public void setArgs(List<String> args) {
		this.args = args;
		setArgsString(String.valueOf(args));
	}

	/* (non-Javadoc)
	 * @see fspotcloud.botdispatch.model.command.Command#getArgs()
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

	/* (non-Javadoc)
	 * @see fspotcloud.botdispatch.model.command.Command#getCtime()
	 */
	public Date getCtime() {
		return ctime;
	}

}
