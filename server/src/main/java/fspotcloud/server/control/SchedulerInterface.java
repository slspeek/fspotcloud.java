package fspotcloud.server.control;

import java.util.List;

public interface SchedulerInterface {

	public abstract boolean schedule(String cmd, List<String> args);

}