package com.example.youngwookwon.myproject;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserReview {
    String cultcode;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String dbChild = user.getEmail().substring(0, user.getEmail().indexOf("@"));
    DatabaseReference databaseReference;

    public UserReview(String cultcode, String review) {
        this.setCultcode(cultcode);
    }
    void setCultcode(String cultcode) {
        this.cultcode = cultcode;
    }
    void saveReview(String review) {
        databaseReference = FirebaseDatabase.getInstance().getReference("user").child(dbChild);
        databaseReference.child(cultcode).setValue(review);
    }
}