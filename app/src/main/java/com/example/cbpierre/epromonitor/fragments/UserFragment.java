package com.example.cbpierre.epromonitor.fragments;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.cbpierre.epromonitor.R;
import com.example.cbpierre.epromonitor.adapters.LoginAdapter;
import com.example.cbpierre.epromonitor.models.Login;
import com.example.cbpierre.epromonitor.viewModels.LoginViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends Fragment {
    private LoginViewModel loginViewModel;

    public UserFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.rvUsers);
        setHasOptionsMenu(true);
        final LoginAdapter loginAdapter = new LoginAdapter(this.getContext());
        recyclerView.setAdapter(loginAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        loginViewModel.getAllUsers().observe(this, new Observer<List<Login>>() {
            @Override
            public void onChanged(@Nullable List<Login> logins) {
                loginAdapter.setUser(logins);
            }
        });

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_menu_user, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()) {
            case R.id.insert_user_menu:
                fragment = new SignInFragment();
                replaceFragment(fragment);
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager =getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.flContent, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
