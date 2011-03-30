package fspotcloud.peer;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public class StopListener extends Thread {
	
	final int stopPort;
	private ServerSocket server;
	@Inject
	public StopListener(@Named("stop port") int port) {
		this.stopPort = port;
	}
	
	public void run() {
		try{
		    server = new ServerSocket(stopPort);
		    server.accept();
		  } catch (IOException e) {
		    System.out.println("Could not listen on port " + stopPort);
		    System.exit(-1);
		  }
		 System.exit(0);
	}

}
