package com.example.youngwookwon.myproject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
public class MainActivity extends AppCompatActivity {
    private BottomNavigationView mBottomNavigation;
    Intent intent;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TextView textview;
    DisplayInfo[] display;

    @BindView(R.id.search_button)
    ImageButton search_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.enableDefaults();

        Typeface typeface = Typeface.createFromAsset(this.getAssets(), "font/seoulnamsanm.ttf");


        List<Home_View_Item> items = new ArrayList<>();
        Home_View_Item[] item = new Home_View_Item[20];
        display = new DisplayInfo[40];
        int i = 0;

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_main);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        // Initializing ViewPager
        viewPager = (ViewPager) findViewById(R.id.main_pager);

        // Creating TabPagerAdapter adapter
        TabPagerAdapter pagerAdapter = new TabPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        // Set TabSelectedListener
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public void clickProfile(View view) {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
    }

    public void clickSearch(View view) {
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }

    public void getDisplay(DisplayInfo display, int count)
    {
        this.display[count] = display;
    }
}