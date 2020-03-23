package com.example.cbpierre.epromonitor.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.cbpierre.epromonitor.R;
import com.example.cbpierre.epromonitor.adapters.CompleteContactAdapter;
import com.example.cbpierre.epromonitor.models.CompleteContact;
import com.example.cbpierre.epromonitor.viewModels.ContactViewModel;
import com.example.cbpierre.epromonitor.viewModels.SharedViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ContactFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ContactFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContactFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private ContactViewModel contactViewModel;
    private SharedViewModel sharedViewModel;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;
    private FloatingActionButton fabButton;

    public ContactFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ContactFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ContactFragment newInstance(String param1, String param2) {
        ContactFragment fragment = new ContactFragment();
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
        // ViewModelProviders
        contactViewModel = ViewModelProviders.of(this).get(ContactViewModel.class);
        sharedViewModel = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //menu
        setHasOptionsMenu(true);

        RecyclerView contactRecyclerView = view.findViewById(R.id.rvContacts);
        final CompleteContactAdapter completeContactAdapter = new CompleteContactAdapter(this.getContext());
        contactRecyclerView.setAdapter(completeContactAdapter);
        contactRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        /*contactViewModel.getCompleteContact().observe(this, new Observer<List<CompleteContact>>() {
            @Override
            public void onChanged(@Nullable List<CompleteContact> completeContacts) {
                completeContactAdapter.setContact(completeContacts);
            }
        });*/

        // perform query here
        contactViewModel.setContactByNom(null);
        contactViewModel.getCompleteContact().observe(this, new Observer<List<CompleteContact>>() {
            @Override
            public void onChanged(@Nullable List<CompleteContact> completeContacts) {
                completeContactAdapter.setContact(completeContacts);
            }
        });

        completeContactAdapter.setOnContactClickListener(new CompleteContactAdapter.OnContactClickListener() {
            @Override
            public void onContactClick(List<CompleteContact> _completeContact, int position) {
                sharedViewModel.setContactMutableLiveData(_completeContact.get(position));
                ViewPagerFragment fragment = new ViewPagerFragment();
                replaceFragment(fragment);
                /*Log.d("contact", _completeContact.get(position).getNom() + " " + _completeContact.get(position).getModifie_par()
                        + " " + _completeContact.get(position).getModifie_le() + " " + _completeContact.get(position).getStatut());*/
            }
        });

        //fab
        fabButton = view.findViewById(R.id.fabContact);
        fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new ContactRegisterFragment();
                replaceFragment(fragment);
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_menu_contact, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setQueryHint("Search by nom, prénom, spécialité");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                // perform query here
                contactViewModel.setContactByNom(s);
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
                contactViewModel.setContactByNom(null);
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()) {
            case R.id.insert_contact_menu:
                fragment = new ContactRegisterFragment();
                replaceFragment(fragment);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

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

}
