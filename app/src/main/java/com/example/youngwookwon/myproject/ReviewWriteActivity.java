package com.example.youngwookwon.myproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ReviewWriteActivity extends AppCompatActivity{
    @BindView(R.id.Review)
    Button Review;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_write);
    }

    public void write_review(View view) {
        Toast.makeText(this, "ddddddd", Toast.LENGTH_SHORT).show();
        finish();
    }
}
