/*
 * Copyright 2010-2012 Steven L. Speek.
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 *
 */
package com.googlecode.fspotcloud.test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.googlecode.fspotcloud.server.inject.J2eeTotalModule;
import com.googlecode.fspotcloud.server.inject.TestServerGuiceServletConfig;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.webapp.WebAppContext;


public class FscServer {
    private Server server;

    public FscServer(int port) {
        server = new Server(port);

        //ServletContextHandler handler = new ServletContextHandler(myServer, "/context");
        final WebAppContext bb = new WebAppContext();
        bb.setServer(server);
        bb.setContextPath("/");
        bb.setWar("/home/steven/fspotcloud/j2ee-server-test/build/exploded");
        server.addHandler(bb);
    }

    protected Module getFscModule() {
        return new J2eeTotalModule(10, "VERY_GRADLE", "slspeek@gmail.com");
    }

    public Injector start() {
        try {
            //Injector result = Guice.createInjector(getFscModule());
            //System.out.println(" " + TestServerGuiceServletConfig.INJECTOR);
            server.start();

            return TestServerGuiceServletConfig.INJECTOR;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws Exception {
        FscServer server1 = new FscServer(8080);
        server1.start();
        Thread.sleep(10000);
        server1.server.stop();
    }
}
