package fspotcloud.server.main.user;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.server.SimpleActionHandler;

import com.google.inject.Inject;
import fspotcloud.shared.main.actions.GetUserInfo;
import fspotcloud.shared.main.actions.UserInfo;
import fspotcloud.user.UserService;
import net.customware.gwt.dispatch.shared.DispatchException;

public class GetUserInfoHandler extends SimpleActionHandler<GetUserInfo, UserInfo> {

    final private UserService userService;

    @Inject
    public GetUserInfoHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserInfo execute(GetUserInfo action, ExecutionContext context) throws DispatchException {
        UserInfo info = new UserInfo(userService.getEmail(),
                userService.isUserAdmin(), userService.isUserLoggedIn());
        return info;
    }
}
