package com.example.youngwookwon.myproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private EditText edit_Name;
    private EditText edit_Nickname;
    private EditText edit_Email;
    private EditText edit_PW;
    private String Name;
    private String Nickname;
    private String Email;
    private String dbChild;
    private String PW;
    private DatabaseReference mDatabase;
    private UserInfo user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    public void Register(View view) { //Register Activity
        if(isEmptyEditText()); //see if there's any blanks
        else {
            if(isValidEmail(Email)) { //check if Email is valid
                mAuth.createUserWithEmailAndPassword(Email, PW).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            //TEMP))DEBUG
                            Toast.makeText(RegisterActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            //TEMP))DEBUG
                            Toast.makeText(RegisterActivity.this, "가입되었습니다", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
            else {
                Toast.makeText(this, "잘못된 형식의 이메일입니다.", Toast.LENGTH_SHORT).show();
            }
        }
        user = new UserInfo(Name, Nickname, Email, PW);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("user").child(dbChild).setValue(user);
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        //intent.putExtra("UserTestData", user);
        //intent.putExtra("userNickname", user.getnickname());
        startActivity(intent);
        finish();
    }
    private boolean isEmptyEditText() {
        edit_Name = findViewById(R.id.edit_Name);
        edit_Nickname = findViewById(R.id.edit_Nickname);
        edit_Email = findViewById(R.id.edit_regEmail);
        edit_PW = findViewById(R.id.edit_regPW);
        Name = edit_Name.getText().toString();
        Nickname = edit_Nickname.getText().toString();
        Email = edit_Email.getText().toString();
        PW = edit_PW.getText().toString();
        if(Name.matches("") || Email.matches("") || PW.matches("") || Nickname.matches("")) {
            Toast.makeText(this, "모든 칸을 채워주세요", Toast.LENGTH_SHORT).show();
            return true;
        }
        int index;
        index = Email.indexOf("@");
        dbChild = Email.substring(0, index);
        return false;
    }
    private boolean isValidEmail(String Email) {
        return Patterns.EMAIL_ADDRESS.matcher(Email).matches();
    }
}
