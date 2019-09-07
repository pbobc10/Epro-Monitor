package com.example.cbpierre.epromonitor.adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.cbpierre.epromonitor.fragments.PaContactFragment;

public class PaContactPagerAdapter extends FragmentPagerAdapter {
    private int NUM_ITEMS = 2;

    public PaContactPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    // Returns the fragment to display for that page
    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                //return null;
                return new PaContactFragment();
            case 1:
                //return null;
                return new PaContactFragment();
            default:
                return null;
        }

    }

    // Returns total number of pages
    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    // Returns the page title for the top indicator
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        super.getPageTitle(position);
        if (position == 0)
            return "PA Contact";
        else
            return "Produit";
    }
}
