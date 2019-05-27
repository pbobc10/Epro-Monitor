package com.example.cbpierre.epromonitor.fragments;


import android.app.ProgressDialog;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.cbpierre.epromonitor.AppConfig;
import com.example.cbpierre.epromonitor.AppVolleySingleton;
import com.example.cbpierre.epromonitor.CustomArrayRequest;
import com.example.cbpierre.epromonitor.CustomRequest;
import com.example.cbpierre.epromonitor.R;
import com.example.cbpierre.epromonitor.models.CompleteEtablissement;
import com.example.cbpierre.epromonitor.models.Contact;
import com.example.cbpierre.epromonitor.models.ContactEtablissement;
import com.example.cbpierre.epromonitor.models.Etablissement;
import com.example.cbpierre.epromonitor.models.Force;
import com.example.cbpierre.epromonitor.models.Nature;
import com.example.cbpierre.epromonitor.models.Secteur;
import com.example.cbpierre.epromonitor.models.Specialite;
import com.example.cbpierre.epromonitor.models.Titre;
import com.example.cbpierre.epromonitor.models.Zone;
import com.example.cbpierre.epromonitor.repositories.NatureRepository;
import com.example.cbpierre.epromonitor.repositories.SecteurRepository;
import com.example.cbpierre.epromonitor.repositories.SpecialiteRepository;
import com.example.cbpierre.epromonitor.viewModels.ContactEtablissementViewModel;
import com.example.cbpierre.epromonitor.viewModels.ContactViewModel;
import com.example.cbpierre.epromonitor.viewModels.EtablissementViewModel;
import com.example.cbpierre.epromonitor.viewModels.ForceViewModel;
import com.example.cbpierre.epromonitor.viewModels.NatureViewModel;
import com.example.cbpierre.epromonitor.viewModels.SecteurViewModel;
import com.example.cbpierre.epromonitor.viewModels.SpecialiteViewModel;
import com.example.cbpierre.epromonitor.viewModels.TitreViewModel;
import com.example.cbpierre.epromonitor.viewModels.ZoneViewModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TelechargementFragment extends Fragment {

    private ForceViewModel forceViewModel;
    private SecteurViewModel secteurViewModel;
    private NatureViewModel natureViewModel;
    private SpecialiteViewModel specialiteViewModel;
    private TitreViewModel titreViewModel;
    private ZoneViewModel zoneViewModel;
    private ContactViewModel contactViewModel;
    private EtablissementViewModel etablissementViewModel;
    private ContactEtablissementViewModel contactEtablissementViewModel;

    private Button btnDownloadContact, btnSyncContact, btnDownloadEtablissement, btnSyncEtablissement;

    private ProgressDialog pDialog;


    public TelechargementFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ViewModelProviders
        forceViewModel = ViewModelProviders.of(this).get(ForceViewModel.class);
        secteurViewModel = ViewModelProviders.of(this).get(SecteurViewModel.class);
        natureViewModel = ViewModelProviders.of(this).get(NatureViewModel.class);
        specialiteViewModel = ViewModelProviders.of(this).get(SpecialiteViewModel.class);
        titreViewModel = ViewModelProviders.of(this).get(TitreViewModel.class);
        zoneViewModel = ViewModelProviders.of(this).get(ZoneViewModel.class);
        contactViewModel = ViewModelProviders.of(this).get(ContactViewModel.class);
        etablissementViewModel = ViewModelProviders.of(this).get(EtablissementViewModel.class);
        contactEtablissementViewModel = ViewModelProviders.of(this).get(ContactEtablissementViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_telechargement, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Progress Dialog
        pDialog = new ProgressDialog(getContext(), ProgressDialog.STYLE_SPINNER);
        pDialog.setCancelable(false);

        btnDownloadContact = view.findViewById(R.id.btnDownloadData);
        btnDownloadEtablissement = view.findViewById(R.id.btnDownloadDataEta);


        btnDownloadContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //set title of the dialog
                pDialog.setTitle("Get Contact Data  ...");
                //set message of the dialog
                pDialog.setMessage("Please wait.");
                //show dialog
                showDialog();

                forceRequest(AppConfig.URL_FORCE);
                natureRequest(AppConfig.URL_NATURE);
                secteurRequest(AppConfig.URL_SECTEUR);
                specialiteRequest(AppConfig.URL_SPECIALITE);
                titreRequest(AppConfig.URL_TITRE);
                zoneRequest(AppConfig.URL_ZONE);
                contactRequest(AppConfig.URL_CONTACT);

            }
        });

        btnDownloadEtablissement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //set title of the dialog
                pDialog.setTitle("Get Etablissement Data  ...");
                //set message of the dialog
                pDialog.setMessage("Please wait.");
                //show dialog
                showDialog();

                etablissementRequest(AppConfig.URL_ETABLISSEMENT);

            }
        });

    }

    public void forceRequest(String url) {

        CustomArrayRequest customArrayRequest = new CustomArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                ArrayList<Force> forces = Force.fromJson(response);

                for (Force force : forces) {
                    forceViewModel.insert(force);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        AppVolleySingleton.getInstance(getContext()).addToRequestQueue(customArrayRequest);
    }

    public void natureRequest(String url) {

        CustomArrayRequest customArrayRequest = new CustomArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                ArrayList<Nature> natures = Nature.fromJson(response);

                for (Nature nature : natures) {
                    natureViewModel.insertNature(nature);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        AppVolleySingleton.getInstance(getContext()).addToRequestQueue(customArrayRequest);
    }

    public void secteurRequest(String url) {

        CustomArrayRequest customArrayRequest = new CustomArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                ArrayList<Secteur> secteurs = Secteur.fromJson(response);

                for (Secteur secteur : secteurs) {
                    secteurViewModel.insertSecteur(secteur);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        AppVolleySingleton.getInstance(getContext()).addToRequestQueue(customArrayRequest);
    }

    public void specialiteRequest(String url) {

        CustomArrayRequest customArrayRequest = new CustomArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                ArrayList<Specialite> specialites = Specialite.fromJson(response);

                for (Specialite specialite : specialites) {
                    specialiteViewModel.insertSpecialite(specialite);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        AppVolleySingleton.getInstance(getContext()).addToRequestQueue(customArrayRequest);
    }

    public void titreRequest(String url) {

        CustomArrayRequest customArrayRequest = new CustomArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                ArrayList<Titre> titres = Titre.fromJson(response);

                for (Titre titre : titres) {
                    titreViewModel.insertTitre(titre);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        AppVolleySingleton.getInstance(getContext()).addToRequestQueue(customArrayRequest);
    }

    public void zoneRequest(String url) {

        CustomArrayRequest customArrayRequest = new CustomArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                ArrayList<Zone> zones = Zone.fromJson(response);

                for (Zone zone : zones) {
                    zoneViewModel.insertZone(zone);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        AppVolleySingleton.getInstance(getContext()).addToRequestQueue(customArrayRequest);
    }

    public void contactRequest(String url) {

        CustomArrayRequest customArrayRequest = new CustomArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                ArrayList<Contact> contacts = Contact.fromJson(response);

                for (Contact contact : contacts) {
                    contactViewModel.insertContact(contact);
                }
                for (ContactEtablissement etab : Contact.fromJsonEtabs(response)) {
                    contactEtablissementViewModel.insertContactEtablissement(etab);
                }
                //dismiss dialog
                hideDialog();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        AppVolleySingleton.getInstance(getContext()).addToRequestQueue(customArrayRequest);
    }

    public void etablissementRequest(String url) {

        CustomArrayRequest customArrayRequest = new CustomArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                ArrayList<Etablissement> etablissements = Etablissement.fromJson(response);

                for (Etablissement etablissement : etablissements) {
                    etablissementViewModel.insertEtablissement(etablissement);
                }
                //dismiss dialog
                hideDialog();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        AppVolleySingleton.getInstance(getContext()).addToRequestQueue(customArrayRequest);
    }

    public void showDialog() {
        if (!pDialog.isShowing()) {
            pDialog.show();
        }
    }

    public void hideDialog() {
        if (pDialog.isShowing()) {
            pDialog.dismiss();
        }
    }

}
