package com.example.cbpierre.epromonitor.fragments;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cbpierre.epromonitor.R;
import com.example.cbpierre.epromonitor.adapters.GHAdapter;
import com.example.cbpierre.epromonitor.models.GH;
import com.example.cbpierre.epromonitor.viewModels.GHViewModel;
import com.example.cbpierre.epromonitor.viewModels.ShareGHId;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class GHFragment extends Fragment {
    private RecyclerView rvGH;
    private GHAdapter ghAdapter;
    private GHViewModel ghViewModel;
    private ShareGHId shareGHId;

    public GHFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ghViewModel = ViewModelProviders.of(this).get(GHViewModel.class);
        shareGHId = ViewModelProviders.of(getActivity()).get(ShareGHId.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gh, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ghAdapter = new GHAdapter(getContext());
        rvGH = view.findViewById(R.id.rvGH);
        ghViewModel.getAllGH().observe(this, new Observer<List<GH>>() {
            @Override
            public void onChanged(@Nullable List<GH> ghs) {
                ghAdapter.setGhs(ghs);
            }
        });
        rvGH.setAdapter(ghAdapter);
        rvGH.setLayoutManager(new LinearLayoutManager(getContext()));

        ghAdapter.setListener(new GHAdapter.GHItemListener() {
            @Override
            public void onGHClick(int ghId) {
                shareGHId.setGhId(ghId);
                replaceFragment(new GHCourantViewPagerFragment());
            }
        });
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.flContent, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
