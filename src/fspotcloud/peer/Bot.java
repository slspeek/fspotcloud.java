package fspotcloud.peer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;

import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.apache.xmlrpc.client.XmlRpcCommonsTransportFactory;

public class Bot {

	private URL controllerURL;
	private XmlRpcClient controller;
	private BotWorker botWorker;

	public static Bot getInstance() throws Exception {
		String url = System.getProperty("fspotcloud.controller.url");
		URL controllerURL = new URL(url);
		return new Bot(controllerURL);
	}

	private Bot(URL controllerURL) {
		this.controllerURL = controllerURL;
		initController();
		this.botWorker = new BotWorker(this.controller);
	}

	/**
	 * Initializes the connection to the server where the commands are fetched
	 */
	private void initController() {
		// create configuration
		XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
		config.setServerURL(controllerURL);
		config.setEnabledForExtensions(true);
		config.setConnectionTimeout(60 * 1000);
		config.setReplyTimeout(60 * 1000);
		controller = new XmlRpcClient();
		// use Commons HttpClient as transport
		controller.setTransportFactory(new XmlRpcCommonsTransportFactory(
				controller));
		// set configuration
		controller.setConfig(config);
	}

	public static void main(String[] args) throws Exception {
		Bot bot = Bot.getInstance();
		bot.serveForever();
	}

	private void serveForever() {
		Object[] commandReturn;
		Object[] args;
		String cmd;
		while (true) {
			try {
				commandReturn = (Object[]) controller.execute(
						"Controller.getCommand", new Object[] {});
			} catch (Exception e) {
				System.out
						.println("Not able to get new command, sleeping for 5s ");
				e.printStackTrace();
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e1) {
				}
				continue;
			}
			if (commandReturn.length > 0) {
				cmd = (String) commandReturn[0];
				args = (Object[]) commandReturn[1];
				dispatch(cmd, args);
			} else {
				System.out.println("No action at this time, sleeping for 10s");
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e1) {
				}
			}

		}

	}

	private void dispatch(String cmd, Object[] args) {
		Method method = findMethod(cmd, BotWorker.class);
		try {
			method.invoke(botWorker, args);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
