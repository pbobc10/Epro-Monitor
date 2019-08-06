package com.example.cbpierre.epromonitor.fragments;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
import com.example.cbpierre.epromonitor.models.CompleteContact;
import com.example.cbpierre.epromonitor.models.Contact;
import com.example.cbpierre.epromonitor.models.Force;
import com.example.cbpierre.epromonitor.models.Nature;
import com.example.cbpierre.epromonitor.models.Secteur;
import com.example.cbpierre.epromonitor.models.Specialite;
import com.example.cbpierre.epromonitor.models.Titre;
import com.example.cbpierre.epromonitor.repositories.ContactRepository;
import com.example.cbpierre.epromonitor.viewModels.ContactViewModel;
import com.example.cbpierre.epromonitor.viewModels.ForceViewModel;
import com.example.cbpierre.epromonitor.viewModels.NatureViewModel;
import com.example.cbpierre.epromonitor.viewModels.SecteurViewModel;
import com.example.cbpierre.epromonitor.viewModels.SharedViewModel;
import com.example.cbpierre.epromonitor.viewModels.SpecialiteViewModel;
import com.example.cbpierre.epromonitor.viewModels.TitreViewModel;
import com.example.cbpierre.epromonitor.viewModels.ZoneViewModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class ModifyContactFragment extends Fragment {
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
    private Button btnRegister;
    private ImageButton btnAddField;
    private String natureId, titreId, secteurId, specialiteId, forceId, nom, prenom, email, tel, tel2, tel3, etatTel2, etatTel3;
    private LinearLayout parentContactRegisterLayout;
    private int fieldCounter = 1;

    private ContactViewModel mContactViewModel;
    private ForceViewModel forceViewModel;
    private NatureViewModel natureViewModel;
    private SecteurViewModel secteurViewModel;
    private SpecialiteViewModel specialiteViewModel;
    private TitreViewModel titreViewModel;
    private SharedViewModel sharedViewModel;
    private ZoneViewModel zoneViewModel;

    private ForceSpinnerAdapter forceSpinnerAdapter;
    private NatureSpinnerAdapter natureSpinnerAdapter;
    private SecteurSpinnerAdapter secteurSpinnerAdapter;
    private SpecialiteSpinnerAdapter specialiteSpinnerAdapter;
    private TitreSpinnerAdapter titreSpinnerAdapter;

    private UserSessionPreferences userSessionPreferences;

    private String creeLe, creePar, modifieLe, modifiePar, transfereLe, transferePar;
    private EditText etPhone2, etPhone3;

    private Contact contact;


    public ModifyContactFragment() {
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
        sharedViewModel = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);

        //SharePreference
        userSessionPreferences = new UserSessionPreferences(getContext());
        creePar = modifiePar = transferePar = userSessionPreferences.getUserDetails();

        //Date
        String parttern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(parttern);
        creeLe = modifieLe = transfereLe = simpleDateFormat.format(new Date());
        // tel
        tel = tel2 = tel3 = null;
        etatTel2 = etatTel3 = "attente";
        //
        contact = new Contact();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //enable back arrow
        showBackButton();
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_modify_contact, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Toast.makeText(getContext(), "Modify Screen", Toast.LENGTH_SHORT).show();
        //Spinner
        spNature = view.findViewById(R.id.spNatureModify);
        spTitre = view.findViewById(R.id.spTitreModify);
        spSecteur = view.findViewById(R.id.spSecteurModify);
        spSpecialite = view.findViewById(R.id.spSpecialiteModify);
        spForce = view.findViewById(R.id.spForceModify);

        parentContactRegisterLayout = view.findViewById(R.id.parent_contact_register_layout);
        mNom = view.findViewById(R.id.etNomModify);
        mPrenom = view.findViewById(R.id.etPrenomModify);
        mEmail = view.findViewById(R.id.etEmailModify);
        mTel = view.findViewById(R.id.etPhoneModify);
        btnRegister = view.findViewById(R.id.btnModify);
        etPhone2 = view.findViewById(R.id.etPhone2);
        etPhone3 = view.findViewById(R.id.etPhone3);
        //populate Spinner
        getSpinnerItem(view);
        ////
        sharedViewModel.getContactMutableLiveData().observe(this, new Observer<CompleteContact>() {
            @Override
            public void onChanged(@Nullable CompleteContact completeContact) {
                // populateContactDetail(completeContact);
                //Log.d("tel3", "modify : "+completeContact);
                mContactViewModel.setNewContactById(completeContact.getContactId());
            }
        });
        mContactViewModel.getNewContactById().observe(this, new Observer<List<Contact>>() {
            @Override
            public void onChanged(@Nullable List<Contact> contacts) {
                Log.d("tel3", "modify : " + contacts.size());
                contact = contacts.get(0);
                setDefaultTextView();
                Log.d("tel3", "modify : " + contact.getContactId());
            }
        });
        setDefaultTextView();
        ////

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("tel3", "tel2: " + tel2 + ' ' + "tel3: " + tel3);
                Log.d("tel3", "fieldCounter: " + fieldCounter);
                modifieContact();
                // contactRepository.populateContact();
            }
        });

        //observers
        spinnerObservers();
        //call back arrow
        backArrow();
    }

    public void modifieContact() {
        initialize();
        if (isValidate()) {
            contact.setTitre(titreId);
            contact.setNom(nom);
            contact.setPrenom(prenom);
            contact.setNature(natureId);
            contact.setSecteur(secteurId);
            contact.setSpecialite(specialiteId);
            contact.setForce(forceId);
            contact.setPhone1(tel);
            contact.setPhone2(tel2);
            contact.setPhone3(tel3);
            contact.setEmail(email);
            mContactViewModel.updateNewContact(contact);
            getActivity().getSupportFragmentManager().popBackStack(getActivity().getSupportFragmentManager().getBackStackEntryAt(0).getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
            Log.d("tel3", tel2 + ' ' + tel3);

        } else {
            Toast.makeText(getContext(), "l'enregistrement a échoué!!!", Toast.LENGTH_LONG).show();
        }
    }

    public void initialize() {
        nom = mNom.getText().toString().trim();
        prenom = mPrenom.getText().toString().trim();
        email = mEmail.getText().toString().trim();
        tel = mTel.getText().toString().trim();
        tel2 = etPhone2.getText().toString().trim();
        tel3 = etPhone3.getText().toString().trim();

        if (tel2.isEmpty() && !tel3.isEmpty()) {
            tel2 = tel3;
            tel3 = "";
        }

    }

    public void setDefaultTextView() {
        mNom.setText(contact.getNom());
        mPrenom.setText(contact.getPrenom());
        mEmail.setText(contact.getEmail());
        mTel.setText(contact.getPhone1());
        etPhone2.setText(contact.getPhone2());
        etPhone3.setText(contact.getPhone3());
    }

    public boolean isValidate() {
        boolean valid = true;
        if (TextUtils.isEmpty(natureId) || natureId == null) {
            TextView errorText = (TextView) spNature.getSelectedView();
            errorText.setError("");
            errorText.setTextColor(Color.RED);//just to highlight that this is an error
            errorText.setText("Le fileur Nature est vide!");
            valid = false;
        } else if (TextUtils.isEmpty(titreId) || titreId == null) {
            TextView errorText = (TextView) spTitre.getSelectedView();
            errorText.setError("");
            errorText.setTextColor(Color.RED);//just to highlight that this is an error
            errorText.setText("Le fileur Titre est vide!");
            valid = false;
        } else if (nom.isEmpty() || nom.length() < 3) {
            mNom.setError("Merci d'entrer un nom valide");
            valid = false;
        } else if (!(natureId.equals("CP") || natureId.equals("IC") || natureId.equals("ICH") || natureId.equals("IE") || natureId.equals("IP")) && (prenom.isEmpty() || prenom.length() < 3)) {
            mPrenom.setError("Merci d'entrer un prenom valide");
            valid = false;
        } else if (TextUtils.isEmpty(secteurId) || secteurId == null) {
            TextView errorText = (TextView) spSecteur.getSelectedView();
            errorText.setError("");
            errorText.setTextColor(Color.RED);//just to highlight that this is an error
            errorText.setText("Le fileur Secteur est vide!");
            valid = false;
        } else if (TextUtils.isEmpty(specialiteId) || specialiteId == null) {
            TextView errorText = (TextView) spSpecialite.getSelectedView();
            errorText.setError("");
            errorText.setTextColor(Color.RED);//just to highlight that this is an error
            errorText.setText("Le fileur Specialite est vide!");
            valid = false;
        } else if (TextUtils.isEmpty(forceId) || forceId == null) {
            TextView errorText = (TextView) spForce.getSelectedView();
            errorText.setError("");
            errorText.setTextColor(Color.RED);//just to highlight that this is an error
            errorText.setText("Le fileur Force est vide!");
            valid = false;
        } else if (tel.isEmpty() || !isValidePhoneNumber(tel)) {
            mTel.setError("Merci d'entrer un numero de telephone valide. ex: ###-####-####");
            valid = false;
        } else if (!tel2.isEmpty() && !isValidePhoneNumber(tel2)) {
            etPhone2.setError("Merci d'entrer un numero de telephone valide. ex: ###-####-####");
            valid = false;
        } else if (!tel3.isEmpty() && !isValidePhoneNumber(tel3)) {
            etPhone3.setError("Merci d'entrer un numero de telephone valide. ex: ###-####-####");
            valid = false;
        } else if (!email.isEmpty() && !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError("Merci d'entrer un email valide");
            valid = false;
        }
        return valid;
    }

    public boolean isValidePhoneNumber(String number) {
        String PHONE_NUMBER = "\\d{3}-\\d{4}-\\d{4}";
        //String PHONE_NUMBER = "\\d{4}-\\d{4}";
        Pattern pattern = Pattern.compile(PHONE_NUMBER);
        Matcher matcher = pattern.matcher(number);
        return matcher.matches();
    }

    public void spinnerObservers() {
        natureViewModel.getAllNature().observe(this, new Observer<List<Nature>>() {
            @Override
            public void onChanged(@Nullable List<Nature> natures) {
                natureSpinnerAdapter = new NatureSpinnerAdapter(getContext(), R.layout.spinner_item, natures);
                natureSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spNature.setAdapter(natureSpinnerAdapter);
                // Nature Spinner Default value
                for (int i = 0; i < natureSpinnerAdapter.getCount(); i++) {
                    if (natureSpinnerAdapter.getItem(i).getNatId().equals(contact.getNature())) {
                        spNature.setSelection(i);
                        break;
                    }
                }
            }
        });
        titreViewModel.getmAllTitre().observe(this, new Observer<List<Titre>>() {
            @Override
            public void onChanged(@Nullable List<Titre> titres) {
                titreSpinnerAdapter = new TitreSpinnerAdapter(getContext(), R.layout.spinner_item, titres);
                titreSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spTitre.setAdapter(titreSpinnerAdapter);
                // Titre Spinner Default value
                for (int i = 0; i < titreSpinnerAdapter.getCount(); i++) {
                    if (titreSpinnerAdapter.getItem(i).getTid().equals(contact.getTitre())) {
                        spTitre.setSelection(i);
                        break;
                    }
                }
            }
        });
        secteurViewModel.getsAllSecteur().observe(this, new Observer<List<Secteur>>() {
            @Override
            public void onChanged(@Nullable List<Secteur> secteurs) {
                secteurSpinnerAdapter = new SecteurSpinnerAdapter(getContext(), R.layout.spinner_item, secteurs);
                secteurSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spSecteur.setAdapter(secteurSpinnerAdapter);
                // Secteur Spinner Default value
                for (int i = 0; i < secteurSpinnerAdapter.getCount(); i++) {
                    if (secteurSpinnerAdapter.getItem(i).getSecId().equals(contact.getSecteur())) {
                        spSecteur.setSelection(i);
                        break;
                    }
                }
            }
        });
        specialiteViewModel.getAllSpecialite().observe(this, new Observer<List<Specialite>>() {
            @Override
            public void onChanged(@Nullable List<Specialite> specialites) {
                specialiteSpinnerAdapter = new SpecialiteSpinnerAdapter(getContext(), R.layout.spinner_item, specialites);
                specialiteSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spSpecialite.setAdapter(specialiteSpinnerAdapter);
                // Specialite Spinner Default value
                for (int i = 0; i < specialiteSpinnerAdapter.getCount(); i++) {
                    if (specialiteSpinnerAdapter.getItem(i).getSpId().equals(contact.getSpecialite())) {
                        spSpecialite.setSelection(i);
                        break;
                    }
                }

            }
        });
        forceViewModel.getAllForce().observe(this, new Observer<List<Force>>() {
            @Override
            public void onChanged(@Nullable List<Force> forces) {
                forceSpinnerAdapter = new ForceSpinnerAdapter(getContext(), R.layout.spinner_item, forces);
                forceSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spForce.setAdapter(forceSpinnerAdapter);
                // Force Spinner Default value
                for (int i = 0; i < forceSpinnerAdapter.getCount(); i++) {
                    if (forceSpinnerAdapter.getItem(i).getFid().equals(contact.getForce())) {
                        spForce.setSelection(i);
                        break;
                    }
                }
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
                if (nature == null)
                    Log.d("spinner_test", "test 0");

                //test spTitre is enabled
                if (!spTitre.isEnabled())
                    spTitre.setEnabled(true);
                //test spSecteur is enabled
                if (!spSecteur.isEnabled())
                    spSecteur.setEnabled(true);
                //test spSpecialite is enabled
                if (!spSpecialite.isEnabled())
                    spSpecialite.setEnabled(true);

                if (nature.getNomNature().equals("-- SELECTIONNER UNE NATURE --")) {
                    //change default item color
                    TextView textView = (TextView) view;
                    textView.setTextColor(getResources().getColor(android.R.color.darker_gray));
                } else {
                    Log.d("spinner_test", "test 1");
                    natureId = nature.getNatId();
                    if (natureId.equals("CP") || natureId.equals("IC") || natureId.equals("ICH") || natureId.equals("IE") || natureId.equals("IP")) {
                        for (int i = 0; i < titreSpinnerAdapter.getCount(); i++) {
                            if (titreSpinnerAdapter.getItem(i).getTid().equals("NA")) {
                                spTitre.setSelection(i);
                                spTitre.setEnabled(false);
                                //titreId = titreSpinnerAdapter.getItem(i).getTid();  //?
                                Toast.makeText(getContext(), "Selected: " + titreId, Toast.LENGTH_LONG).show();
                                break;
                            }
                        }
                    } //
                    if (natureId.equals("ICH") || natureId.equals("IE") || natureId.equals("IP") || natureId.equals("P")) {
                        for (int i = 0; i < secteurSpinnerAdapter.getCount(); i++) {
                            if (secteurSpinnerAdapter.getItem(i).getSecId().equals("NA")) {
                                spSecteur.setSelection(i);
                                spSecteur.setEnabled(false);
                                break;
                            }
                        }
                    }
                    //
                    if (!(natureId.equals("P") || natureId.equals("CP"))) {
                        for (int i = 0; i < specialiteSpinnerAdapter.getCount(); i++) {
                            if (specialiteSpinnerAdapter.getItem(i).getSpId().equals("NA")) {
                                spSpecialite.setSelection(i);
                                spSpecialite.setEnabled(false);
                                break;
                            }
                        }
                    }
                    //
                    else {
                        natureId = nature.getNatId();
                    }
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
