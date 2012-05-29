package com.googlecode.fspotcloud.server.main.handler;

import com.googlecode.fspotcloud.shared.main.AuthenticationAction;
import com.googlecode.fspotcloud.shared.main.AuthenticationResult;
import org.jukito.JukitoRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.junit.Assert.assertFalse;

@RunWith(JukitoRunner.class)
public class AuthenticationHandlerTest  {

    @Inject
    AuthenticationHandler handler;

    @Test
    public void testExecute() throws Exception {
        AuthenticationAction action = new AuthenticationAction("foo", "secret");
        AuthenticationResult result = handler.execute(action, null);
        assertFalse(result.getSuccess());
    }
}
