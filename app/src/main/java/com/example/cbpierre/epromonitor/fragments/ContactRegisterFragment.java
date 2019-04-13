package com.example.cbpierre.epromonitor.fragments;


import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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
import com.example.cbpierre.epromonitor.models.Contact;
import com.example.cbpierre.epromonitor.repositories.ContactRepository;
import com.example.cbpierre.epromonitor.viewModels.ContactViewModel;
import com.example.cbpierre.epromonitor.viewModels.LoginViewModel;

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
    private String item1, item2, item3, item4, item5;
    private ContactViewModel mContactViewModel;


    public ContactRegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // ViewModelProviders
        mContactViewModel = ViewModelProviders.of(this).get(ContactViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
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
        spinnerItem(view);

        button = view.findViewById(R.id.btnRegister);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertLocalContacts();
                // contactRepository.populateContact();
            }
        });
    }

    public void insertLocalContacts() {
        String nom = mNom.getText().toString().trim();
        String prenom = mPrenom.getText().toString().trim();
        String email = mEmail.getText().toString().trim();
        String tel = mTel.getText().toString().trim();

        if (!(TextUtils.isEmpty(nom) || TextUtils.isEmpty(prenom) || TextUtils.isEmpty(email) || TextUtils.isEmpty(tel) || TextUtils.isEmpty(item1) || TextUtils.isEmpty(item2) || TextUtils.isEmpty(item3) || TextUtils.isEmpty(item4) || TextUtils.isEmpty(item5))) {
            mContactViewModel.insertContact(new Contact(item2, nom, prenom, item1, item3, item4, item5, tel, null, null, email, 0, null, null, null, null, null, null, 0, null, null));
            getActivity().getSupportFragmentManager().popBackStack(getActivity().getSupportFragmentManager().getBackStackEntryAt(0).getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);

        } else {
            Toast.makeText(getContext(), "Vous avez oublie un champ!!!", Toast.LENGTH_LONG).show();
        }
    }

    public void spinnerItem(@NonNull View view) {

        // Nature spinner
        spNature.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if (view == null)
                    return;
                if (adapterView.getItemAtPosition(position).equals("SELECTIONNER UNE NATURE...")) {
                    //change default item color
                    TextView textView = (TextView) view;
                    textView.setTextColor(getResources().getColor(android.R.color.darker_gray));
                } else {
                    //on selected a spinner item
                    item1 = adapterView.getItemAtPosition(position).toString();
                    // show selected spinner item
                    Toast.makeText(getContext(), "Selected: " + item1, Toast.LENGTH_LONG).show();
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
                if (adapterView.getItemAtPosition(position).equals("SELECTIONNER UN TITRE...")) {
                    //change default item color
                    TextView textView = (TextView) view;
                    textView.setTextColor(getResources().getColor(R.color.input_login_hint));
                } else {
                    //on selected a spinner item
                    item2 = adapterView.getItemAtPosition(position).toString();
                    // show selected spinner item
                    Toast.makeText(getContext(), "Selected: " + item2, Toast.LENGTH_LONG).show();
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
                if (adapterView.getItemAtPosition(position).equals("SELECTIONNER UN SECTEUR...")) {
                    //change default item color
                    TextView textView = (TextView) view;
                    textView.setTextColor(getResources().getColor(R.color.input_login_hint));
                } else {
                    //on selected a spinner item
                    item3 = adapterView.getItemAtPosition(position).toString();
                    // show selected spinner item
                    Toast.makeText(getContext(), "Selected: " + item3, Toast.LENGTH_LONG).show();
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
                if (adapterView.getItemAtPosition(position).equals("SELECTIONNER UNE SPECIALITE...")) {
                    //change default item color
                    TextView textView = (TextView) view;
                    textView.setTextColor(getResources().getColor(R.color.input_login_hint));
                } else {
                    //on selected a spinner item
                    item4 = adapterView.getItemAtPosition(position).toString();
                    // show selected spinner item
                    Toast.makeText(getContext(), "Selected: " + item4, Toast.LENGTH_LONG).show();
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
                if (adapterView.getItemAtPosition(position).equals("SELECTIONNER UNE FORCE...")) {
                    //change default item color
                    TextView textView = (TextView) view;
                    textView.setTextColor(getResources().getColor(R.color.input_login_hint));
                } else {
                    //on selected a spinner item
                    item5 = adapterView.getItemAtPosition(position).toString();
                    // show selected spinner item
                    Toast.makeText(getContext(), "Selected: " + item5, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
}
