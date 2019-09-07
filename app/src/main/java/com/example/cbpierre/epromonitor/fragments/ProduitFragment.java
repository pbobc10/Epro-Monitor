package com.example.cbpierre.epromonitor.fragments;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cbpierre.epromonitor.R;
import com.example.cbpierre.epromonitor.adapters.ProduitContactAdapter;
import com.example.cbpierre.epromonitor.models.Produit;
import com.example.cbpierre.epromonitor.viewModels.PaContactProduitViewModel;
import com.example.cbpierre.epromonitor.viewModels.ShareProduitContactViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProduitFragment extends Fragment {
    private RecyclerView rvProduitContact;
    private ProduitContactAdapter produitContactAdapter;
    private PaContactProduitViewModel paContactProduitViewModel;
    private ShareProduitContactViewModel shareProduitContactViewModel;

    public ProduitFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        shareProduitContactViewModel = ViewModelProviders.of(getActivity()).get(ShareProduitContactViewModel.class);
        paContactProduitViewModel = ViewModelProviders.of(this).get(PaContactProduitViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //show back arrow
        showBackButton();
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_produit, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvProduitContact = view.findViewById(R.id.rvProduitContact);
        produitContactAdapter = new ProduitContactAdapter(getContext());
        rvProduitContact.setAdapter(produitContactAdapter);

        shareProduitContactViewModel.getProduitContact().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                paContactProduitViewModel.setProduitByContactId(integer);
            }
        });
        //paContactProduitViewModel.setProduitByContactId(686);


        paContactProduitViewModel.getProduitByContactId().observe(this, new Observer<List<Produit>>() {
            @Override
            public void onChanged(@Nullable List<Produit> produits) {
                produitContactAdapter.setProduits(produits);
            }
        });
        rvProduitContact.setLayoutManager(new LinearLayoutManager(getContext()));

        //call back Arrow
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
