package com.googlecode.fspotcloud.client.main.view;


import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.googlecode.fspotcloud.client.main.view.api.LoginView;
import com.googlecode.fspotcloud.shared.main.AuthenticationResult;
import com.googlecode.fspotcloud.shared.main.GetUserInfo;
import com.googlecode.fspotcloud.shared.main.UserInfo;
import net.customware.gwt.dispatch.client.DispatchAsync;
import net.customware.gwt.dispatch.shared.Action;
import net.customware.gwt.dispatch.shared.Result;
import org.jukito.JukitoRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@RunWith(JukitoRunner.class)
public class LoginPresenterImplTest {

    @Inject
    LoginPresenterImpl presenter;

    @Test
    public void testStart(LoginView loginView, DispatchAsync dispatch, AcceptsOneWidget panel,
                          ArgumentCaptor<AsyncCallback<UserInfo>> captor, ArgumentCaptor<Action> actionCaptor) throws Exception {
        presenter.start(panel, null);
        verify(dispatch).execute(actionCaptor.capture(), captor.capture());
        GetUserInfo action = (GetUserInfo) actionCaptor.getValue();
        assertEquals("FSpotCloud.html", action.getDestinationUrl());
        verify(loginView).setPresenter(presenter);
        verify(loginView).focusUserNameField();
        verify(panel).setWidget(loginView);
        verifyNoMoreInteractions(panel, loginView, dispatch);
    }

    @Test
    public void testOnUserFieldKeyUp(LoginView loginView, DispatchAsync dispatch,
                                     ArgumentCaptor<AsyncCallback<UserInfo>> captor, ArgumentCaptor<Action> actionCaptor) throws Exception {
                     presenter.onUserFieldKeyUp(11)
                     ;
        verifyNoMoreInteractions(loginView, dispatch);
        presenter.onUserFieldKeyUp(13);
        verify(loginView).focusPasswordField();
        verifyNoMoreInteractions(loginView, dispatch);
    }

    @Test
    public void testOnPasswordFieldKeyUp(LoginView loginView, DispatchAsync dispatch,
                                         ArgumentCaptor<AsyncCallback<?>> captor, ArgumentCaptor<Action> actionCaptor) throws Exception {
        presenter.onPasswordFieldKeyUp(11);
        verifyNoMoreInteractions(loginView, dispatch);
     }

    @Test
    public void testSubmitError(LoginView loginView, DispatchAsync dispatch,
                               ArgumentCaptor<AsyncCallback<? extends Result>> captor, ArgumentCaptor<Action> actionCaptor) throws Exception {
        presenter.onPasswordFieldKeyUp(13);
        when(loginView.getPasswordField()).thenReturn("Secret");
        when(loginView.getUserNameField()).thenReturn("Admin");
        verify(dispatch).execute(actionCaptor.capture(), captor.capture());
        verify(loginView).getUserNameField();
        verify(loginView).getPasswordField();
        verifyNoMoreInteractions(dispatch, loginView);

        AsyncCallback<AuthenticationResult> callback =  (AsyncCallback<AuthenticationResult>) captor.getValue();
        callback.onFailure(new RuntimeException("Boom"));
        verify(loginView).setStatusText(LoginPresenterImpl.AN_ERROR_OCCURRED_MAKING_THE_AUTHENTICATION_REQUEST);

        verifyNoMoreInteractions(loginView, dispatch);
    }

    @Test
    public void testSubmitFailure(LoginView loginView, DispatchAsync dispatch,
                                ArgumentCaptor<AsyncCallback<? extends Result>> captor, ArgumentCaptor<Action> actionCaptor) throws Exception {
        presenter.onPasswordFieldKeyUp(13);
        when(loginView.getPasswordField()).thenReturn("Secret");
        when(loginView.getUserNameField()).thenReturn("Admin");
        verify(dispatch).execute(actionCaptor.capture(), captor.capture());
        verify(loginView).getUserNameField();
        verify(loginView).getPasswordField();
        verifyNoMoreInteractions(dispatch, loginView);

        AsyncCallback<AuthenticationResult> callback =  (AsyncCallback<AuthenticationResult>) captor.getValue();
        callback.onSuccess(new AuthenticationResult(false));
        verify(loginView).setStatusText(LoginPresenterImpl.NOT_A_VALID_USERNAME_AND_PASSWORD_COMBINATION);

        verifyNoMoreInteractions(loginView, dispatch);
    }

    @Test
    public void testSubmitSuccess(LoginView loginView, DispatchAsync dispatch,
                                ArgumentCaptor<AsyncCallback<? extends Result>> captor, ArgumentCaptor<Action> actionCaptor) throws Exception {
        presenter.onPasswordFieldKeyUp(13);
        when(loginView.getPasswordField()).thenReturn("Secret");
        when(loginView.getUserNameField()).thenReturn("Admin");
        verify(dispatch).execute(actionCaptor.capture(), captor.capture());
        verify(loginView).getUserNameField();
        verify(loginView).getPasswordField();
        verifyNoMoreInteractions(dispatch, loginView);

        AsyncCallback<AuthenticationResult> callback =  (AsyncCallback<AuthenticationResult>) captor.getValue();
        callback.onSuccess(new AuthenticationResult(true));
        verify(loginView).setStatusText(LoginPresenterImpl.LOGGED_IN);

        verifyNoMoreInteractions(loginView, dispatch);
    }
}
