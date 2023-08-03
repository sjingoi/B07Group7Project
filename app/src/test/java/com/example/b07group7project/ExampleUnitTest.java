package com.example.b07group7project;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

import androidx.fragment.app.Fragment;

import com.example.b07group7project.ui.login.LoginModel;
import com.example.b07group7project.ui.login.LoginPresenter;
import com.example.b07group7project.ui.login.LoginView;
import com.example.b07group7project.ui.login.LoginViewAdapter;
import com.example.b07group7project.ui.login.ResetPasswordFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

public class ExampleUnitTest {



    @Test
    public void testForgotPasswordOnClick(){
        final boolean[] wasFragmentSwitchedToResetPassword = {false};
        LoginPresenter presenter = new LoginPresenter(
                new LoginModel() {
            @Override
            public void sendResetLink(String email) {
                throw new RuntimeException();
            }
            @Override
            public void signIn(String email, String password, OnCompleteListener<AuthResult> onComplete) {
                throw new RuntimeException();
            }
            @Override
            public void register(String email, String password, OnCompleteListener<AuthResult> onComplete) {
                throw new RuntimeException();
            }
            @Override
            public FirebaseUser getCurrentUser() {
                throw new RuntimeException();
            }
            @Override
            public void setUserType(UserType type, FirebaseUser user) {
                throw new RuntimeException();
            }
            @Override
            public UserType getUserType(FirebaseUser user) {
                throw new RuntimeException();
            }
            @Override
            public void signOut() {
                throw new RuntimeException();
            }
        },
                new LoginView() {
            @Override
            public void setLoadingAnimation(boolean isLoading) {
                throw new RuntimeException();
            }

            @Override
            public void replaceFragment(Fragment resetPasswordFragment) {
                wasFragmentSwitchedToResetPassword[0] = true;
                assert resetPasswordFragment instanceof ResetPasswordFragment;
            }

            @Override
            public void onRegistrationFailed() {
                throw new RuntimeException();
            }

            @Override
            public void onLoginComplete() {
                throw new RuntimeException();
            }

            @Override
            public void onLoginFailed() {
                throw new RuntimeException();
            }
        }
        );
        presenter.forgotPasswordOnClick();
        assert wasFragmentSwitchedToResetPassword[0];
    }



}