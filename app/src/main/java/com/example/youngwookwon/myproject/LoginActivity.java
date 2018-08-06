package com.example.youngwookwon.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private CallbackManager callbackManager;
    private EditText edit_Email;
    private EditText edit_PW;

    private String userNickname;
    private String userUID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        //User State Listener
        mAuthListener = new FirebaseAuth.AuthStateListener() { //사용자의 로그인 상태 변화에 따라 이벤트 받음
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) { //user is signed in
                    Toast.makeText(LoginActivity.this, "Welcome! "+userNickname, Toast.LENGTH_SHORT).show();
                    //UserInfo userInfo = (UserInfo)getIntent().getParcelableExtra("UserTestData");
                    Intent intent = new Intent(LoginActivity.this, UserInfoActivity.class);
                    startActivity(intent);
                }
                else { //user is signed out
                    Toast.makeText(LoginActivity.this, "Signed out", Toast.LENGTH_SHORT).show();
                }
            }
        };

        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();

        //Facebook already Log in
        if (isLoggedIn) {
            Toast.makeText(LoginActivity.this, "Welcome! (fb)", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }

        //Facebook not log in
        else {
            callbackManager = CallbackManager.Factory.create();
            LoginButton fblogin = findViewById(R.id.button_fblogin);
            fblogin.setReadPermissions("email", "public_profile");
            fblogin.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
//            LoginManager.getInstance().registerCallback(callbackManager,
//                    new FacebookCallback<LoginResult>() {
                        @Override
                        public void onSuccess(LoginResult loginResult) {
                            AccessToken token = loginResult.getAccessToken();
                            AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
                            mAuth.signInWithCredential(credential).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()) {
                                        Log.d("Debug", "SignInWithCredential:success");
                                    }
                                }
                            });
                            Toast.makeText(LoginActivity.this, "Welcome! (fb)", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                        }

                        @Override
                        public void onCancel() {
                            Toast.makeText(LoginActivity.this, "Facebook Signed Out", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onError(FacebookException exception) {
                            // App code
                        }
                    });
        }
    }

    /*
     start Listener when App starts,
     end Listener when App stops
     */
    @Override
    public void onStart() {
        super.onStart();
        //check if user is signed in(!null) and update UI accordingly
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) { //user is signed in
            Intent intent = new Intent(LoginActivity.this, UserInfoActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    //event function for 'LOGIN' button
    public void click_login(View view) {
        edit_Email = (EditText) findViewById(R.id.edit_Email);
        edit_PW = (EditText) findViewById(R.id.edit_PW);
        final String Email = edit_Email.getText().toString();
        final String PW = edit_PW.getText().toString();

        mAuth.signInWithEmailAndPassword(Email, PW).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                //checkTaskException(task);
                if (!task.isSuccessful()) {
                    Toast.makeText(LoginActivity.this, "Sign in Failed", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(LoginActivity.this, "Welcome!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void click_register(View view) {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void click_forgot_pw(View view) {
        //Intent intent = new Intent(LoginActivity.this, ForgotActivity.class);
        //startActivity(intent);
    }
}