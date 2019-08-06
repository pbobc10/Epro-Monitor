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
import android.text.TextUtils;
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
import com.example.cbpierre.epromonitor.UserSessionPreferences;
import com.example.cbpierre.epromonitor.adapters.EtablissementAdapter;
import com.example.cbpierre.epromonitor.adapters.EtabsSpinnerAdapter;
import com.example.cbpierre.epromonitor.adapters.ZoneSpinnerAdapter;
import com.example.cbpierre.epromonitor.models.CompleteContact;
import com.example.cbpierre.epromonitor.models.Etablissement;
import com.example.cbpierre.epromonitor.models.NewContactETab;
import com.example.cbpierre.epromonitor.models.Zone;
import com.example.cbpierre.epromonitor.viewModels.EtablissementViewModel;
import com.example.cbpierre.epromonitor.viewModels.NewContactEtabViewModel;
import com.example.cbpierre.epromonitor.viewModels.SharedViewModel;
import com.example.cbpierre.epromonitor.viewModels.ZoneViewModel;

import java.text.SimpleDateFormat;
import java.util.Date;
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
    private NewContactEtabViewModel contactEtabViewModel;
    private SharedViewModel sharedViewModel;

    private EtabsSpinnerAdapter etabsSpinnerAdapter;

    private Zone zone;
    private Etablissement etablissement;
    private CompleteContact contact;

    private UserSessionPreferences userSessionPreferences;
    private String creeLe, creePar, transfereLe, transferePar, nom, adresse;

    public EtabsRegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        zoneViewModel = ViewModelProviders.of(this).get(ZoneViewModel.class);
        etablissementViewModel = ViewModelProviders.of(this).get(EtablissementViewModel.class);
        contactEtabViewModel = ViewModelProviders.of(this).get(NewContactEtabViewModel.class);
        sharedViewModel = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);
        //SharePreference
        userSessionPreferences = new UserSessionPreferences(getContext());
        creePar = transferePar = userSessionPreferences.getUserDetails();

        //Date
        String parttern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(parttern);
        creeLe = transfereLe = simpleDateFormat.format(new Date());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //show back Arrow
        showBackButton();
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

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertNewEtab();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        // contact Id
        sharedViewModel.getContactMutableLiveData().observe(this, new Observer<CompleteContact>() {
            @Override
            public void onChanged(@Nullable CompleteContact completeContact) {
                if (completeContact != null) {
                    contact = completeContact;
                }
            }
        });

        //max new etablissement id
        etablissementViewModel.getMaxEtab().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                if (contact.getConId() != null)
                    contactEtabViewModel.insert(new NewContactETab(contact.getConId(), null, integer));
                else
                    contactEtabViewModel.insert(new NewContactETab(contact.getContactId(), null, integer));
            }
        });
    }

    public void spinnerObservers() {
        //zone view model
        zoneViewModel.getAllZone().observe(this, new Observer<List<Zone>>() {
            @Override
            public void onChanged(@Nullable List<Zone> zones) {
                zoneSpinnerAdapter = new ZoneSpinnerAdapter(getContext(), R.layout.spinner_item, zones);
                zoneSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spLocalite.setAdapter(zoneSpinnerAdapter);
                spLocalite.setPrompt("LOCALITE :");
            }
        });

        // etablissement view model
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

        // Localite spinner
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
                    zone = (Zone) adapterView.getItemAtPosition(position);
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

        // Etablissement spinner
        spEtab.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                if (view == null)
                    return;
                if (adapterView.getItemAtPosition(position).equals("--SELECTIONNER UN ETABLISSEMENT--")) {
                    //change default item color
                    TextView textView = (TextView) view;
                    textView.setTextColor(getResources().getColor(android.R.color.darker_gray));
                } else {
                    //on selected a spinner item
                    etablissement = (Etablissement) adapterView.getItemAtPosition(position);
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

    /**
     * insert etablissement info
     */
    public void insertNewEtab() {
        //check if it's in the list
        if (etablissement.getNomEtablissement().equals("N'EST PAS DANS LA LISTE")) {
            initialize();
            //check for empty views
            if (isValidate()) {
                etablissementViewModel.insertEtablissement(new Etablissement(null, nom, zone.getZoneHtId(), adresse, null, null, 0, false, null, null, creePar, creeLe, transferePar, transfereLe, true));
                etablissementViewModel.finMaxEtab();
                Toast.makeText(getContext(), "enregistrement réussi!", Toast.LENGTH_SHORT).show();
                getActivity().onBackPressed();
            } else {
                Toast.makeText(getContext(), "l'enregistrement échoué!", Toast.LENGTH_LONG).show();
            }

        } // its in the list
        else {
            if (contact.getConId() != null) {
                contactEtabViewModel.insert(new NewContactETab(contact.getConId(), etablissement.getEtId(), null));
                Toast.makeText(getContext(), "enregistrement réussi!", Toast.LENGTH_SHORT).show();
                getActivity().onBackPressed();
            } else {
                contactEtabViewModel.insert(new NewContactETab(contact.getContactId(), etablissement.getEtId(), null));
                Toast.makeText(getContext(), "enregistrement réussi!", Toast.LENGTH_SHORT).show();
                getActivity().onBackPressed();
            }
        }
    }

    public void initialize() {
        nom = etNom.getText().toString().trim();
        adresse = etAdresse.getText().toString().trim();
    }

    public boolean isValidate() {
        boolean valid = true;
        if (TextUtils.isEmpty(nom) || nom.length() < 3) {
            etNom.setError("Merci d'entrer un nom valide ...");
            valid = false;
        } else if (TextUtils.isEmpty(adresse) || adresse.length() < 4) {
            etAdresse.setError("Merci d'entrer une adresse valide ...");
            valid = false;
        }
        return valid;
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
