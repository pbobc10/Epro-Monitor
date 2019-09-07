package com.example.cbpierre.epromonitor.fragments;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.cbpierre.epromonitor.R;
import com.example.cbpierre.epromonitor.adapters.JoinContactEtablissementAdapter;
import com.example.cbpierre.epromonitor.models.CompleteContact;
import com.example.cbpierre.epromonitor.models.JoinContactEtablissementData;
import com.example.cbpierre.epromonitor.viewModels.ContactEtablissementViewModel;
import com.example.cbpierre.epromonitor.viewModels.EtablissementViewModel;
import com.example.cbpierre.epromonitor.viewModels.NewContactEtabViewModel;
import com.example.cbpierre.epromonitor.viewModels.SharedViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactEtabsFragment extends Fragment {
    private RecyclerView recyclerView;
    private ContactEtablissementViewModel contactEtablissementViewModel;
    private NewContactEtabViewModel newContactEtabViewModel;
    private EtablissementViewModel etablissementViewModel;
    private SharedViewModel sharedViewModel;
    private JoinContactEtablissementAdapter etablissementAdapter;
    private FloatingActionButton fabButton;
    private int newContactId;
    public static Integer id;

    public ContactEtabsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contactEtablissementViewModel = ViewModelProviders.of(this).get(ContactEtablissementViewModel.class);
        newContactEtabViewModel = ViewModelProviders.of(this).get(NewContactEtabViewModel.class);
        etablissementViewModel = ViewModelProviders.of(this).get(EtablissementViewModel.class);
        sharedViewModel = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);
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

        sharedViewModel.getContactMutableLiveData().observe(this, new Observer<CompleteContact>() {
            @Override
            public void onChanged(@Nullable CompleteContact completeContact) {
                if (completeContact != null) {
                    if (completeContact.getConId() != null) {
                        contactEtablissementViewModel.setEtabsByContactId(completeContact.getConId());
                        contactEtablissementViewModel.getEtabsByContactId().observe(getViewLifecycleOwner(), new Observer<List<JoinContactEtablissementData>>() {
                            @Override
                            public void onChanged(@Nullable List<JoinContactEtablissementData> joinContactEtablissementData) {
                                etablissementAdapter.setEtablissement(joinContactEtablissementData);
                            }
                        });
                    } else {
                        newContactId = completeContact.getContactId();
                        contactEtablissementViewModel.setNewEtabsByContactId(completeContact.getContactId());
                        contactEtablissementViewModel.getNewEtabsByContactId().observe(getViewLifecycleOwner(), new Observer<List<JoinContactEtablissementData>>() {
                            @Override
                            public void onChanged(@Nullable List<JoinContactEtablissementData> joinContactEtablissementData) {
                                etablissementAdapter.setEtablissement(joinContactEtablissementData);
                            }
                        });
                    }
                }
            }
        });
        ///
        etablissementAdapter.setOnEtablissementClickListener(new JoinContactEtablissementAdapter.OnEtablissementListener() {
            @Override
            public void onEtablissementClick(List<JoinContactEtablissementData> _etablissement, int position) {
                JoinContactEtablissementData joinContactEtablissementData = _etablissement.get(position);
                if (joinContactEtablissementData.getEtId() != null) {
                    newContactEtabViewModel.deleteNewContactEtabById(newContactId, joinContactEtablissementData.getEtId());
                    Toast.makeText(getContext(), "Adresse supprimée avec succès!", Toast.LENGTH_SHORT).show();
                } else if (joinContactEtablissementData.getEtabId() != null) {
                    newContactEtabViewModel.deleteNewContactNewEtabById(newContactId, joinContactEtablissementData.getEtabId());
                    etablissementViewModel.deleteNewEtabById(joinContactEtablissementData.getEtabId());
                    Toast.makeText(getContext(), "Adresse supprimée avec succès!", Toast.LENGTH_SHORT).show();
                }

            }
        });
        ///

        //fab
        fabButton = view.findViewById(R.id.fabEtabs);
        fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new EtabsRegisterFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.flContent, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }

}
