package fspotcloud.server.model.batch;

import java.io.Serializable;
import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
@PersistenceCapable(detachable="true")
public class Batch implements Serializable{
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

	@Persistent
	private String result;

	@Persistent
	private String state;

	@Persistent
	private int interationCount = 0;

	public Batch(String jobName) {
		setJobName(jobName);
		setCtime(new Date());
	}
	
	/*public Batch() {
		setCtime(new Date());
	}*/

	/**
	 * @param ctime
	 *            the ctime to set
	 */
	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	/* (non-Javadoc)
	 * @see fspotcloud.server.model.batch.BatchReadOnly#getCtime()
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
	 * @param running
	 *            the running to set
	 */
	public void stop() {
		this.running = false;
		setFtime(new Date());
	}

	/* (non-Javadoc)
	 * @see fspotcloud.server.model.batch.BatchReadOnly#isRunning()
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

	/* (non-Javadoc)
	 * @see fspotcloud.server.model.batch.BatchReadOnly#getInterationCount()
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

	/* (non-Javadoc)
	 * @see fspotcloud.server.model.batch.BatchReadOnly#getKey()
	 */
	public Long getKey() {
		return key;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	
}
