package com.example.youngwookwon.myproject;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;
import android.widget.TextView;

public class TabPagerAdapter extends FragmentStatePagerAdapter {
    TextView textview;
    View view;
    // Count number of tabs
    private int tabCount;
    public TabPagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {

        // Returning the current tabs
        switch (position) {
            case 0:
                Home_Fragment tabFragment1 = new Home_Fragment();
                return tabFragment1;
            case 1:
                Search_Fragment tabFragment2 = new Search_Fragment();
                return tabFragment2;
            case 2:
                SNS_Fragment tabFragment3 = new SNS_Fragment();
                return tabFragment3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
