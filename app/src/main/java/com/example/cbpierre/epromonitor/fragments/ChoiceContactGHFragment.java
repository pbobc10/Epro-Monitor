package com.example.cbpierre.epromonitor.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.cbpierre.epromonitor.R;
import com.example.cbpierre.epromonitor.adapters.ChoiceContactGHAdapter;
import com.example.cbpierre.epromonitor.adapters.CommuneGHAdapter;
import com.example.cbpierre.epromonitor.adapters.LocaliteGHAdapter;
import com.example.cbpierre.epromonitor.adapters.SpecialiteGHAdapter;
import com.example.cbpierre.epromonitor.models.ChoiceContactGH;
import com.example.cbpierre.epromonitor.models.CommuneGH;
import com.example.cbpierre.epromonitor.models.LocaliteGH;
import com.example.cbpierre.epromonitor.models.SpecialiteGH;
import com.example.cbpierre.epromonitor.viewModels.CommuneLocaliteContactViewModel;
import com.example.cbpierre.epromonitor.viewModels.ContactVisiteViewModel;
import com.example.cbpierre.epromonitor.viewModels.SpecialiteViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ChoiceContactGHFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class ChoiceContactGHFragment extends Fragment {
    private RecyclerView rvChoiceContactGH;
    private Spinner spSpecialite, spCommune, spLocalite;

    private ChoiceContactGHAdapter choiceContactGHAdapter;
    private SpecialiteGHAdapter specialiteGHAdapter;
    private CommuneGHAdapter communeGHAdapter;
    private LocaliteGHAdapter localiteGHAdapter;

    private ContactVisiteViewModel contactVisiteViewModel;
    private SpecialiteViewModel specialiteViewModel;
    private CommuneLocaliteContactViewModel communeLocaliteContactViewModel;

    private SpecialiteGH specialiteGH;
    private CommuneGH communeGH;

    private OnFragmentInteractionListener mListener;

    public ChoiceContactGHFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contactVisiteViewModel = ViewModelProviders.of(this).get(ContactVisiteViewModel.class);
        specialiteViewModel = ViewModelProviders.of(this).get(SpecialiteViewModel.class);
        communeLocaliteContactViewModel = ViewModelProviders.of(this).get(CommuneLocaliteContactViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_choice_contact_gh, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvChoiceContactGH = view.findViewById(R.id.rvChoiceContactGH);
        spSpecialite = view.findViewById(R.id.spSpecialiteGH);
        spCommune = view.findViewById(R.id.spCommuneGH);
        spLocalite = view.findViewById(R.id.spLocaliteGH);

        spCommune.setVisibility(View.GONE);
        spLocalite.setVisibility(View.GONE);

        choiceContactGHAdapter = new ChoiceContactGHAdapter(getContext());
        rvChoiceContactGH.setLayoutManager(new LinearLayoutManager(getContext()));
        //populate Spinner
        populateSpinner();
        contactVisiteViewModel.getChoiceContactGH().observe(this, new Observer<List<ChoiceContactGH>>() {
            @Override
            public void onChanged(@Nullable List<ChoiceContactGH> choiceContactGHS) {
                choiceContactGHAdapter.setChoiceContactGHS(choiceContactGHS);
            }
        });
        rvChoiceContactGH.setAdapter(choiceContactGHAdapter);
        //get Spinner Item
        getSpinnerItem();
    }

    public void populateSpinner() {
        // spinner SpecialiteGH
        specialiteViewModel.getAllSpecialiteGH().observe(this, new Observer<List<SpecialiteGH>>() {
            @Override
            public void onChanged(@Nullable List<SpecialiteGH> specialiteGHS) {
                //add first element in the spinner
                if (!specialiteGHS.contains(new SpecialiteGH("--- SELECTIONNER UNE SPECIALITE ---", "AAA")))
                    specialiteGHS.add(0, new SpecialiteGH("--- SELECTIONNER UNE SPECIALITE ---", "AAA"));
                SpecialiteGHAdapter specialiteGHAdapter = new SpecialiteGHAdapter(getContext(), R.layout.spinner_rows, specialiteGHS);
                specialiteGHAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spSpecialite.setAdapter(specialiteGHAdapter);
            }
        });
        // spinner CommuneGh
        communeLocaliteContactViewModel.getAllCommuneGH().observe(this, new Observer<List<CommuneGH>>() {
            @Override
            public void onChanged(@Nullable List<CommuneGH> communeGHList) {
                if (!communeGHList.contains(new CommuneGH("--- SELECTIONNER UNE COMMUNE ---")))
                    communeGHList.add(0, new CommuneGH("--- SELECTIONNER UNE COMMUNE ---"));
                CommuneGHAdapter communeGHAdapter = new CommuneGHAdapter(getContext(), R.layout.spinner_rows, communeGHList);
                communeGHAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spCommune.setAdapter(communeGHAdapter);
            }
        });

        // spinner LocaliteGH
        communeLocaliteContactViewModel.getAllLocaliteGH().observe(this, new Observer<List<LocaliteGH>>() {
            @Override
            public void onChanged(@Nullable List<LocaliteGH> localiteGHList) {
                if (!localiteGHList.contains(new LocaliteGH("--- SELECTIONNER UNE LOCALITE ---")))
                    localiteGHList.add(0, new LocaliteGH("--- SELECTIONNER UNE LOCALITE ---"));
                LocaliteGHAdapter localiteGHAdapter = new LocaliteGHAdapter(getContext(), R.layout.spinner_rows, localiteGHList);
                localiteGHAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spLocalite.setAdapter(localiteGHAdapter);
            }
        });
    }

    public void getSpinnerItem() {
        spSpecialite.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                specialiteGH = (SpecialiteGH) parent.getItemAtPosition(position);

                if (specialiteGH.getNomSpecialite().equals("--- SELECTIONNER UNE SPECIALITE ---")) {
                    TextView textView = (TextView) view;
                    textView.setTextColor(Color.GRAY);
                } else {
                    contactVisiteViewModel.setContactGhParam(specialiteGH.getSpId(), null, null);
                    spCommune.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //spCommune
        spCommune.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                communeGH = (CommuneGH) parent.getItemAtPosition(position);
                if (communeGH.getCommune().equals("--- SELECTIONNER UNE COMMUNE ---")) {
                    TextView textView = (TextView) view;
                    textView.setTextColor(Color.GRAY);
                } else {
                    contactVisiteViewModel.setContactGhParam(specialiteGH.getSpId(), communeGH.getCommune(), null);
                    communeLocaliteContactViewModel.setLocaliteGHMutable(communeGH.getCommune());
                    spLocalite.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //spLocalite
        spLocalite.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                LocaliteGH localiteGH = (LocaliteGH) parent.getItemAtPosition(position);
                if (localiteGH.getLocalite().equals("--- SELECTIONNER UNE LOCALITE ---")) {
                    TextView textView = (TextView) view;
                    textView.setTextColor(Color.GRAY);
                } else {
                    contactVisiteViewModel.setContactGhParam(specialiteGH.getSpId(), communeGH.getCommune(), localiteGH.getLocalite());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
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
