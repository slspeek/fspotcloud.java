package fspotcloud.server.model.batch;

import java.io.Serializable;
import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import fspotcloud.server.model.api.Batch;
@PersistenceCapable(detachable="true")
public class BatchDO implements Serializable, Batch{
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

	public BatchDO(String jobName) {
		setJobName(jobName);
		setCtime(new Date());
	}
	
	public BatchDO() {
		setCtime(new Date());
	}

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
	/* (non-Javadoc)
	 * @see fspotcloud.server.model.batch.Batch#getCtime()
	 */
	public Date getCtime() {
		return ctime;
	}

	/* (non-Javadoc)
	 * @see fspotcloud.server.model.batch.Batch#setJobName(java.lang.String)
	 */
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	/* (non-Javadoc)
	 * @see fspotcloud.server.model.batch.Batch#getJobName()
	 */
	public String getJobName() {
		return jobName;
	}

	/* (non-Javadoc)
	 * @see fspotcloud.server.model.batch.Batch#setFtime(java.util.Date)
	 */
	public void setFtime(Date ftime) {
		this.ftime = ftime;
	}

	/* (non-Javadoc)
	 * @see fspotcloud.server.model.batch.Batch#getFtime()
	 */
	public Date getFtime() {
		return ftime;
	}

	
	/* (non-Javadoc)
	 * @see fspotcloud.server.model.batch.Batch#stop()
	 */
	public void stop() {
		this.running = false;
		setFtime(new Date());
	}

	/* (non-Javadoc)
	 * @see fspotcloud.server.model.batch.BatchReadOnly#isRunning()
	 */
	/* (non-Javadoc)
	 * @see fspotcloud.server.model.batch.Batch#isRunning()
	 */
	public boolean isRunning() {
		return running;
	}

	/* (non-Javadoc)
	 * @see fspotcloud.server.model.batch.Batch#incrementInterationCount()
	 */
	public void incrementInterationCount() {
		this.interationCount++;
	}

	/* (non-Javadoc)
	 * @see fspotcloud.server.model.batch.BatchReadOnly#getInterationCount()
	 */
	/* (non-Javadoc)
	 * @see fspotcloud.server.model.batch.Batch#getInterationCount()
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
	/* (non-Javadoc)
	 * @see fspotcloud.server.model.batch.Batch#getKey()
	 */
	public Long getKey() {
		return key;
	}

	/* (non-Javadoc)
	 * @see fspotcloud.server.model.batch.Batch#getResult()
	 */
	public String getResult() {
		return result;
	}

	/* (non-Javadoc)
	 * @see fspotcloud.server.model.batch.Batch#setResult(java.lang.String)
	 */
	public void setResult(String result) {
		this.result = result;
	}

	/* (non-Javadoc)
	 * @see fspotcloud.server.model.batch.Batch#getState()
	 */
	public String getState() {
		return state;
	}

	/* (non-Javadoc)
	 * @see fspotcloud.server.model.batch.Batch#setState(java.lang.String)
	 */
	public void setState(String state) {
		this.state = state;
	}

	
}
