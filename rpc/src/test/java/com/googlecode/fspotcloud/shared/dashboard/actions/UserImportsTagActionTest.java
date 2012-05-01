/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.fspotcloud.shared.dashboard.actions;

import junit.framework.TestCase;
import org.apache.commons.lang.SerializationUtils;

/**
 *
 * @author steven
 */
public class UserImportsTagActionTest extends TestCase {
    public static final String TAG_ID = "1";
    
    public UserImportsTagActionTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testGetTagId() {
        UserImportsTagAction instance = new UserImportsTagAction(TAG_ID);
        String expResult = TAG_ID;
        String result = instance.getTagId();
        assertEquals(expResult, result);
    }
    
    public void testSeriazability() {
        UserImportsTagAction instance = new UserImportsTagAction(TAG_ID);
        SerializationUtils.serialize(instance);
    }
}
