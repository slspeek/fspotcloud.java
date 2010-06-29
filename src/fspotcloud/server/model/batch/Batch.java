package fspotcloud.server.model.batch;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import com.google.appengine.api.datastore.Key;
@PersistenceCapable
public class Batch {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long key;

	@Persistent
	private String jobName;

	@Persistent
	private boolean running = true;

	@Persistent
	private Date ctime;

	@Persistent
	private Date ftime;

	@Persistent(serialized = "true")
	private Object result;

	@Persistent(serialized = "true")
	private Object[] state;

	@Persistent
	private int interationCount = 1;

	public Batch(String jobName) {
		setJobName(jobName);
		setCtime(new Date());
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

	/**
	 * @param jobName
	 *            the jobName to set
	 */
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	/**
	 * @return the jobName
	 */
	public String getJobName() {
		return jobName;
	}

	/**
	 * @param ftime
	 *            the ftime to set
	 */
	public void setFtime(Date ftime) {
		this.ftime = ftime;
	}

	/**
	 * @return the ftime
	 */
	public Date getFtime() {
		return ftime;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(Object[] state) {
		this.state = state;
	}

	/**
	 * @return the state
	 */
	public Object[] getState() {
		return state;
	}

	/**
	 * @param result
	 *            the result to set
	 */
	public void setResult(Object result) {
		this.result = result;
	}

	/**
	 * @return the result
	 */
	public Object getResult() {
		return result;
	}

	/**
	 * @param running
	 *            the running to set
	 */
	public void stop() {
		this.running = false;
		setFtime(new Date());
	}

	/**
	 * @return the running
	 */
	public boolean isRunning() {
		return running;
	}

	/**
	 * increment the iteration count
	 */
	public void incrementInterationCount() {
		this.interationCount++;
	}

	/**
	 * @return the interationCount
	 */
	public int getInterationCount() {
		return interationCount;
	}

	/**
	 * @param key the key to set
	 */
	public void setKey(Long key) {
		this.key = key;
	}

	/**
	 * @return the key
	 */
	public Long getKey() {
		return key;
	}

	
}
