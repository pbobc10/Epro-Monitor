package com.example.cbpierre.epromonitor.fragments;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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
        etablissementViewModel.setCompleteEtabsByNom(null);

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
        //menu
        setHasOptionsMenu(true);

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


        etablissementAdapter.setOnCompleteEtablissementListener(new CompleteEtablissementAdapter.OnCompleteEtablissementListener() {
            @Override
            public void onContactClick(List<CompleteEtablissement> _completeEtablissement, int position) {
                EtabsDetailFragment fragment = new EtabsDetailFragment();
                CompleteEtablissement etablissement = _completeEtablissement.get(position);
                Bundle arg = new Bundle();
                arg.putSerializable("COMPLETE_ETAB", etablissement);
                fragment.setArguments(arg);
                replaceFragment(fragment);
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_menu_etablissement, menu);

        MenuItem searchItem = menu.findItem(R.id.action_etab_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setQueryHint("Search by Nom");
        searchView.setIconifiedByDefault(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                // perform query here
                etablissementViewModel.setCompleteEtabsByNom(s);
                //reset searchView
                searchView.clearFocus();
                //searchView.setIconified(true);
                //searchView.setIconifiedByDefault(false);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        searchItem.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                // Toast.makeText(getContext(), "menu Expand", Toast.LENGTH_LONG).show();
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                //Toast.makeText(getContext(), "menu collapse", Toast.LENGTH_LONG).show();
                // perform query here
                etablissementViewModel.setCompleteEtabsByNom(null);
                return true;
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
