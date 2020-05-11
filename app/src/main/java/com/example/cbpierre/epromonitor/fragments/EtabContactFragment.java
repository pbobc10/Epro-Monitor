package com.example.cbpierre.epromonitor.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cbpierre.epromonitor.R;
import com.example.cbpierre.epromonitor.adapters.EtabContactAdapter;
import com.example.cbpierre.epromonitor.models.CompleteEtablissement;
import com.example.cbpierre.epromonitor.models.EtabContact;
import com.example.cbpierre.epromonitor.viewModels.ContactViewModel;
import com.example.cbpierre.epromonitor.viewModels.ShareEtabDetailViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class EtabContactFragment extends Fragment {
    private RecyclerView recyclerView;
    private EtabContactAdapter etabContactAdapter;
    private ContactViewModel contactViewModel;
    private ShareEtabDetailViewModel shareEtabDetailViewModel;

    public EtabContactFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contactViewModel = ViewModelProviders.of(this).get(ContactViewModel.class);
        shareEtabDetailViewModel = ViewModelProviders.of(getActivity()).get(ShareEtabDetailViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_etab_contact, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rvEtabContact);
        etabContactAdapter = new EtabContactAdapter(getContext());
        recyclerView.setAdapter(etabContactAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this.getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        shareEtabDetailViewModel.getEtabMutableLiveData().observe(this, new Observer<CompleteEtablissement>() {
            @Override
            public void onChanged(@Nullable CompleteEtablissement completeEtablissement) {
                contactViewModel.setEtabContactbyId(Integer.parseInt(completeEtablissement.getEtId()));
                //contactViewModel.setEtabContactbyId(100);
            }
        });

        contactViewModel.getAllEtabContact().observe(this, new Observer<List<EtabContact>>() {
            @Override
            public void onChanged(@Nullable List<EtabContact> etabContacts) {
                etabContactAdapter.setEtabContactList(etabContacts);
            }
        });
    }
}
