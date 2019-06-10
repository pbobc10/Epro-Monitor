package com.example.cbpierre.epromonitor.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cbpierre.epromonitor.R;
import com.example.cbpierre.epromonitor.adapters.EtablissementAdapter;
import com.example.cbpierre.epromonitor.adapters.EtabsSpinnerAdapter;
import com.example.cbpierre.epromonitor.adapters.ZoneSpinnerAdapter;
import com.example.cbpierre.epromonitor.models.Etablissement;
import com.example.cbpierre.epromonitor.models.Zone;
import com.example.cbpierre.epromonitor.viewModels.EtablissementViewModel;
import com.example.cbpierre.epromonitor.viewModels.ZoneViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EtabsRegisterFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class EtabsRegisterFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private Spinner spLocalite, spEtab;
    private EditText etNom, etAdresse;
    private Button btnCancel, btnOk;
    private String zoneHtId, nomEtab;

    private ZoneSpinnerAdapter zoneSpinnerAdapter;

    private ZoneViewModel zoneViewModel;
    private EtablissementViewModel etablissementViewModel;
    private EtabsSpinnerAdapter etabsSpinnerAdapter;


    public EtabsRegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        zoneViewModel = ViewModelProviders.of(this).get(ZoneViewModel.class);
        etablissementViewModel = ViewModelProviders.of(this).get(EtablissementViewModel.class);

        //show back Arrow
        showBackButton();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_etabs_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        spLocalite = view.findViewById(R.id.spLocalite);
        spEtab = view.findViewById(R.id.spEtabs);
        etNom = view.findViewById(R.id.etNom);
        etAdresse = view.findViewById(R.id.etAdresse);
        btnCancel = view.findViewById(R.id.btnCancel);
        btnOk = view.findViewById(R.id.btnok);

        spEtab.setVisibility(View.GONE);
        etNom.setVisibility(View.GONE);
        etAdresse.setVisibility(View.GONE);
        btnOk.setVisibility(View.GONE);
        btnCancel.setVisibility(View.GONE);


        //spinner Listener
        getSpinnerListener(view);
        //etablissementViewModel.setEtabByLocalite("HT.ND.CH-1-HT1110-LOC");
        //spinner Observers
        spinnerObservers();

        //back Arrow
        backArrow();
    }

    public void spinnerObservers() {
        zoneViewModel.getAllZone().observe(this, new Observer<List<Zone>>() {
            @Override
            public void onChanged(@Nullable List<Zone> zones) {
                zoneSpinnerAdapter = new ZoneSpinnerAdapter(getContext(), R.layout.spinner_item, zones);
                zoneSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spLocalite.setAdapter(zoneSpinnerAdapter);
                spLocalite.setPrompt("LOCALITE :");

            }
        });

        etablissementViewModel.getEtabByLocalite().observe(this, new Observer<List<Etablissement>>() {
            @Override
            public void onChanged(@Nullable List<Etablissement> etablissements) {
                etabsSpinnerAdapter = new EtabsSpinnerAdapter(getContext(), R.layout.spinner_item, etablissements);
                etabsSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spEtab.setAdapter(etabsSpinnerAdapter);
                spEtab.setPrompt("ETABLISSEMENT :");
            }
        });

    }

    public void getSpinnerListener(@NonNull View view) {

        // Nature spinner
        spLocalite.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                if (view == null)
                    return;
                if (position == 0) {
                    //change default item color
                    TextView textView = (TextView) view;
                    textView.setTextColor(getResources().getColor(android.R.color.darker_gray));
                } else {
                    //on selected a spinner item
                    Zone zone = (Zone) adapterView.getItemAtPosition(position);
                    zoneHtId = zone.getZoneHtId();
                    spEtab.setVisibility(View.VISIBLE);
                    etablissementViewModel.setEtabByLocalite(zoneHtId);
                    // show selected spinner item
                    Toast.makeText(getContext(), "Selected: " + zoneHtId, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Nature spinner
        spEtab.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                if (view == null)
                    return;
                if (adapterView.getItemAtPosition(position).equals("SELECTIONNER UNE NATURE...")) {
                    //change default item color
                    TextView textView = (TextView) view;
                    textView.setTextColor(getResources().getColor(android.R.color.darker_gray));
                } else {
                    //on selected a spinner item
                    Etablissement etablissement = (Etablissement) adapterView.getItemAtPosition(position);
                    nomEtab = etablissement.getNomEtablissement();

                    etNom.setVisibility(View.VISIBLE);
                    etAdresse.setVisibility(View.VISIBLE);
                    btnOk.setVisibility(View.VISIBLE);
                    btnCancel.setVisibility(View.VISIBLE);

                    if (etablissement.getNomEtablissement().equals("N'EST PAS DANS LA LISTE")) {
                        etNom.setText(null);
                    } else {
                        etNom.setText(etablissement.getNomEtablissement());
                    }

                    etAdresse.setText(etablissement.getAdresse());
                    //etablissementViewModel.setEtabByLocalite(zoneHtId);
                    // show selected spinner item
                    Toast.makeText(getContext(), "Selected: " + nomEtab, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void insertNewEtab(String nomEtab, String zoneHtId, String adresse) {
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
     * backArrow
     */
    public void backArrow() {
        final Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
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
