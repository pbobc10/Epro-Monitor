package com.example.cbpierre.epromonitor.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.cbpierre.epromonitor.R;
import com.example.cbpierre.epromonitor.adapters.JoinContactPaContactAdapter;
import com.example.cbpierre.epromonitor.models.JoinContactPaContact;
import com.example.cbpierre.epromonitor.viewModels.ContactViewModel;
import com.example.cbpierre.epromonitor.viewModels.ShareProduitContactViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PaContactFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PaContactFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PaContactFragment extends Fragment {
    private RecyclerView rvPaContact;
    private ContactViewModel contactViewModel;
    private JoinContactPaContactAdapter paContactAdapter;
    private ShareProduitContactViewModel shareProduitContactViewModel;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public PaContactFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PaContactFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PaContactFragment newInstance(String param1, String param2) {
        PaContactFragment fragment = new PaContactFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        //ViewModelProviders
        contactViewModel = ViewModelProviders.of(this).get(ContactViewModel.class);
        shareProduitContactViewModel = ViewModelProviders.of(getActivity()).get(ShareProduitContactViewModel.class);
        //tell the activity that the fragment want to have menu
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //show back arrow
        showBackButton();
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pa_contact, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvPaContact = view.findViewById(R.id.rvPaContact);
        // Create adapter passing in the sample user data
        paContactAdapter = new JoinContactPaContactAdapter(getContext());
        Log.d("pa_contact", "test1");
        // set PA Contact by nom
        contactViewModel.setPaContactByNom(null);
        // Initialize PaContacts
        contactViewModel.getAllContactPA().observe(this, new Observer<List<JoinContactPaContact>>() {
            @Override
            public void onChanged(@Nullable List<JoinContactPaContact> joinContactPaContacts) {
                Log.d("pa_contact", "" + joinContactPaContacts);
                paContactAdapter.setJoinContactPaContacts(joinContactPaContacts);
            }
        });
        // Attach the adapter to the recyclerview to populate items
        rvPaContact.setAdapter(paContactAdapter);
        // Set layout manager to position the items
        rvPaContact.setLayoutManager(new LinearLayoutManager(getContext()));
        Log.d("pa_contact", "test2");
        paContactAdapter.setPaClickLIstener(new JoinContactPaContactAdapter.OnContactPaClickLIstener() {
            @Override
            public void onClickPaContact(JoinContactPaContact paContact) {
                shareProduitContactViewModel.setProduitContact(paContact);
                ProduitFragment produitFragment = new ProduitFragment();
                replaceFragment(produitFragment);
            }
        });
        //call onBackPress/ back arrow
        backArrow();
        Log.d("pa_contact", "test3");
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_pa, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setQueryHint("Recherche par Nom,Ratio,Spécialité");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                // perform query here
                contactViewModel.setPaContactByNom(s);
                // Reset SearchView
                searchView.clearFocus();
                // searchView.setIconified(true);
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
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                // perform query here
                contactViewModel.setPaContactByNom(null);
                return true;
            }
        });
    }

    /**
     * menu
     */


    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.flContent, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
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
