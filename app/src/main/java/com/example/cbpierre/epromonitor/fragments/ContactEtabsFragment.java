package com.example.cbpierre.epromonitor.fragments;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.cbpierre.epromonitor.R;
import com.example.cbpierre.epromonitor.adapters.EtablissementAdapter;
import com.example.cbpierre.epromonitor.adapters.JoinContactEtablissementAdapter;
import com.example.cbpierre.epromonitor.models.JoinContactEtablissementData;
import com.example.cbpierre.epromonitor.repositories.ContactEtablissementRepository;
import com.example.cbpierre.epromonitor.viewModels.ContactEtablissementViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactEtabsFragment extends Fragment {
    private RecyclerView recyclerView;
    private ContactEtablissementViewModel contactEtablissementViewModel;
    private JoinContactEtablissementAdapter etablissementAdapter;
    public static int id;

    public ContactEtabsFragment() {
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
        return inflater.inflate(R.layout.fragment_contact_etabs, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rvEtabs);
        etablissementAdapter = new JoinContactEtablissementAdapter(getContext());
        recyclerView.setAdapter(etablissementAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        contactEtablissementViewModel.findContactEtablissement(id);
        contactEtablissementViewModel.getSearchEtabsByContactId().observe(this, new Observer<List<JoinContactEtablissementData>>() {
            @Override
            public void onChanged(@Nullable List<JoinContactEtablissementData> joinContactEtablissementData) {
                etablissementAdapter.setEtablissement(joinContactEtablissementData);
            }
        });
    }

}
