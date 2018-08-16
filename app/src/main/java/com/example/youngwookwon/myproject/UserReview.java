package com.example.youngwookwon.myproject;

import android.support.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/*
    사용자 별 좋아요 및 리뷰 저장
    리뷰 쓴 경우 -> 전시회 title, review 저장
    *****하면서 굳이 객체 필요없으면 리뷰 쓸 때마다 바로바로 String으로 저장하게끔.
 */
public class UserReview {
    String cultcode;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String dbChild = user.getEmail().substring(0, user.getEmail().indexOf("@"));
    DatabaseReference databaseReference;

    public UserReview(String cultcode, String review) {
        this.setCultcode(cultcode);
        this.saveReview(review);
    }
    void setCultcode(String cultcode) {
        this.cultcode = cultcode;
    }
    void saveReview(String review) {
        databaseReference = FirebaseDatabase.getInstance().getReference("user").child(dbChild);
        databaseReference.child(cultcode).setValue(review);
    }
}