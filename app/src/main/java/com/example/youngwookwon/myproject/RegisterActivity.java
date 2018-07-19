package com.example.youngwookwon.myproject;

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

public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText edit_Email;
    private EditText edit_PW;
    private String Email;
    private String PW;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
    }

    public void Register(View view) { //Register Activity
        if(isEmptyEditText()); //see if there's any blanks
        else {
            if(isValidEmail(Email)) { //check if Email is valid
                mAuth.createUserWithEmailAndPassword(Email, PW).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this, "Signed Failed", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(RegisterActivity.this, "SignIn Success", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
            else {
                Toast.makeText(this, "your E-Mail is not VALID.", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private boolean isEmptyEditText() {
        edit_Email = findViewById(R.id.edit_regEmail);
        edit_PW = findViewById(R.id.edit_regPW);
        Email = edit_Email.getText().toString();
        PW = edit_PW.getText().toString();
        if(Email.matches("") || PW.matches("")) {
            Toast.makeText(this, "Please Fill in E-mail and Password Completely", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }
    private boolean isValidEmail(String Email) {
        return Patterns.EMAIL_ADDRESS.matcher(Email).matches();
    }
}
