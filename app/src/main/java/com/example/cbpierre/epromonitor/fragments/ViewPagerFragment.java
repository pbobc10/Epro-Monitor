package com.example.cbpierre.epromonitor.fragments;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.design.widget.TabLayout;
import android.widget.Toast;


import com.example.cbpierre.epromonitor.R;
import com.example.cbpierre.epromonitor.adapters.MyPagerAdapter;
import com.example.cbpierre.epromonitor.viewModels.ContactEtablissementViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class ViewPagerFragment extends Fragment {
    private ViewPager viewPager;
    private MyPagerAdapter myPagerAdapter;
    private TabLayout tabLayout;
    private ContactEtablissementViewModel contactEtablissementViewModel;


    public ViewPagerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contactEtablissementViewModel = ViewModelProviders.of(this).get(ContactEtablissementViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_pager, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewPager = view.findViewById(R.id.vpPager);
        myPagerAdapter = new MyPagerAdapter(this.getChildFragmentManager());
        viewPager.setAdapter(myPagerAdapter);
        tabLayout = view.findViewById(R.id.tlTabs);
        tabLayout.setupWithViewPager(viewPager);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                switch (i) {
                    case 0:
                        Toast.makeText(getContext(), "page : Contact", Toast.LENGTH_LONG).show();
                        break;
                    case 1:
                        Toast.makeText(getContext(), "page : Adresse", Toast.LENGTH_LONG).show();
                        break;

                }


            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }
}
