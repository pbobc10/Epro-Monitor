package com.example.cbpierre.epromonitor.adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.cbpierre.epromonitor.fragments.EtabContactFragment;
import com.example.cbpierre.epromonitor.fragments.EtabsDetailFragment;

public class EtablissementPagerAdapter extends FragmentPagerAdapter {
    private final int numItem = 2;

    public EtablissementPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    // Returns the fragment to display for that page
    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                return new EtabsDetailFragment();
            case 1:
                return new EtabContactFragment();
            default:
                return null;
        }

    }

    // Returns total number of pages
    @Override
    public int getCount() {
        return numItem;
    }

    // Returns the page title for the top indicator
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        super.getPageTitle(position);
        if (position == 0)
            return "Etablissement";
        else
            return "Contact";
    }
}
