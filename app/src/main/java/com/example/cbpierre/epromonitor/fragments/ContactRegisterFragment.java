package com.example.cbpierre.epromonitor.fragments;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cbpierre.epromonitor.R;
import com.example.cbpierre.epromonitor.UserSessionPreferences;
import com.example.cbpierre.epromonitor.adapters.ForceSpinnerAdapter;
import com.example.cbpierre.epromonitor.adapters.NatureSpinnerAdapter;
import com.example.cbpierre.epromonitor.adapters.SecteurSpinnerAdapter;
import com.example.cbpierre.epromonitor.adapters.SpecialiteSpinnerAdapter;
import com.example.cbpierre.epromonitor.adapters.TitreSpinnerAdapter;
import com.example.cbpierre.epromonitor.adapters.ZoneSpinnerAdapter;
import com.example.cbpierre.epromonitor.models.Contact;
import com.example.cbpierre.epromonitor.models.Force;
import com.example.cbpierre.epromonitor.models.Nature;
import com.example.cbpierre.epromonitor.models.Secteur;
import com.example.cbpierre.epromonitor.models.Specialite;
import com.example.cbpierre.epromonitor.models.Titre;
import com.example.cbpierre.epromonitor.models.Zone;
import com.example.cbpierre.epromonitor.repositories.ContactRepository;
import com.example.cbpierre.epromonitor.viewModels.ContactViewModel;
import com.example.cbpierre.epromonitor.viewModels.ForceViewModel;
import com.example.cbpierre.epromonitor.viewModels.LoginViewModel;
import com.example.cbpierre.epromonitor.viewModels.NatureViewModel;
import com.example.cbpierre.epromonitor.viewModels.SecteurViewModel;
import com.example.cbpierre.epromonitor.viewModels.SpecialiteViewModel;
import com.example.cbpierre.epromonitor.viewModels.TitreViewModel;
import com.example.cbpierre.epromonitor.viewModels.ZoneViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactRegisterFragment extends Fragment {
    ContactRepository contactRepository;
    private Spinner spNature;
    private Spinner spTitre;
    private Spinner spSecteur;
    private Spinner spSpecialite;
    private Spinner spForce;
    private EditText mNom;
    private EditText mPrenom;
    private EditText mTel;
    private EditText mEmail;
    private Button button;
    private String natureId, titreId, secteurId, specialiteId, forceId, nom, prenom, email, tel;

    private ContactViewModel mContactViewModel;
    private ForceViewModel forceViewModel;
    private NatureViewModel natureViewModel;
    private SecteurViewModel secteurViewModel;
    private SpecialiteViewModel specialiteViewModel;
    private TitreViewModel titreViewModel;
    private ZoneViewModel zoneViewModel;

    private ForceSpinnerAdapter forceSpinnerAdapter;
    private NatureSpinnerAdapter natureSpinnerAdapter;
    private SecteurSpinnerAdapter secteurSpinnerAdapter;
    private SpecialiteSpinnerAdapter specialiteSpinnerAdapter;
    private TitreSpinnerAdapter titreSpinnerAdapter;

    private UserSessionPreferences userSessionPreferences;

    private String creeLe, creePar, modifieLe, modifiePar, transfereLe, transferePar;

    public ContactRegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // ViewModelProviders
        mContactViewModel = ViewModelProviders.of(this).get(ContactViewModel.class);
        forceViewModel = ViewModelProviders.of(this).get(ForceViewModel.class);
        natureViewModel = ViewModelProviders.of(this).get(NatureViewModel.class);
        secteurViewModel = ViewModelProviders.of(this).get(SecteurViewModel.class);
        specialiteViewModel = ViewModelProviders.of(this).get(SpecialiteViewModel.class);
        titreViewModel = ViewModelProviders.of(this).get(TitreViewModel.class);

        //SharePreference
        userSessionPreferences = new UserSessionPreferences(getContext());
        creePar = modifiePar = transferePar = userSessionPreferences.getUserDetails();

        //Date
        String parttern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(parttern);
        creeLe = modifieLe = transfereLe = simpleDateFormat.format(new Date());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //enable back arrow
        showBackButton();
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact_register, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Spinner
        spNature = view.findViewById(R.id.spNature);
        spTitre = view.findViewById(R.id.spTitre);
        spSecteur = view.findViewById(R.id.spSecteur);
        spSpecialite = view.findViewById(R.id.spSpecialite);
        spForce = view.findViewById(R.id.spForce);

        mNom = view.findViewById(R.id.etNom);
        mPrenom = view.findViewById(R.id.etPrenom);
        mEmail = view.findViewById(R.id.etEmail);
        mTel = view.findViewById(R.id.etPhone);
        //populate Spinner
        getSpinnerItem(view);

        button = view.findViewById(R.id.btnRegister);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contactRegister();
                // contactRepository.populateContact();
            }
        });

        //observers
        spinnerObservers();
        //call back arrow
        backArrow();
    }

    public void contactRegister() {
        initialize();
        if (isValidate()) {
            mContactViewModel.insertContact(new Contact(null, titreId, nom, prenom, natureId, secteurId, specialiteId, forceId, tel, null, null, email, 0, transferePar, transfereLe, creePar, creeLe, modifiePar, modifieLe, false, null, null, true));
            getActivity().getSupportFragmentManager().popBackStack(getActivity().getSupportFragmentManager().getBackStackEntryAt(0).getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);

        } else {
            Toast.makeText(getContext(), "l'enregistrement a échoué!!!", Toast.LENGTH_LONG).show();
        }
    }

    public void initialize() {
        nom = mNom.getText().toString().trim();
        prenom = mPrenom.getText().toString().trim();
        email = mEmail.getText().toString().trim();
        tel = mTel.getText().toString().trim();
    }

    public boolean isValidate() {
        boolean valid = true;
        if (TextUtils.isEmpty(natureId) || natureId == null) {
            TextView errorText = (TextView) spNature.getSelectedView();
            errorText.setError("");
            errorText.setTextColor(Color.RED);//just to highlight that this is an error
            errorText.setText("le fileur Nature est vide!");
            valid = false;
        } else if (TextUtils.isEmpty(titreId) || titreId == null) {
            TextView errorText = (TextView) spTitre.getSelectedView();
            errorText.setError("");
            errorText.setTextColor(Color.RED);//just to highlight that this is an error
            errorText.setText("le fileur Titre est vide!");
            valid = false;
        } else if (nom.isEmpty() || nom.length() < 3) {
            mNom.setError("Merci d'entrer un nom valide");
            valid = false;
        } else if (prenom.isEmpty() || prenom.length() < 3) {
            mPrenom.setError("Merci d'entrer un prenom valide");
            valid = false;
        } else if (TextUtils.isEmpty(secteurId) || secteurId == null) {
            TextView errorText = (TextView) spSecteur.getSelectedView();
            errorText.setError("");
            errorText.setTextColor(Color.RED);//just to highlight that this is an error
            errorText.setText("le fileur Secteur est vide!");
            valid = false;
        } else if (TextUtils.isEmpty(specialiteId) || specialiteId == null) {
            TextView errorText = (TextView) spSpecialite.getSelectedView();
            errorText.setError("");
            errorText.setTextColor(Color.RED);//just to highlight that this is an error
            errorText.setText("le fileur Specialite est vide!");
            valid = false;
        } else if (TextUtils.isEmpty(forceId) || forceId == null) {
            TextView errorText = (TextView) spForce.getSelectedView();
            errorText.setError("");
            errorText.setTextColor(Color.RED);//just to highlight that this is an error
            errorText.setText("le fileur Force est vide!");
            valid = false;
        } else if (tel.isEmpty() || !isValidePhoneNumber(tel)) {
            mTel.setError("Merci d'entrer un numero de telephone valide. ex: ###-####-####");
            valid = false;
        } else if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError("Merci d'entrer un email valide");
            valid = false;
        }
        return valid;
    }

    public boolean isValidePhoneNumber(String number) {
        String PHONE_NUMBER = "\\d{3}-\\d{4}-\\d{4}";
        Pattern pattern = Pattern.compile(PHONE_NUMBER);
        Matcher matcher = pattern.matcher(number);
        return matcher.matches();
    }

    public void spinnerObservers() {
        forceViewModel.getAllForce().observe(this, new Observer<List<Force>>() {
            @Override
            public void onChanged(@Nullable List<Force> forces) {
                forceSpinnerAdapter = new ForceSpinnerAdapter(getContext(), R.layout.spinner_item, forces);
                forceSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spForce.setAdapter(forceSpinnerAdapter);
            }
        });
        natureViewModel.getAllNature().observe(this, new Observer<List<Nature>>() {
            @Override
            public void onChanged(@Nullable List<Nature> natures) {
                natureSpinnerAdapter = new NatureSpinnerAdapter(getContext(), R.layout.spinner_item, natures);
                natureSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spNature.setAdapter(natureSpinnerAdapter);
            }
        });
        secteurViewModel.getsAllSecteur().observe(this, new Observer<List<Secteur>>() {
            @Override
            public void onChanged(@Nullable List<Secteur> secteurs) {
                secteurSpinnerAdapter = new SecteurSpinnerAdapter(getContext(), R.layout.spinner_item, secteurs);
                secteurSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spSecteur.setAdapter(secteurSpinnerAdapter);
            }
        });
        specialiteViewModel.getAllSpecialite().observe(this, new Observer<List<Specialite>>() {
            @Override
            public void onChanged(@Nullable List<Specialite> specialites) {
                specialiteSpinnerAdapter = new SpecialiteSpinnerAdapter(getContext(), R.layout.spinner_item, specialites);
                specialiteSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spSpecialite.setAdapter(specialiteSpinnerAdapter);
            }
        });
        titreViewModel.getmAllTitre().observe(this, new Observer<List<Titre>>() {
            @Override
            public void onChanged(@Nullable List<Titre> titres) {
                titreSpinnerAdapter = new TitreSpinnerAdapter(getContext(), R.layout.spinner_item, titres);
                titreSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spTitre.setAdapter(titreSpinnerAdapter);
            }
        });
    }

    /**
     * Spinner
     */

    public void getSpinnerItem(@NonNull View view) {

        // Nature spinner
        spNature.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if (view == null)
                    return;
                //Nature Object
                //on selected a spinner item
                Nature nature = (Nature) adapterView.getItemAtPosition(position);

                if (nature.getNomNature().equals("-- SELECTIONNER UNE NATURE --")) {
                    //change default item color
                    TextView textView = (TextView) view;
                    textView.setTextColor(getResources().getColor(android.R.color.darker_gray));
                } else {
                    natureId = nature.getNatId();
                    // show selected spinner item
                    //Toast.makeText(getContext(), "Selected: " + item1, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //Titre spinner
        spTitre.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if (view == null)
                    return;
                //Titre Object
                //on selected a spinner item
                Titre titre = (Titre) adapterView.getItemAtPosition(position);

                if (titre.getNomTitre().equals("-- SELECTIONNER UN TITRE --")) {
                    //change default item color
                    TextView textView = (TextView) view;
                    textView.setTextColor(getResources().getColor(R.color.input_login_hint));
                } else {
                    titreId = titre.getTid();
                    // show selected spinner item
                    // Toast.makeText(getContext(), "Selected: " + item2, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //Secteur spinner
        spSecteur.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if (view == null)
                    return;
                //Secteur Object
                //on selected a spinner item
                Secteur secteur = (Secteur) adapterView.getItemAtPosition(position);

                if (secteur.getNomSecteur().equals("-- SELECTIONNER UN SECTEUR --")) {
                    //change default item color
                    TextView textView = (TextView) view;
                    textView.setTextColor(getResources().getColor(R.color.input_login_hint));
                } else {
                    secteurId = secteur.getSecId();
                    // show selected spinner item
                    //Toast.makeText(getContext(), "Selected: " + item3, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //Specialite spinner
        spSpecialite.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if (view == null)
                    return;
                //Specialite
                //on selected a spinner item
                Specialite specialite = (Specialite) adapterView.getItemAtPosition(position);

                if (specialite.getNomSpecialite().equals("-- SELECTIONNER UNE SPECIALITE --")) {
                    //change default item color
                    TextView textView = (TextView) view;
                    textView.setTextColor(getResources().getColor(R.color.input_login_hint));
                } else {
                    specialiteId = specialite.getSpId();
                    // show selected spinner item
                    // Toast.makeText(getContext(), "Selected: " + item4, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //Force spinner
        spForce.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if (view == null)
                    return;
                //Force Object
                //on selected a spinner item
                Force force = (Force) adapterView.getItemAtPosition(position);

                if (force.getNomForce().equals("-- SELECTIONNER UNE FORCE --")) {
                    //change default item color
                    TextView textView = (TextView) view;
                    textView.setTextColor(getResources().getColor(R.color.input_login_hint));
                } else {
                    forceId = force.getFid();
                    // show selected spinner item
                    // Toast.makeText(getContext(), "Selected: " + item5, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

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
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
    }
}
