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
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.enableDefaults();
        String code = null;
        String name = null;
        String start = null;
        String end = null;
        String place = null;
        String image = null;

        boolean incode = false;
        boolean inname = false;
        boolean instart = false;
        boolean inend = false;
        boolean inplace = false;
        boolean inimage = false;

        Typeface typeface = Typeface.createFromAsset(this.getAssets(), "font/seoulnamsanm.ttf");
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview1);
     //   TextView textview = findViewById(R.id.main_text);
//        textview.setTypeface(typeface);
  //      textview.setText("이달의 전시");
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        List<Home_View_Item> items = new ArrayList<>();
        Home_View_Item[] item = new Home_View_Item[20];
        display = new DisplayInfo[12];
        int i = 0;
        try {
            URL url = new URL("http://openapi.seoul.go.kr:8088/46674359456869733835786b594d64/xml/SearchPerformanceBySubjectService/1/20/7/");
            XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserCreator.newPullParser();
            parser.setInput(url.openStream(), null);

            int parserEvent = parser.getEventType();
            while (parserEvent != XmlPullParser.END_DOCUMENT) {
                switch (parserEvent) {
                    case XmlPullParser.START_TAG:
                        if (parser.getName().equals("CULTCODE")) {
                            incode = true;
                        }
                        if (parser.getName().equals("TITLE")) {
                            inname = true;
                        }
                        if (parser.getName().equals("STRTDATE")) {
                            instart = true;
                        }
                        if (parser.getName().equals("END_DATE")) {
                            inend = true;
                        }
                        if (parser.getName().equals("PLACE")) {
                            inplace = true;
                        }
                        if (parser.getName().equals("MAIN_IMG")) {
                            inimage = true;
                        }
                        break;
                    case XmlPullParser.TEXT:
                        if (incode) {
                            code = parser.getText();
                            incode = false;
                        }
                        if (inname) {
                            name = parser.getText();
                            inname = false;
                        }
                        if (instart) {
                            start = parser.getText();
                            instart = false;
                        }
                        if (inend) {
                            end = parser.getText();
                            inend = false;
                        }
                        if (inplace) {
                            place = parser.getText();
                            inplace = false;
                        }
                        if (inimage) {
                            String iaddress = null;
                            String iextension = null;
                            image = parser.getText();
                            iaddress = image.substring(0, image.length() - 3);
                            iextension = image.substring(image.length() - 3, image.length());
                            iaddress = iaddress.toLowerCase();
                            image = iaddress.concat(iextension);
                            inimage = false;
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if (parser.getName().equals("row")) {
                            String tmp = "이름 : " + name + "\n기간 : " + start + "~" + end + "\n장소 : " + place + "";
                            display[i] = new DisplayInfo(code, name, start, end, place, image);
                            item[i] = new Home_View_Item(image," "+name," "+start+" ~ "+end, " "+place, code);
                            i++;
                        }
                        break;
                }
                parserEvent = parser.next();
            }
        } catch (Exception e) {
        }
        for (int j = 0; j < 20; j++) items.add(item[j]);
        recyclerView.setAdapter(new Home_View_Adapter(getApplicationContext(),items, R.layout.activity_main));

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
       //         TextView textview = (TextView) findViewById(R.id.main_text);
      //          if(tab.getPosition() == 0) textview.setText("이달의 전시");
        //        else if(tab.getPosition() == 1) textview.setText("전시회 리뷰");
          //      else if(tab.getPosition() == 2) textview.setText("좋아요 목록");
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
  /*  public void changeText(String Text)
    {
        TextView textview = (TextView)findViewById(R.id.main_text);
        textview.setText(Text);
    }*/

    public void click_Profile(View view) {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    public void imgClick(View view)
    {
       // Intent intent = new Intent(this, UserInfoActivity.class);
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
    }
}