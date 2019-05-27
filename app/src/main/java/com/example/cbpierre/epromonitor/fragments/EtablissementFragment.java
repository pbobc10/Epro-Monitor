package com.example.cbpierre.epromonitor.fragments;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cbpierre.epromonitor.R;
import com.example.cbpierre.epromonitor.adapters.CompleteEtablissementAdapter;
import com.example.cbpierre.epromonitor.adapters.EtablissementAdapter;
import com.example.cbpierre.epromonitor.models.CompleteEtablissement;
import com.example.cbpierre.epromonitor.models.Etablissement;
import com.example.cbpierre.epromonitor.viewModels.EtablissementViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class EtablissementFragment extends Fragment {
    private RecyclerView recyclerView;
    private CompleteEtablissementAdapter etablissementAdapter;
    private EtablissementViewModel etablissementViewModel;

    public EtablissementFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        etablissementViewModel = ViewModelProviders.of(this).get(EtablissementViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_etablissement, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        etablissementAdapter = new CompleteEtablissementAdapter(this.getContext());
        recyclerView = view.findViewById(R.id.rvEtablissements);
        recyclerView.setAdapter(etablissementAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        etablissementViewModel.getAllCompleteEtablissement().observe(this, new Observer<List<CompleteEtablissement>>() {
            @Override
            public void onChanged(@Nullable List<CompleteEtablissement> etablissements) {
                etablissementAdapter.setEtablissement(etablissements);
            }
        });
    }
}
