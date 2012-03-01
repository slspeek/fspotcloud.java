package fspotcloud.server.admin.actions;

import java.util.logging.Logger;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.server.SimpleActionHandler;
import net.customware.gwt.dispatch.shared.ActionException;
import net.customware.gwt.dispatch.shared.DispatchException;

import com.google.inject.Inject;

import com.googlecode.botdispatch.model.api.Commands;
import fspotcloud.shared.dashboard.actions.CommandDeleteAll;
import fspotcloud.shared.dashboard.actions.VoidResult;
import fspotcloud.user.UserService;
import javax.inject.Provider;

public class CommandDeleteAllHandler extends SimpleActionHandler<CommandDeleteAll, VoidResult> {

    @SuppressWarnings("unused")
    private static final Logger log = Logger.getLogger(CommandDeleteAllHandler.class.getName());
    final private Commands commandManager;
    private final Provider<UserService> userServiceProvider;

    @Inject
    public CommandDeleteAllHandler(Commands commandManager, Provider<UserService> userServiceProvider) {
        super();
        this.commandManager = commandManager;
        this.userServiceProvider = userServiceProvider;
    }

    @Override
    public VoidResult execute(CommandDeleteAll action, ExecutionContext context)
            throws DispatchException {
        UserService userService = userServiceProvider.get();
        if (!userService.isUserAdmin()) {
            throw new SecurityException("Not admin");
        }
        try {
            commandManager.deleteAll();
        } catch (Exception e) {
            throw new ActionException(e);
        }
        return new VoidResult();
    }
}