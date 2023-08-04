package com.example.b07group7project;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import com.example.b07group7project.ui.login.EmailPasswordActivity;
import com.example.b07group7project.ui.login.LoginFragment;
import com.example.b07group7project.ui.login.LoginModel;
import com.example.b07group7project.ui.login.LoginPresenter;
import com.example.b07group7project.ui.login.LoginView;
import com.example.b07group7project.ui.login.RegisterFragment;
import com.example.b07group7project.ui.login.RegisterPresenter;
import com.example.b07group7project.ui.login.ResetPasswordFragment;
import com.example.b07group7project.ui.login.ResetPasswordPresenter;
import com.example.b07group7project.ui.login.ResetPasswordView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {


    LoginView loginView = mock(LoginView.class);


    LoginModel loginModel = mock(LoginModel.class);
    EmailPasswordActivity emailPasswordActivity = mock(EmailPasswordActivity.class);


    LoginPresenter loginPresenter = new LoginPresenter(loginModel, loginView);

    ArgumentCaptor<OnCompleteListener<AuthResult>> captor =
            ArgumentCaptor.forClass(OnCompleteListener.class);

    Task<AuthResult> task = mock(Task.class);
    FirebaseUser user = mock(FirebaseUser.class);

    @Test
    public void testForgotPasswordOnClick(){
        loginPresenter.forgotPasswordOnClick();

        verify(loginView).replaceFragment(any(ResetPasswordFragment.class));
        verifyNoInteractions(loginModel);
    }

    @Test
    public void testUsernameCannotBeNull(){
        assertFalse(loginPresenter.isValid(null, "myPassword"));
        verifyNoInteractions(loginModel, loginView);
    }

    @Test
    public void testPasswordMustBeLongEnough(){
        assertFalse(loginPresenter.isValid("user", "12345"));
        verifyNoInteractions(loginModel, loginView);
    }

    @Test
    public void testVerifiesGoodUsernameAndPassword(){
        assertTrue(loginPresenter.isValid("myUser", "myPassword"));
        verifyNoInteractions(loginModel, loginView);
    }

    @Test
    public void testLoginWithInvalidData(){
        loginPresenter.loginButtonOnClick("user", null);
        verifyNoInteractions(loginModel, loginView);
    }

    @Test
    public void testLoginPassesCredentialsAndOnLoginAttempt(){
        loginPresenter.loginButtonOnClick("user", "password");

        verify(loginView).setLoadingAnimation(true);
        verify(loginModel).signIn(eq("user"), eq("password"), any());
        verifyNoMoreInteractions(loginModel, loginView);
    }

    @Test
    public void testOnLoginFailedRedirect(){
        loginPresenter.loginButtonOnClick("user", "password");

        verify(loginView).setLoadingAnimation(true);
        verify(loginModel).signIn(eq("user"), eq("password"), captor.capture());
        OnCompleteListener<AuthResult> result = captor.getValue();

        when(loginModel.getCurrentUser()).thenReturn(null);
        result.onComplete(task);

        verify(loginModel).getCurrentUser();
        verify(loginView).onLoginFailed();
        verify(loginView).setLoadingAnimation(false);
        verifyNoMoreInteractions(loginModel, loginView);
    }

    @Test
    public void testOnLoginSucceededRedirect(){
        loginPresenter.loginButtonOnClick("user", "password");

        verify(loginView).setLoadingAnimation(true);
        verify(loginModel).signIn(eq("user"), eq("password"), captor.capture());
        OnCompleteListener<AuthResult> result = captor.getValue();

        when(loginModel.getCurrentUser()).thenReturn(user);
        result.onComplete(task);
        
        verify(loginModel).getCurrentUser();
        verify(loginView).onLoginComplete();
        verify(loginView).setLoadingAnimation(false);
        verifyNoMoreInteractions(loginModel, loginView);
    }

    @Test
    public void testRegisterWithInvalidData(){
        loginPresenter.registerButtonOnClick("user", null);
        verifyNoInteractions(loginModel, loginView);
    }

    @Test
    public void testOnRegistrationFailedRedirect(){
        loginPresenter.registerButtonOnClick("user", "password");

        verify(loginView).setLoadingAnimation(true);
        verify(loginModel).register(eq("user"), eq("password"), captor.capture());
        OnCompleteListener<AuthResult> result = captor.getValue();

        when(loginModel.getCurrentUser()).thenReturn(null);
        result.onComplete(task);

        verify(loginModel).getCurrentUser();
        verify(loginView).onRegistrationFailed();
        verify(loginView).setLoadingAnimation(false);
        verifyNoMoreInteractions(loginModel, loginView);
    }

    @Test
    public void testOnRegistrationSucceededRedirect(){
        loginPresenter.registerButtonOnClick("user", "password");

        verify(loginView).setLoadingAnimation(true);
        verify(loginModel).register(eq("user"), eq("password"), captor.capture());
        OnCompleteListener<AuthResult> result = captor.getValue();

        when(loginModel.getCurrentUser()).thenReturn(user);
        result.onComplete(task);

        verify(loginModel).getCurrentUser();
        verify(loginView).replaceFragment(any(RegisterFragment.class));
        verify(loginView).setLoadingAnimation(false);
        verifyNoMoreInteractions(loginModel, loginView);
    }

    @Test
    public void testRegisterPresenterStoreOwner(){
        when(loginModel.getCurrentUser()).thenReturn(null);

        RegisterPresenter registerPresenter = new RegisterPresenter(emailPasswordActivity, loginModel);
        registerPresenter.handleCreateAccount(UserType.STORE_OWNER);

        verify(loginModel).setUserType(UserType.STORE_OWNER, null);
        verify(emailPasswordActivity).onLoginCompete();
    }

    @Test
    public void testRegisterPresenterShopper(){
        when(loginModel.getCurrentUser()).thenReturn(user);

        RegisterPresenter registerPresenter = new RegisterPresenter(emailPasswordActivity, loginModel);
        registerPresenter.handleCreateAccount(UserType.SHOPPER);

        verify(loginModel).setUserType(UserType.SHOPPER, user);
        verify(emailPasswordActivity).onLoginCompete();
    }

    @Test
    public void testResetPasswordOnGoBack(){
        ResetPasswordView resetPasswordView = mock(ResetPasswordView.class);

        ResetPasswordPresenter resetPasswordPresenter = new ResetPasswordPresenter(resetPasswordView, loginModel);
        resetPasswordPresenter.onGoBack();

        verify(resetPasswordView).replaceFragment(any(LoginFragment.class));
    }

    @Test
    public void testResetPasswordOnSendResetLink(){
        ResetPasswordView resetPasswordView = mock(ResetPasswordView.class);
        when(resetPasswordView.getEmail()).thenReturn("myEmail");

        ResetPasswordPresenter resetPasswordPresenter = new ResetPasswordPresenter(resetPasswordView, loginModel);
        resetPasswordPresenter.onSendResetLink();

        verify(resetPasswordView).getEmail();
        verify(loginModel).sendResetLink("myEmail");
        verify(resetPasswordView).showEmailSentNotification();
        verifyNoMoreInteractions(loginModel, resetPasswordView);
    }

}