package com.example.youngwookwon.myproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;


public class UserInfoActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo);
        ImageView naeunimage = (ImageView)findViewById(R.id.naeun);
        naeunimage.setImageResource(R.drawable.mylove);
    }
    public void backClick(View view)
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
        //나중에 고쳐야됨
    }

    public void click_signout(View view) {
        FirebaseAuth.getInstance().signOut();
        LoginManager.getInstance().logOut();
        Intent intent = new Intent(UserInfoActivity.this, MainActivity.class);
        startActivity(intent);
        Toast.makeText(UserInfoActivity.this, "Signed Out Successfully! ", Toast.LENGTH_SHORT).show();
    }
}