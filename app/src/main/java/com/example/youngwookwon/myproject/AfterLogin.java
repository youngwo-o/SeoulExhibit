package com.example.youngwookwon.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;

public class AfterLogin extends AppCompatActivity {
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
    }

    public void click_signout(View view) {
            FirebaseAuth.getInstance().signOut();
            LoginManager.getInstance().logOut();
            //Intent intent = new Intent(AfterLogin.this, LoginActivity.class);
            //startActivity(intent);
            Toast.makeText(AfterLogin.this, "Signed Out Successfully! ", Toast.LENGTH_SHORT).show();
    }
}
