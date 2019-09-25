package com.example.cbpierre.epromonitor.adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.cbpierre.epromonitor.fragments.DimancheGHFragment;
import com.example.cbpierre.epromonitor.fragments.JeudiFragment;
import com.example.cbpierre.epromonitor.fragments.LundiGHFragment;
import com.example.cbpierre.epromonitor.fragments.MardiGHFragment;
import com.example.cbpierre.epromonitor.fragments.MercrediGHFragment;
import com.example.cbpierre.epromonitor.fragments.SamediFragment;
import com.example.cbpierre.epromonitor.fragments.VendrediGHFragment;

public class GHCourantPagerAdapter extends FragmentPagerAdapter {
    private int NUM_ITEMS = 7;

    public GHCourantPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    // Returns the fragment to display for that page
    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                //return null;
                return new LundiGHFragment();
            case 1:
                //return null;
                return new MardiGHFragment();
            case 2:
                //return null;
                return new MercrediGHFragment();
            case 3:
                //return null;
                return new JeudiFragment();
            case 4:
                //return null;
                return new VendrediGHFragment();
            case 5:
                //return null;
                return new SamediFragment();
            case 6:
                //return null;
                return new DimancheGHFragment();

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
        switch (position) {
            case 0:
                return "LUNDI";
            case 1:
                return "MARDI";
            case 2:
                return "MERCREDI";
            case 3:
                return "JEUDI";
            case 4:
                return "VENDREDI";
            case 5:
                return "SAMEDI";
            case 6:
                return "DIMANCHE";
            default:
                return null;
        }

    }
}
