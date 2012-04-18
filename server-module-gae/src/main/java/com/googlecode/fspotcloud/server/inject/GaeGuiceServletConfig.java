package com.googlecode.fspotcloud.server.inject;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import java.util.Properties;

public class GaeGuiceServletConfig extends GuiceServletContextListener {

    Properties p = (new PropertiesLoader()).loadProperties();

    @Override
    protected Injector getInjector() {
        System.setProperty("java.util.logging.config.file", "logging.properties");
        int maxTicks = Integer.valueOf(p.getProperty("maxTicks", "100"));
        String botSecret = p.getProperty("bot.secret");
        Injector i = Guice.createInjector(new GaeTotalModule(maxTicks, botSecret));
        return i;
    }
}
