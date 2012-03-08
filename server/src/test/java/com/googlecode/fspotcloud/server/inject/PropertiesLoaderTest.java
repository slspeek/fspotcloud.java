/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.fspotcloud.server.inject;

import com.googlecode.fspotcloud.server.inject.PropertiesLoader;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 * @author steven
 */
public class PropertiesLoaderTest  {
    
    
    /**
     * Test of loadProperties method, of class PropertiesLoader.
     */
    @Test
    public void testLoadProperties() {
        PropertiesLoader l = new PropertiesLoader();
        l.loadProperties();
        Assert.assertNotNull(System.getProperty("user.home"));
    }
}
