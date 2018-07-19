package com.example.youngwookwon.myproject.Info;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.example.youngwookwon.myproject.Info.MapActivity;
import com.example.youngwookwon.myproject.R;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class InfoActivity extends AppCompatActivity  {
    String TITLE = null;
    String ORG_LINK=null;
    String PLACE = null;
    List<Address> list = null;

    @BindView(R.id.link)
    Button link;
    @BindView(R.id.showmap)
    Button showmap;
    @BindView(R.id.info_toolbar)
    Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        getData();
    }

    private void getData() {
        Intent intent = getIntent();
        String TIME=null, DATE=null, STARTDATE=null, END_DATE=null, USE_TRGT=null, USE_FEE = null, INQUIRY=null;
        boolean intitle = false, intime = false, indate = false, instartdate = false,inend_date = false, inplace = false, inorg_link = false, inuse_trgt = false, inuse_fee = false, ininquiry = false;
        String str_url = "http://openapi.seoul.go.kr:8088/766b79726868697336354e79574b51/xml/SearchConcertDetailService/1/5/"+intent.getExtras().getString("cultcode");

        try {
            URL url = new URL(str_url);
            XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserCreator.newPullParser();
            parser.setInput(url.openStream(), null);
            int parserEvent = parser.getEventType();
            while (parserEvent != XmlPullParser.END_DOCUMENT) {
                switch (parserEvent) {
                    case XmlPullParser.START_TAG:
                        if (parser.getName().equals("TITLE")) {
                            intitle = true;
                        }
                        if (parser.getName().equals("STRTDATE")) {
                            instartdate = true;
                        }
                        if (parser.getName().equals("END_DATE")) {
                            inend_date = true;
                        }
                        if (parser.getName().equals("TIME")) {
                            intime = true;
                        }
                        if (parser.getName().equals("PLACE")) {
                            inplace = true;
                        }
                        if (parser.getName().equals("ORG_LINK")) {
                            inorg_link = true;
                        }
                        if(parser.getName().equals("USE_TRGT")){
                            inuse_trgt = true;
                        }
                        if(parser.getName().equals("USE_FEE")){
                            inuse_fee = true;
                        }
                        if(parser.getName().equals("INQUIRY")){
                            ininquiry = true;
                        }
                        break;
                    case XmlPullParser.TEXT:
                        if (intitle) {
                            TITLE = parser.getText();
                            TextView titletext;
                            titletext = (TextView) findViewById(R.id.exhibitname);
                            titletext.setText(TITLE);
                            intitle = false;
                        }
                        if (instartdate) {
                            STARTDATE = parser.getText();
                            instartdate = false;
                        }
                        if (inend_date) {
                            END_DATE = parser.getText();
                            DATE = STARTDATE +" ~ " + END_DATE;
                            TextView datetext;
                            datetext = (TextView) findViewById(R.id.exhibitdate);
                            datetext.setText(DATE);
                            inend_date = false;
                        }
                        if (intime) {
                            TIME = parser.getText();
                            TextView timetext;
                            timetext = (TextView) findViewById(R.id.exhibittime);
                            timetext.setText(TIME);
                            intime = false;
                        }
                        if(inplace){
                            PLACE = parser.getText();
                            TextView placetext;
                            placetext = (TextView) findViewById(R.id.exhibitplace);
                            placetext.setText(PLACE);
                            inplace =false;
                        }
                        if (inorg_link) {
                            ORG_LINK = parser.getText();
                            inorg_link = false;
                            link.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(ORG_LINK));
                                    startActivity(intent);
                                }
                            });
                        }
                        if (inuse_trgt) {
                            USE_TRGT = parser.getText();
                            TextView trgttext;
                            trgttext = (TextView)findViewById(R.id.exhibittrgt);
                            trgttext.setText(USE_TRGT);
                            inuse_trgt = false;
                        }
                        if (inuse_fee) {
                            USE_FEE = parser.getText();
                            TextView feetext;
                            feetext = (TextView) findViewById(R.id.exhibitfee);
                            feetext.setText(USE_FEE);
                            inuse_fee = false;
                        }
                        if (ininquiry){
                            INQUIRY = parser.getText();
                            ininquiry = false;
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        break;
                }
                parserEvent = parser.next();
            }
        } catch (Exception e) {
        }
    }

    private void getLocation() {
        Geocoder geocoder = new Geocoder(this);
        try {
            list = geocoder.getFromLocationName(
                    PLACE, // 지역 이름
                    10); // 읽을 개수
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("test","입출력 오류 - 서버에서 주소변환시 에러발생");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.fav) //fav button click
    public void favEvent() {

    }

    @OnClick(R.id.showmap)
    public void showMapPage() {
        getLocation();
        Address addr = list.get(0);
        double latitude = addr.getLatitude();
        double longitude = addr.getLongitude();

        Intent intent = new Intent(getApplicationContext(), MapActivity.class);
        intent.putExtra("title", TITLE);
        intent.putExtra("place", PLACE);
        intent.putExtra("latitude", latitude);
        intent.putExtra("longitude", longitude);
        startActivity(intent);
    }
}