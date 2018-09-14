package com.example.youngwookwon.myproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.PopupWindow;
/*
아이디/비번 찾기
구현 제대로 안됨!
 */
public class ForgotActivity extends AppCompatActivity {
    private PopupWindow popup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //erase Title-bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        WindowManager.LayoutParams layoutParams=new WindowManager.LayoutParams();
        //blur background
        layoutParams.flags= WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        layoutParams.dimAmount=0.7f;
        getWindow().setAttributes(layoutParams);
        setContentView(R.layout.activity_forgot);
    }
}
