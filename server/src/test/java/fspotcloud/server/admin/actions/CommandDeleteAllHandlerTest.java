/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fspotcloud.server.admin.actions;

import fspotcloud.botdispatch.model.api.Commands;
import fspotcloud.shared.dashboard.actions.CommandDeleteAll;
import fspotcloud.user.UserService;
import javax.inject.Provider;
import net.customware.gwt.dispatch.shared.ActionException;
import net.customware.gwt.dispatch.shared.DispatchException;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.mockito.Mockito.*;

/**
 *
 * @author steven
 */
public class CommandDeleteAllHandlerTest {

    @Mock
    Commands commandManager;
    @Mock
    UserService userService;
    CommandDeleteAllHandler handler;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        Provider<UserService> userServiceProvider = new Provider<UserService>() {

            @Override
            public UserService get() {
                return userService;
            }
        };
        handler = new CommandDeleteAllHandler(commandManager, userServiceProvider);
    }

    /**
     * Test of execute method, of class CommandDeleteAllHandler.
     *
     */
    @Test(expectedExceptions = SecurityException.class)
    public void testExecuteNotBeingAdmin() throws Exception {
        when(userService.isUserAdmin()).thenReturn(Boolean.FALSE);
        handler.execute(new CommandDeleteAll(), null);
        verifyNoMoreInteractions(commandManager, userService);
    }

    @Test
    void execute() throws DispatchException {
        when(userService.isUserAdmin()).thenReturn(Boolean.TRUE);
        handler.execute(new CommandDeleteAll(), null);
        verify(commandManager).deleteAll();
        verifyNoMoreInteractions(commandManager);
        verify(userService).isUserAdmin();
    }

    @Test(expectedExceptions = ActionException.class)
    void commandManagerFails() throws DispatchException {
        when(userService.isUserAdmin()).thenReturn(Boolean.TRUE);
        doThrow(new RuntimeException()).when(commandManager).deleteAll();
        handler.execute(new CommandDeleteAll(), null);
    }
}
