package fspotcloud.server.model;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;

import com.google.inject.Provider;

public class PersistenceManagerProvider implements Provider<PersistenceManager> {
	
	private static final PersistenceManagerFactory pmfInstance = JDOHelper
	.getPersistenceManagerFactory("transactions-optional");

	public PersistenceManagerProvider() {
	
	}
	@Override
	public PersistenceManager get() {
		// TODO Auto-generated method stub
		return pmfInstance.getPersistenceManager();
	}

}