/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.fspotcloud.server.inject;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author steven
 */
public class PropertiesLoader {

    public Properties loadProperties() {
        try {
            Properties p = new Properties();
            ClassLoader l = getClass().getClassLoader();
            //ClassLoader l = Thread.currentThread().getContextClassLoader();
            //ClassLoader l = ClassLoader.getSystemClassLoader();
            final InputStream resourceAsStream = l.getResourceAsStream("properties.properties");
            if (resourceAsStream == null) {
                throw new IOException("properties.properties not found");
            }
            p.load(resourceAsStream);
            Logger.getLogger(PropertiesLoader.class.getName()).info("Properties successfully loaded.");
            return p;
        } catch (IOException ex) {
            Logger.getLogger(PropertiesLoader.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
