/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.fspotcloud.server.control.hook;

import com.googlecode.fspotcloud.model.jpa.peerdatabase.PeerDatabaseEntity;
import com.googlecode.fspotcloud.server.model.api.PeerDatabase;
import com.googlecode.fspotcloud.server.model.api.PeerDatabases;
import java.util.Date;
import javax.inject.Inject;
import org.jukito.JukitoRunner;
import org.junit.*;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.*;
/**
 *
 * @author steven
 */
@RunWith(JukitoRunner.class)
public class TimeLoggingControllerHookTest {
    
    @Inject private TimeLoggingControllerHook instance;
    private PeerDatabase peer = new PeerDatabaseEntity();
    
    
    @Test
    public void testPreprocess(PeerDatabases peers) {
        Date now = new Date();
        when(peers.get()).thenReturn(peer);
        long id = 0L;
        byte[] result_2 = null;
        instance.preprocess(id, result_2);
        verify(peers).save(peer);
        assertTrue(peer.getPeerLastContact().compareTo(now) != -1);
    }
}
