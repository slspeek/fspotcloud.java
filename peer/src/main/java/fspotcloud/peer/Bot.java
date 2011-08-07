package fspotcloud.peer;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.inject.Inject;
import com.google.inject.name.Named;



public class Bot {

	final static private Logger log = Logger.getLogger(Bot.class.getName());
	private CommandWorkerFactory workerFactory;
	private Pauser pauser;
	private CommandFetcher fetcher;
	private int pause;

	@Inject
	private Bot(CommandWorkerFactory workerFactory, CommandFetcher fetcher, Pauser pauser,
			@Named("pause") int pause) {
		this.workerFactory = workerFactory;
		this.pauser = pauser;
		this.fetcher = fetcher;
		this.pause = pause;
	}

	public void serveForever() {
		Object[] commandReturn;
		Object[] args;
		String cmd;
		while (true) {
			try {
				commandReturn = fetcher.getCommand();
			} catch (Exception e) {
				log.log(Level.SEVERE, "Exception during fetching of new command",e);
				log.info("Not able to get new command, sleeping for 5s ");
				pauser.pause(5000);
				continue;
			}
			if (commandReturn.length > 0) {
				cmd = (String) commandReturn[0];
				args = (Object[]) commandReturn[1];
				try {
					dispatch(cmd, args);
				} catch (Exception e) {
					log.log(Level.SEVERE, "Exception during execution of " + cmd,e);
					log.info("Will sleep for 2 seconds");
					pauser.pause(2000);
				}

			} else {
				log.info("No action at this time, sleeping for "
						+ (pause / 1000) + "s");
				pauser.pause(pause);
			}

		}

	}

	private void dispatch(String cmd, Object[] args) {
		List list = Arrays.asList(args);
		String whatWeRun = cmd + "(" + String.valueOf(list) + ")";
		log.info("Running " + whatWeRun);
		CommandWorker worker = workerFactory.get(cmd, args);
		worker.run();
	}


}
