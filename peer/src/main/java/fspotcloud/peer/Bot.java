package fspotcloud.peer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.google.inject.Inject;

public class Bot {

	private BotWorker botWorker;
	private Pauser pauser;
	private CommandFetcher fetcher;
	
	@Inject
	private Bot(BotWorker botWorker, CommandFetcher fetcher, Pauser pauser) {
		this.botWorker = botWorker;
		this.pauser = pauser;
		this.fetcher = fetcher;
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
				// e.printStackTrace();
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
							.println("Exception during execution, sleeping for 2s");
					e.printStackTrace();
					pauser.pause(2000);
				}

			} else {
				System.out.println("No action at this time, sleeping for 10s");
				pauser.pause(10000);
			}

		}

	}

	private void dispatch(String cmd, Object[] args) {
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
