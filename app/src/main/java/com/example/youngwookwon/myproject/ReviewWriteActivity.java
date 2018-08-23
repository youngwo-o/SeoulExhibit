package com.example.youngwookwon.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*
리뷰 쓰기 팝업창
리뷰 쓴 후 등록 누르면 데이터 저장
 */
public class ReviewWriteActivity extends AppCompatActivity{
    @BindView(R.id.button_review)
    Button button_review;
    private String dbChild;
    private String date;
    private EditText edit_review;
    private String review;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_write);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button_review)
    public void write_review() {
        isEmptyEditText();
        Intent intent = getIntent();
        final String cultcode = intent.getExtras().getString("cultcode");
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        dbChild = user.getEmail().substring(0, user.getEmail().indexOf("@"));
        final DatabaseReference databaseReference;
        long now = System.currentTimeMillis();
        Date _date = new Date(now);


        databaseReference = FirebaseDatabase.getInstance().getReference("review").child(cultcode);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                databaseReference.child(dbChild).setValue("날짜"); //사용자 아이디 : 작성시간
                UserReview userReview = new UserReview();
                userReview.setCultcode(cultcode);
                userReview.saveReview(review);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Toast.makeText(this, "리뷰가 등록되었습니다.", Toast.LENGTH_SHORT).show();
        finish();
    }

    //리뷰 받아오는 함수
    private void isEmptyEditText() {
        edit_review = findViewById(R.id.edit_review);
        review = edit_review.getText().toString();
        if (review.matches("")) {
            review = new String("흥미로웠습니다!");
        }
    }
}
