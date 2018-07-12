package com.example.youngwookwon.myproject;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class Home_Fragment extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_, container, false);
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
        ((MainActivity)getActivity()).changeText("이달의 전시");
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview2);
        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);

        List<Home_View_Item> items = new ArrayList<>();
        Home_View_Item[] item = new Home_View_Item[7];
        int i = 0;
        try {
            URL url = new URL("http://openapi.seoul.go.kr:8088/46674359456869733835786b594d64/xml/SearchPerformanceBySubjectService/1/7/7/");
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
                            item[i] = new Home_View_Item(image," "+name," "+start+" ~ "+end, " "+place, code);
                            i++;
                        }

                        break;
                }
                parserEvent = parser.next();
            }
        } catch (Exception e) {

        }
        for (int j = 0; j < 7; j++) items.add(item[j]);
        recyclerView.setAdapter(new Home_View_Adapter(getActivity(),items,R.layout.fragment_home_));
        return view;
    }
}
