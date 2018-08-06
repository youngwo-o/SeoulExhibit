package com.example.youngwookwon.myproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class UserInfoActivity extends Activity {
    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;
    private String dbChild;
    private UserInfo userInfo;
    private String uid;
    private String name;
    private String nickname;
    private String email;
    private String photoURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) { //user is signed in
            email = user.getEmail(); //로그인 된 사용자 이메일 불러옴
            dbChild = email.substring(0, email.indexOf("@"));
            databaseReference = FirebaseDatabase.getInstance().getReference("user").child(dbChild);
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    userInfo = dataSnapshot.getValue(UserInfo.class);
                    if(userInfo != null) {
                        name = userInfo.getName();
                        nickname = userInfo.getNickname();
                        photoURL = userInfo.getImage();
                        Toast.makeText(UserInfoActivity.this, name + "/" + email + "/", Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }


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
        Toast.makeText(UserInfoActivity.this, "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show();
    }
}