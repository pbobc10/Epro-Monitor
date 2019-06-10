package com.example.cbpierre.epromonitor.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cbpierre.epromonitor.R;
import com.example.cbpierre.epromonitor.models.CompleteEtablissement;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EtabsDetailFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class EtabsDetailFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private CompleteEtablissement etablissement;
    private TextView nom, adresse, localite, statut, commune, departement, modifiePar, modifieLe;

    public EtabsDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get back arguments
        etablissement = (CompleteEtablissement) getArguments().getSerializable("COMPLETE_ETAB");
        //
        showBackButton();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_etabs_detail, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        nom = view.findViewById(R.id.txtEtabNom);
        adresse = view.findViewById(R.id.txtEtabAdresse);
        localite = view.findViewById(R.id.txtEtabLocalite);
        statut = view.findViewById(R.id.txtEtabStatut);
        commune = view.findViewById(R.id.txtEtabCommune);
        departement = view.findViewById(R.id.txtEtabDepartement);
        modifiePar = view.findViewById(R.id.txtEtabModifiePar);
        modifieLe = view.findViewById(R.id.txtEtabDate);
        //populate etab
        populate(etablissement);

        /////
        backArrow();
    }

    public void populate(CompleteEtablissement etablissement) {
        nom.setText(etablissement.getNom_Etablissement());
        adresse.setText(etablissement.getAdresse());
        localite.setText(etablissement.getLocalite());
        statut.setText(testStatut(etablissement.getStatut()));
        commune.setText(etablissement.getCommune());
        departement.setText(etablissement.getDepartement());
        modifiePar.setText(etablissement.getModifie_par());
        modifieLe.setText(etablissement.getModifie_le());
    }

    public String testStatut(int i) {
        if (i == 0)
            return "INACTIF";
        else
            return "ACTIF";
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
     * backArrow
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
     * Changes the icon of the drawer to menu
     */
    public void showDrawerButton() {
        if (getActivity() instanceof AppCompatActivity) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(false);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
        // mActionBarDrawerToggle.syncState();
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
