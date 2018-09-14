package com.example.youngwookwon.myproject;

import android.support.annotation.NonNull;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class GetReview {
    String id;
    String review;

    public GetReview(String id, final String cultcode) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("user").child(id).child(cultcode);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue() != null) {
                    setReview(dataSnapshot.getValue().toString());
                    /*for(DataSnapshot reviewSnapshot: dataSnapshot.getChildren()) {
                        if(reviewSnapshot.getKey().equals(cultcode)) {
                            review = reviewSnapshot.child(cultcode).getValue(String.class);
                        }
                    }*/
                }
                else {
                    System.out.println("1111111111111111111111111111111111");
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    void setReview(String review) {
        this.review = review;
    }
    String getreview() {
        return this.review;
    }
}
