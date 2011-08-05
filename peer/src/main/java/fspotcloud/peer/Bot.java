package fspotcloud.peer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.inject.Inject;
import com.google.inject.name.Named;



public class Bot {

	final static private Logger log = Logger.getLogger(Bot.class.getName());
	private BotWorker botWorker;
	private Pauser pauser;
	private CommandFetcher fetcher;
	private int pause;

	@Inject
	private Bot(BotWorker botWorker, CommandFetcher fetcher, Pauser pauser,
			@Named("pause") int pause) {
		this.botWorker = botWorker;
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
				log.info("Not able to get new command, sleeping for 5s ");
				e.printStackTrace();
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
		Method method = findMethod(cmd, BotWorker.class);
		try {
			method.invoke(botWorker, args);
		} catch (IllegalArgumentException e) {
			log.log(Level.SEVERE, "Illegal Argument for: " + whatWeRun, e);
		} catch (IllegalAccessException e) {
			log.log(Level.SEVERE, "Illegal Access for: " + whatWeRun, e);
		} catch (InvocationTargetException e) {
			log.log(Level.SEVERE, "Invocation Target for: " + whatWeRun, e);
		}
	}

	private Method findMethod(String name, Class c) {
		Method[] all = c.getDeclaredMethods();
		for (Method m : all) {
			if (m.getName().equals(name)) {
				return m;
			}
		}
		return null;
	}

}
