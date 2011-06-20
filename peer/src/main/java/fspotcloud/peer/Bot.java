package fspotcloud.peer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public class Bot {

	private BotWorker botWorker;
	private Pauser pauser;
	private CommandFetcher fetcher;
	private int pause;
	
	@Inject
	private Bot(BotWorker botWorker, CommandFetcher fetcher, Pauser pauser, @Named("pause") int pause) {
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
				System.out
						.println("Not able to get new command, sleeping for 5s ");
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
					System.out
							.println("Exception during execution of " + cmd + ", sleeping for 2s");
					e.printStackTrace();
					pauser.pause(2000);
				}

			} else {
				System.out.println("No action at this time, sleeping for " + (pause/1000) + "s");
				pauser.pause(pause);
			}

		}

	}

	private void dispatch(String cmd, Object[] args) {
		List list = Arrays.asList(args);
		System.out.println("Running " + cmd + "("+ String.valueOf(list) +")");
		Method method = findMethod(cmd, BotWorker.class);
		try {
			method.invoke(botWorker, args);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	private Method findMethod(String name, Class c) {
		Method[] all = c.getDeclaredMethods();
		for (Method m : all) {
			// System.out.println(m.getName());
			if (m.getName().equals(name)) {
				return m;
			}
		}
		return null;
	}

}
