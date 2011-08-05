package fspotcloud.peer;

public interface DataFetcher {
	Object getData(String dataMethod, Object[] args) throws MethodNotFoundException;
}
