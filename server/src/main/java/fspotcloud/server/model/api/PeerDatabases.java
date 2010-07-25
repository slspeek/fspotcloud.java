package fspotcloud.server.model.api;

public interface PeerDatabases {

	public abstract PeerDatabase get();

	public abstract void save(PeerDatabase pd);

}