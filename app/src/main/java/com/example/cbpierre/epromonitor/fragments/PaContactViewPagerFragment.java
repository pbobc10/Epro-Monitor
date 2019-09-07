package com.example.cbpierre.epromonitor.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.cbpierre.epromonitor.R;
import com.example.cbpierre.epromonitor.adapters.PaContactPagerAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class PaContactViewPagerFragment extends Fragment {
    private ViewPager vpPaContact;
    private TabLayout tabLayout;
    private PaContactPagerAdapter paContactPagerAdapter;

    public PaContactViewPagerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //enable back arrow
        showBackButton();
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pa_contact_view_pager, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        vpPaContact = view.findViewById(R.id.vpPaContact);
        tabLayout = view.findViewById(R.id.tlPaTabs);
        paContactPagerAdapter = new PaContactPagerAdapter(this.getChildFragmentManager());
        tabLayout.setupWithViewPager(vpPaContact);
        vpPaContact.setAdapter(paContactPagerAdapter);

        vpPaContact.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            // This method will be invoked when the current page is scrolled
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            // This method will be invoked when a new page becomes selected.
            @Override
            public void onPageSelected(int i) {
                switch (i) {
                    case 0:
                        Toast.makeText(getContext(), "Page: PA Contact", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(getContext(), "Page: Produit", Toast.LENGTH_SHORT).show();
                        break;

                }
            }

            // Called when the scroll state changes:
            // SCROLL_STATE_IDLE, SCROLL_STATE_DRAGGING, SCROLL_STATE_SETTLING
            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        //call back arrow
        backArrow();
    }

    /**
     * back arrow
     */
    public void backArrow() {
        final Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDrawerButton();
                getActivity().onBackPressed();
            }
        });
    }

    /**
     * Changes the icon of the drawer to back
     */
    public void showBackButton() {
        if (getActivity() instanceof AppCompatActivity) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    /**
     * changes the icon of the drawer to menu
     */
    public void showDrawerButton() {
        if (getActivity() instanceof AppCompatActivity) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
    }

}
