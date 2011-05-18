package fspotcloud.server.model.api;

import java.util.Date;

public interface Batch {

	/* (non-Javadoc)
	 * @see fspotcloud.server.model.batch.BatchReadOnly#getCtime()
	 */
	public abstract Date getCtime();

	/**
	 * @param jobName
	 *            the jobName to set
	 */
	public abstract void setJobName(String jobName);

	/**
	 * @return the jobName
	 */
	public abstract String getJobName();

	/**
	 * @param ftime
	 *            the ftime to set
	 */
	public abstract void setFtime(Date ftime);

	/**
	 * @return the ftime
	 */
	public abstract Date getFtime();

	/**
	 * @param running
	 *            the running to set
	 */
	public abstract void stop();

	/* (non-Javadoc)
	 * @see fspotcloud.server.model.batch.BatchReadOnly#isRunning()
	 */
	public abstract boolean isRunning();

	/**
	 * increment the iteration count
	 */
	public abstract void incrementInterationCount();

	/* (non-Javadoc)
	 * @see fspotcloud.server.model.batch.BatchReadOnly#getInterationCount()
	 */
	public abstract int getInterationCount();

	/* (non-Javadoc)
	 * @see fspotcloud.server.model.batch.BatchReadOnly#getKey()
	 */
	public abstract Long getKey();

	public abstract String getResult();

	public abstract void setResult(String result);

	public abstract String getState();

	public abstract void setState(String state);

}