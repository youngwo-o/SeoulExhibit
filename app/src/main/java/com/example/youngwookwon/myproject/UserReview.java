package com.example.youngwookwon.myproject;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
/*
firebase db 객체 생성
review : 전시회 코드-사용자아이디, 날짜
user : 사용자 아이디-전시회코드, 리뷰
 */
public class UserReview {
    String cultcode;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String dbChild = user.getEmail().substring(0, user.getEmail().indexOf("@"));
    DatabaseReference databaseReference;

    public UserReview() {}
    void setCultcode(String cultcode) {
        this.cultcode = cultcode;
    }
    void saveReview(String review) {
        databaseReference = FirebaseDatabase.getInstance().getReference("user").child(dbChild);
        databaseReference.child(cultcode).setValue(review);
    }
}