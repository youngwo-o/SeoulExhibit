package com.example.youngwookwon.myproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

/**
 * Created by user on 2018-07-09.
 */

public class SearchActivity extends Activity {
    private BottomNavigationView mBottomNavigation;
    Intent intent;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        mBottomNavigation = (BottomNavigationView) findViewById(R.id.a_search_bnv);
        mBottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_Home:
                        intent = new Intent(SearchActivity.this, MainActivity.class);
                        //   intent.putExtra(“text”,String.valueOf(editText.getText()));
                        startActivity(intent);
                        return true;
                    case R.id.action_Search:
                        intent = new Intent(SearchActivity.this, SearchActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.action_SNS:
                        return true;

                    default:
                        return false;
                }
            }
        });
    }
}
