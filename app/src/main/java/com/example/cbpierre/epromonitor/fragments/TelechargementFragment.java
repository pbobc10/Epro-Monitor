package com.example.cbpierre.epromonitor.fragments;


import android.app.ProgressDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.cbpierre.epromonitor.AppConfig;
import com.example.cbpierre.epromonitor.AppVolleySingleton;
import com.example.cbpierre.epromonitor.CustomArrayRequest;
import com.example.cbpierre.epromonitor.R;
import com.example.cbpierre.epromonitor.UserSessionPreferences;
import com.example.cbpierre.epromonitor.models.AcceptabiliteRef;
import com.example.cbpierre.epromonitor.models.CommuneLocaliteContact;
import com.example.cbpierre.epromonitor.models.Contact;
import com.example.cbpierre.epromonitor.models.ContactEtablissement;
import com.example.cbpierre.epromonitor.models.ContactVisite;
import com.example.cbpierre.epromonitor.models.Etablissement;
import com.example.cbpierre.epromonitor.models.Force;
import com.example.cbpierre.epromonitor.models.GH;
import com.example.cbpierre.epromonitor.models.GHJour;
import com.example.cbpierre.epromonitor.models.GHJourContact;
import com.example.cbpierre.epromonitor.models.GHJourContactProduit;
import com.example.cbpierre.epromonitor.models.JoinNewEtabNewContact;
import com.example.cbpierre.epromonitor.models.Nature;
import com.example.cbpierre.epromonitor.models.OldEtablissement;
import com.example.cbpierre.epromonitor.models.PaContact;
import com.example.cbpierre.epromonitor.models.PaContactProduit;
import com.example.cbpierre.epromonitor.models.PlanAction;
import com.example.cbpierre.epromonitor.models.PostLogin;
import com.example.cbpierre.epromonitor.models.Produit;
import com.example.cbpierre.epromonitor.models.Secteur;
import com.example.cbpierre.epromonitor.models.SendGH;
import com.example.cbpierre.epromonitor.models.SendNewContactEtabs;
import com.example.cbpierre.epromonitor.models.Specialite;
import com.example.cbpierre.epromonitor.models.StatutJourRef;
import com.example.cbpierre.epromonitor.models.StatutVisiteRef;
import com.example.cbpierre.epromonitor.models.Titre;
import com.example.cbpierre.epromonitor.models.Zone;
import com.example.cbpierre.epromonitor.repositories.ContactRepository;
import com.example.cbpierre.epromonitor.repositories.EtablissementRepository;
import com.example.cbpierre.epromonitor.repositories.GHJourContactProduitRepository;
import com.example.cbpierre.epromonitor.repositories.GHJourContactRepository;
import com.example.cbpierre.epromonitor.repositories.GHJourRepository;
import com.example.cbpierre.epromonitor.repositories.GHRepository;
import com.example.cbpierre.epromonitor.repositories.PaContactRepository;
import com.example.cbpierre.epromonitor.repositories.ProduitRepository;
import com.example.cbpierre.epromonitor.viewModels.AcceptabiliteViewModel;
import com.example.cbpierre.epromonitor.viewModels.CommuneLocaliteContactViewModel;
import com.example.cbpierre.epromonitor.viewModels.ContactEtablissementViewModel;
import com.example.cbpierre.epromonitor.viewModels.ContactViewModel;
import com.example.cbpierre.epromonitor.viewModels.ContactVisiteViewModel;
import com.example.cbpierre.epromonitor.viewModels.EtablissementViewModel;
import com.example.cbpierre.epromonitor.viewModels.ForceViewModel;
import com.example.cbpierre.epromonitor.viewModels.GHJourContactProduitViewModel;
import com.example.cbpierre.epromonitor.viewModels.GHJourContactViewModel;
import com.example.cbpierre.epromonitor.viewModels.GHJourViewModel;
import com.example.cbpierre.epromonitor.viewModels.GHViewModel;
import com.example.cbpierre.epromonitor.viewModels.NatureViewModel;
import com.example.cbpierre.epromonitor.viewModels.NewContactEtabViewModel;
import com.example.cbpierre.epromonitor.viewModels.PaContactProduitViewModel;
import com.example.cbpierre.epromonitor.viewModels.PaContactViewModel;
import com.example.cbpierre.epromonitor.viewModels.PlanActionViewModel;
import com.example.cbpierre.epromonitor.viewModels.PostViewModel;
import com.example.cbpierre.epromonitor.viewModels.ProduitViewModel;
import com.example.cbpierre.epromonitor.viewModels.SecteurViewModel;
import com.example.cbpierre.epromonitor.viewModels.SpecialiteViewModel;
import com.example.cbpierre.epromonitor.viewModels.StatutJourViewModel;
import com.example.cbpierre.epromonitor.viewModels.StatutVisiteViewModel;
import com.example.cbpierre.epromonitor.viewModels.TitreViewModel;
import com.example.cbpierre.epromonitor.viewModels.ZoneViewModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
    private NewContactEtabViewModel newContactEtabViewModel;
    private PlanActionViewModel planActionViewModel;
    private PaContactViewModel paContactViewModel;
    private PaContactProduitViewModel paContactProduitViewModel;
    private ProduitViewModel produitViewModel;
    private PostViewModel postViewModel;
    private ContactVisiteViewModel contactVisiteViewModel;
    private GHViewModel ghViewModel;
    private GHJourViewModel ghJourViewModel;
    private GHJourContactViewModel ghJourContactViewModel;
    private GHJourContactProduitViewModel ghJourContactProduitViewModel;
    private StatutJourViewModel statutJourViewModel;
    private StatutVisiteViewModel statutVisiteViewModel;
    private AcceptabiliteViewModel acceptabiliteViewModel;
    private CommuneLocaliteContactViewModel communeLocaliteContactViewModel;

    private ArrayList<OldEtablissement> oldEtabs;
    private ArrayList<JoinNewEtabNewContact> newEtab;

    private Button btnDownloadContact, btnSyncContact, btnDownloadEtablissement, btnSyncEtabData, btnDownloadPaData, btnSyncPaData, btnDownloadGHData, btnSyncGHData;
    private List<SendNewContactEtabs> sendContactEtabList;
    private SendNewContactEtabs sendNewContactEtabs;
    private ProgressDialog pDialog;

    private ArrayList<Integer> etabExistant;
    private ArrayList<JoinNewEtabNewContact> newEtabList;
    private ArrayList<GH> ghs;
    private ArrayList<GHJour> ghJours;
    private ArrayList<GHJourContact> ghJourContacts;
    private ArrayList<GHJourContactProduit> ghJourContactProduitList;
    private ArrayList<PaContact> paContactArrayList;
    private ArrayList<Integer> produitArrayList;

    UserSessionPreferences userSessionPreferences;

    private String transfere_le, paramCieID;
    private SendGH sendGH;
    private boolean isCheckedCourant, isCheckedProchain;
    private String statutTrans;

    public TelechargementFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ViewModelProviders
        postViewModel = ViewModelProviders.of(this).get(PostViewModel.class);
        forceViewModel = ViewModelProviders.of(this).get(ForceViewModel.class);
        secteurViewModel = ViewModelProviders.of(this).get(SecteurViewModel.class);
        natureViewModel = ViewModelProviders.of(this).get(NatureViewModel.class);
        specialiteViewModel = ViewModelProviders.of(this).get(SpecialiteViewModel.class);
        titreViewModel = ViewModelProviders.of(this).get(TitreViewModel.class);
        zoneViewModel = ViewModelProviders.of(this).get(ZoneViewModel.class);
        contactViewModel = ViewModelProviders.of(this).get(ContactViewModel.class);
        etablissementViewModel = ViewModelProviders.of(this).get(EtablissementViewModel.class);
        contactEtablissementViewModel = ViewModelProviders.of(this).get(ContactEtablissementViewModel.class);
        newContactEtabViewModel = ViewModelProviders.of(this).get(NewContactEtabViewModel.class);
        planActionViewModel = ViewModelProviders.of(this).get(PlanActionViewModel.class);
        paContactViewModel = ViewModelProviders.of(this).get(PaContactViewModel.class);
        paContactProduitViewModel = ViewModelProviders.of(this).get(PaContactProduitViewModel.class);
        produitViewModel = ViewModelProviders.of(this).get(ProduitViewModel.class);
        contactVisiteViewModel = ViewModelProviders.of(this).get(ContactVisiteViewModel.class);
        ghViewModel = ViewModelProviders.of(this).get(GHViewModel.class);
        ghJourViewModel = ViewModelProviders.of(this).get(GHJourViewModel.class);
        ghJourContactViewModel = ViewModelProviders.of(this).get(GHJourContactViewModel.class);
        ghJourContactProduitViewModel = ViewModelProviders.of(this).get(GHJourContactProduitViewModel.class);
        statutJourViewModel = ViewModelProviders.of(this).get(StatutJourViewModel.class);
        statutVisiteViewModel = ViewModelProviders.of(this).get(StatutVisiteViewModel.class);
        acceptabiliteViewModel = ViewModelProviders.of(this).get(AcceptabiliteViewModel.class);
        communeLocaliteContactViewModel = ViewModelProviders.of(this).get(CommuneLocaliteContactViewModel.class);
        //Date
        String parttern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(parttern);
        transfere_le = simpleDateFormat.format(new Date());

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

        //UserSessionPreferences
        userSessionPreferences = new UserSessionPreferences(getContext());

        //Progress Dialog
        pDialog = new ProgressDialog(getContext(), ProgressDialog.STYLE_SPINNER);
        pDialog.setCancelable(false);

        btnDownloadContact = view.findViewById(R.id.btnDownloadData);
        btnDownloadEtablissement = view.findViewById(R.id.btnDownloadDataEta);
        btnSyncEtabData = view.findViewById(R.id.btnSyncEtabData);
        btnSyncContact = view.findViewById(R.id.btnSyncContactData);

        btnDownloadPaData = view.findViewById(R.id.btnDownloadPAData);
        btnSyncPaData = view.findViewById(R.id.btnSyncPAData);
        btnDownloadGHData = view.findViewById(R.id.btnDownloadGHData);
        btnSyncGHData = view.findViewById(R.id.btnSyncGHData);

        btnSyncEtabData.setVisibility(View.INVISIBLE);
        btnSyncPaData.setVisibility(View.INVISIBLE);

        postViewModel.getAllPostLOgin().observe(this, new Observer<List<PostLogin>>() {
            @Override
            public void onChanged(@Nullable List<PostLogin> postLogins) {
                if (postLogins != null && postLogins.size() > 0)
                    paramCieID = postLogins.get(0).getCieId();
            }
        });
        /**
         * Contact button
         */
        btnDownloadContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //set title of the dialog
                pDialog.setTitle("Téléchargement des Contacts ...");
                //set message of the dialog
                pDialog.setMessage("S'il vous plaît, attendez ...");
                //show dialog
                showDialog();

                forceRequest(AppConfig.URL_FORCE);
                natureRequest(AppConfig.URL_NATURE);
                secteurRequest(AppConfig.URL_SECTEUR);
                specialiteRequest(AppConfig.URL_SPECIALITE);
                titreRequest(AppConfig.URL_TITRE);
                contactRequest(AppConfig.URL_CONTACT);

            }
        });

        btnSyncContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //set title of the dialog
                pDialog.setTitle("synchronisation des données ...");
                //set message of the dialog
                pDialog.setMessage("S'il vous plaît, attendez...");
                //show dialog
                showDialog();


                //Async Old Etabs
                etablissementViewModel.getOldEtabs();
                //Async New Etabs
                etablissementViewModel.getNewEtabs();
                //Async New Contact
                contactViewModel.getNewContacts();

                //  Log.d("-test", toJSON(createSendNewContactEtabsObject()));
            }
        });

        /**
         * Etablissement button
         */
        btnDownloadEtablissement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //set title of the dialog
                pDialog.setTitle("synchronisation des données ...");
                //set message of the dialog
                pDialog.setMessage("S'il vous plaît, attendez...");
                //show dialog
                showDialog();

                zoneRequest(AppConfig.URL_ZONE);
                etablissementRequest(AppConfig.URL_ETABLISSEMENT);
            }
        });

        /**
         * Plan d'Action PA button
         */
        btnDownloadPaData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //set title of the dialog
                pDialog.setTitle("Téléchargement des données du PA ...");
                //set message of the dialog
                pDialog.setMessage("S'il vous plaît, attendez ...");
                //show dialog
                showDialog();

                planActionRequest(AppConfig.URL_PA);
                contactVisiteRequest(AppConfig.URL_CONTACT_VISITE);
                produitRequest(AppConfig.URL_PRODUIT_REF);
            }
        });

        /**
         * Gains Hebdomadaires
         */
        btnDownloadGHData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //set title of the dialog
                pDialog.setTitle("Téléchargement des données du GH ...");
                //set message of the dialog
                pDialog.setMessage("S'il vous plaît, attendez ...");
                //show dialog
                showDialog();
                ghRequest(AppConfig.URL_GH);
                statutJourRequest(AppConfig.URL_STATUT_JOUR);
                statutVisiteRequest(AppConfig.URL_STATUT_VISITE);
                acceptabiliteRequest(AppConfig.URL_ACCEPTABILITE);
                communeLocaliteContactRequest(AppConfig.URL_COMMUNE_LOCALITE_CONTACT);
            }
        });

        btnSyncGHData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog();

            }
        });

        /**
         * Async Response
         */
        fromAsyncReposeCreateJsonString();
        fromGHAsyncTask();
    }

    public void insertPA(JSONArray response) {
        paContactArrayList = new ArrayList<>();
        paContactArrayList.clear();

        //inserer les PA
        for (PlanAction pa : PlanAction.fromJson(response)) {
            planActionViewModel.insertPlanAction(pa);
        }
        //inserer les PA Contact
        for (PaContact paC : PlanAction.fromJsonPAContact(response)) {
            paContactViewModel.insertPaContact(paC);
            paContactArrayList.add(paC);
        }
        //inserer les PA Contact Produit
        for (PaContactProduit paCP : PlanAction.fromJsonPaContactProduit(response)) {
            paContactProduitViewModel.insertPaContactProduit(paCP);
        }

        Log.d("sizetest_paContact", "" +paContactArrayList.size());
        Log.d("sizetest_prouduit", "" + produitArrayList.size());

        // insert Contact produit not really in PA
        for (int i = 0; i < paContactArrayList.size(); i++) {
            for (int j = 0; j < produitArrayList.size(); j++) {
                if (paContactArrayList.get(i).getQuota().trim().equals("-1")) {
                    paContactProduitViewModel.insertPaContactProduit(new PaContactProduit(paContactArrayList.get(i).getPaId(), paContactArrayList.get(i).getConId(), produitArrayList.get(j)));
                }
            }
        }
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
                //dismiss dialog
                hideDialog();
                Toast.makeText(getContext(), "Téléchargement annulé en raison d'une erreur", Toast.LENGTH_SHORT).show();
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
                //dismiss dialog
                hideDialog();
                Toast.makeText(getContext(), "Téléchargement annulé en raison d'une erreur", Toast.LENGTH_SHORT).show();
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
                //dismiss dialog
                hideDialog();
                Toast.makeText(getContext(), "Téléchargement annulé en raison d'une erreur", Toast.LENGTH_SHORT).show();
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
                //dismiss dialog
                hideDialog();
                Toast.makeText(getContext(), "Téléchargement annulé en raison d'une erreur", Toast.LENGTH_SHORT).show();
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
                //dismiss dialog
                hideDialog();
                Toast.makeText(getContext(), "Téléchargement annulé en raison d'une erreur", Toast.LENGTH_SHORT).show();
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

                //dismiss dialog
                hideDialog();
                if (error instanceof NetworkError) {
                    Toast.makeText(getContext(), "Network Error! Can't reach https://disprophar.net \n\t\t\t\t ZONE", Toast.LENGTH_SHORT).show();
                } else if (error instanceof ServerError) {
                    Toast.makeText(getContext(), "https://disprophar.net responded with an error response \n\t\t\t\t ZONE", Toast.LENGTH_SHORT).show();
                } else if (error instanceof TimeoutError) {
                    Toast.makeText(getContext(), "Connection or the socket timed out \n\t\t\t\t ZONE", Toast.LENGTH_SHORT).show();
                }
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
                Toast.makeText(getContext(), "Téléchargement terminé", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //dismiss dialog
                hideDialog();
                Toast.makeText(getContext(), "Téléchargement annulé en raison d'une erreur", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(getContext(), "Téléchargement ETABLISSEMENT terminé", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                //dismiss dialog
                hideDialog();
                if (error instanceof NetworkError) {
                    Toast.makeText(getContext(), "Network Error! Can't reach https://disprophar.net \n\t\t\t\t ETABLISSEMENT", Toast.LENGTH_SHORT).show();
                } else if (error instanceof ServerError) {
                    Toast.makeText(getContext(), "https://disprophar.net responded with an error response \n\t\t\t\t ETABLISSEMENT", Toast.LENGTH_SHORT).show();
                } else if (error instanceof TimeoutError) {
                    Toast.makeText(getContext(), "Connection or the socket timed out \n\t\t\t\t ETABLISSEMENT", Toast.LENGTH_SHORT).show();
                }
            }
        });

        AppVolleySingleton.getInstance(getContext()).addToRequestQueue(customArrayRequest);
    }

    /**
     * All PA
     */
    public void planActionRequest(String url) {
        CustomArrayRequest customArrayRequest = new CustomArrayRequest(Request.Method.GET, url + paramCieID, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                //suprimer les PA
                planActionViewModel.deletePlanAction();
                paContactViewModel.deletePaContact();
                paContactProduitViewModel.deletePaContactProduit();

                //inserer  PA
                insertPA(response);

                //dismiss dialog
                hideDialog();
                Toast.makeText(getContext(), "Téléchargement PA terminé", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof NetworkError) {
                    Toast.makeText(getContext(), "Network Error! Can't reach https://disprophar.net \n\t\t\t\t PA", Toast.LENGTH_SHORT).show();
                    //dismiss dialog
                    hideDialog();
                } else if (error instanceof ServerError) {
                    Toast.makeText(getContext(), "https://disprophar.net responded with an error response \n\t\t\t\t PA", Toast.LENGTH_SHORT).show();
                    //dismiss dialog
                    hideDialog();
                } else if (error instanceof TimeoutError) {
                    Toast.makeText(getContext(), "Connection or the socket timed out \n\t\t\t\t PA", Toast.LENGTH_SHORT).show();
                    //dismiss dialog
                    hideDialog();
                }
            }
        });
        //customArrayRequest.setShouldCache(false);
       //0 customArrayRequest.setRetryPolicy(new DefaultRetryPolicy((int) TimeUnit.SECONDS.toMillis(10), DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppVolleySingleton.getInstance(getContext()).addToRequestQueue(customArrayRequest);
    }

    public void produitRequest(String url) {
        CustomArrayRequest customArrayRequest = new CustomArrayRequest(Request.Method.GET, url + paramCieID, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                //delete produit
                produitViewModel.deleteProduit();

                produitArrayList = new ArrayList<>();
                // if (produitArrayList.size() > 0)
                produitArrayList.clear();

                //telecharger produit
                for (Produit pro : Produit.fromJson(response)) {
                    produitViewModel.insertProduit(pro);
                    produitArrayList.add(pro.getProduitId());
                }

                //dismiss dialog
                hideDialog();
                Toast.makeText(getContext(), "Téléchargement PRODUIT terminé", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //dismiss dialog
                hideDialog();
                if (error instanceof NetworkError) {
                    Toast.makeText(getContext(), "Network Error! Can't reach https://disprophar.net \n\t\t\t\t PRODUIT", Toast.LENGTH_SHORT).show();
                } else if (error instanceof ServerError) {
                    Toast.makeText(getContext(), "https://disprophar.net responded with an error response \n\t\t\t\t PRODUIT", Toast.LENGTH_SHORT).show();
                } else if (error instanceof TimeoutError) {
                    Toast.makeText(getContext(), "Connection or the socket timed out \n\t\t\t\t PRODUIT", Toast.LENGTH_SHORT).show();
                }
            }
        });
        AppVolleySingleton.getInstance(getContext()).addToRequestQueue(customArrayRequest);
    }

    public void contactVisiteRequest(String url) {
        CustomArrayRequest customArrayRequest = new CustomArrayRequest(Request.Method.GET, url + paramCieID, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                //delete contact visite
                contactVisiteViewModel.deleteContactVisite();

                //telechager contact visite
                for (ContactVisite contactVisite : ContactVisite.fromJson(response)) {
                    contactVisiteViewModel.insertContactVisite(contactVisite);
                }
                //dismiss dialog
                hideDialog();
                Toast.makeText(getContext(), "Téléchargement CONTACT VISITE terminé", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //dismiss dialog
                hideDialog();
                if (error instanceof NetworkError) {
                    Toast.makeText(getContext(), "Network Error! Can't reach https://disprophar.net \n\t\t\t\t CONTACT VISITE", Toast.LENGTH_SHORT).show();
                } else if (error instanceof ServerError) {
                    Toast.makeText(getContext(), "https://disprophar.net responded with an error response \n\t\t\t\t CONTACT VISITE", Toast.LENGTH_SHORT).show();
                } else if (error instanceof TimeoutError) {
                    Toast.makeText(getContext(), "Connection or the socket timed out  \n\t\t\t\t CONTACT VISITE", Toast.LENGTH_SHORT).show();
                }
            }
        });
        AppVolleySingleton.getInstance(getContext()).addToRequestQueue(customArrayRequest);
    }

    /**
     * All GH
     */
    public void ghRequest(String url) {
        CustomArrayRequest customArrayRequest = new CustomArrayRequest(Request.Method.GET, url + paramCieID, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                //delete
                ghViewModel.deleteGH();
                ghJourViewModel.deleteGHJour();
                ghJourContactViewModel.deleteGHJourContact();
                ghJourContactProduitViewModel.deleteGHJourContactProduit();

                //download
                for (GH gh : GH.fromJSONGH(response)) {
                    ghViewModel.insertGH(gh);
                }

                for (GHJour ghJour : GH.fromJSONGhJour(response)) {
                    ghJourViewModel.insertGHJour(ghJour);
                }
                try {
                    for (GHJourContact ghJourContact : GH.fromJSONGHJourContact(response)) {
                        ghJourContactViewModel.insertGHJourContact(ghJourContact);
                    }

                    for (GHJourContactProduit ghJourContactProduit : GH.fromJSONGHJourContactProduit(response)) {
                        ghJourContactProduitViewModel.insertGHJourContactProduit(ghJourContactProduit);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //dismiss dialog
                hideDialog();
                Toast.makeText(getContext(), "Téléchargement du GH terminé", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //dismiss dialog
                hideDialog();
                if (error instanceof NetworkError) {
                    Toast.makeText(getContext(), "Network Error! Can't reach https://disprophar.net \n\t\t\t\t GH", Toast.LENGTH_SHORT).show();
                } else if (error instanceof ServerError) {
                    Toast.makeText(getContext(), "https://disprophar.net responded with an error response \n\t\t\t\t GH", Toast.LENGTH_SHORT).show();
                } else if (error instanceof TimeoutError) {
                    Toast.makeText(getContext(), "Connection or the socket timed out \n\t\t\t\t GH", Toast.LENGTH_SHORT).show();
                }
            }
        });
        AppVolleySingleton.getInstance(getContext()).addToRequestQueue(customArrayRequest);
    }

    public void statutJourRequest(String url) {
        CustomArrayRequest customArrayRequest = new CustomArrayRequest(Request.Method.GET, url + paramCieID, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                //delete
                statutJourViewModel.deleteStatutJour();

                //download
                for (StatutJourRef statutJourRef : StatutJourRef.fromJSON(response)) {
                    statutJourViewModel.insertStatutJour(statutJourRef);
                }
                //dismiss dialog
                hideDialog();
                Toast.makeText(getContext(), "Téléchargement SJ terminé", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //dismiss dialog
                hideDialog();
                Toast.makeText(getContext(), "Téléchargement SJ annulé en raison d'une erreur", Toast.LENGTH_SHORT).show();
            }
        });
        AppVolleySingleton.getInstance(getContext()).addToRequestQueue(customArrayRequest);
    }

    public void statutVisiteRequest(String url) {
        CustomArrayRequest customArrayRequest = new CustomArrayRequest(Request.Method.GET, url + paramCieID, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                //delete
                statutVisiteViewModel.deleteStatutVisite();

                //download
                for (StatutVisiteRef statutVisiteRef : StatutVisiteRef.fromJSON(response)) {
                    statutVisiteViewModel.insertStatutVisite(statutVisiteRef);
                }
                //dismiss dialog
                hideDialog();
                Toast.makeText(getContext(), "Téléchargement SV terminé", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //dismiss dialog
                hideDialog();
                Toast.makeText(getContext(), "Téléchargement SV annulé en raison d'une erreur", Toast.LENGTH_SHORT).show();
            }
        });
        AppVolleySingleton.getInstance(getContext()).addToRequestQueue(customArrayRequest);
    }

    public void acceptabiliteRequest(String url) {
        CustomArrayRequest customArrayRequest = new CustomArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                //delete
                acceptabiliteViewModel.deleteAcceptabilite();

                //download
                for (AcceptabiliteRef acceptabiliteRef : AcceptabiliteRef.fromJSON(response)) {
                    acceptabiliteViewModel.incertAcceptabilite(acceptabiliteRef);
                }
                //dismiss dialog
                hideDialog();
                Toast.makeText(getContext(), "Téléchargement terminé", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //dismiss dialog
                hideDialog();
                Toast.makeText(getContext(), "Téléchargement annulé en raison d'une erreur", Toast.LENGTH_SHORT).show();
            }
        });
        AppVolleySingleton.getInstance(getContext()).addToRequestQueue(customArrayRequest);
    }

    public void communeLocaliteContactRequest(String url) {
        CustomArrayRequest customArrayRequest = new CustomArrayRequest(Request.Method.GET, url + paramCieID, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                //delete
                communeLocaliteContactViewModel.deleteCommuneLocaliteContact();

                //download
                for (CommuneLocaliteContact communeLocaliteContact : CommuneLocaliteContact.fromJSON(response)) {
                    communeLocaliteContactViewModel.insertCommuneLocaliteContact(communeLocaliteContact);
                }
                //dismiss dialog
                hideDialog();
                Toast.makeText(getContext(), "Téléchargement CLC terminé", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //dismiss dialog
                hideDialog();
                Toast.makeText(getContext(), "Téléchargement CLC annulé en raison d'une erreur", Toast.LENGTH_SHORT).show();
            }
        });
        AppVolleySingleton.getInstance(getContext()).addToRequestQueue(customArrayRequest);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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

    public void fromAsyncPaListData() {
        paContactViewModel.setPacontactListListener(new PaContactRepository.AllPacontactListListener() {
            @Override
            public void onPaContactClick(List<PaContact> paContactList) {
                paContactArrayList = new ArrayList<>();
                if (paContactList != null)
                    paContactArrayList.addAll(paContactList);
            }
        });
        produitViewModel.setProduiIdListener(new ProduitRepository.ProduiIdListener() {
            @Override
            public void onProduitIdClick(List<Integer> produitIdList) {
                produitArrayList = new ArrayList<>();
                if (produitIdList != null)
                    produitArrayList.addAll(produitIdList);
            }
        });
    }

    ///////////////////////////////////////////////////   Send  Contact Etablissement    ///////////////////////////////////////////////////////////////
    public void fromAsyncReposeCreateJsonString() {
        sendContactEtabList = new ArrayList<>();
        // Etab Existant Async Response
        etablissementViewModel.setOnOldEtadListener(new EtablissementRepository.OnOldEtadListener() {
            @Override
            public void getOldEtab(List<OldEtablissement> contactsIds) {
                if (contactsIds != null) {
                    oldEtabs = new ArrayList<>();
                    oldEtabs.addAll(contactsIds);
                }
                // Toast.makeText(getContext(), "etab Existant ok", Toast.LENGTH_SHORT).show();
            }
        });
        //New Etab Async Response
        etablissementViewModel.setOnNewEtabListener(new EtablissementRepository.OnNewEtabListener() {
            @Override
            public void getNewEtab(List<JoinNewEtabNewContact> newEtabs) {
                if (newEtabs != null) {
                    newEtab = new ArrayList<>();
                    newEtab.addAll(newEtabs);
                }
                // Toast.makeText(getContext(), "new etab  ok", Toast.LENGTH_SHORT).show();
            }
        });

        //contact Async Response
        contactViewModel.setOnNewContactListener(new ContactRepository.OnNewcontactListener() {
            @Override
            public void newContact(List<Contact> contacts) {
                if (contacts != null && oldEtabs != null && newEtab != null) {
                    if (contacts.size() > 0) {
                        sendContactEtabList.clear();
                        for (Contact contact : contacts) {
                            etabExistant = new ArrayList<>();
                            newEtabList = new ArrayList<>();
                            sendNewContactEtabs = new SendNewContactEtabs();
                            sendNewContactEtabs.setTitre(contact.getTitre());
                            sendNewContactEtabs.setNom(contact.getNom());
                            sendNewContactEtabs.setPrenom(contact.getPrenom());
                            sendNewContactEtabs.setNature(contact.getNature());
                            sendNewContactEtabs.setSecteur(contact.getSecteur());
                            sendNewContactEtabs.setSpecialite(contact.getSpecialite());
                            sendNewContactEtabs.setForce(contact.getForce());
                            sendNewContactEtabs.setPhone1(contact.getPhone1());
                            sendNewContactEtabs.setPhone2(contact.getPhone2());
                            sendNewContactEtabs.setPhone3(contact.getPhone3());
                            sendNewContactEtabs.setEmail(contact.getEmail());
                            ///////////////////////////////////////////////
                            // fill up etab exitant
                            if (oldEtabs.size() > 0) {
                                for (OldEtablissement old : oldEtabs) {
                                    if (old.getContact_id() == contact.getContactId()) {
                                        //System.out.println("--test " + oldEtabs.size());
                                        etabExistant.add(old.getEtab_id());
                                    }
                                }
                            }
                            sendNewContactEtabs.setEtabsExistants(etabExistant);
                            // fill up new Etab
                            if (newEtab.size() > 0) {
                                for (JoinNewEtabNewContact newEta : newEtab) {
                                    if (newEta.getContact_id() == contact.getContactId()) {
                                        //System.out.println("--test " + oldEtabs.size());
                                        newEtabList.add(newEta);
                                    }
                                }
                            }
                            sendNewContactEtabs.setEtabsNouveaux(newEtabList);
                            /////////////////////////////////////////////
                            sendNewContactEtabs.setCreePar(contact.getCree_par());

                            sendNewContactEtabs.setCreeLe(contact.getCree_le());

                            sendNewContactEtabs.setModifiePar(contact.getModifie_par());

                            sendNewContactEtabs.setModifieLe(contact.getModifie_le());

                            sendNewContactEtabs.setTransferePar(contact.getTransfere_par());

                            sendNewContactEtabs.setTransfereLe(transfere_le);

                            sendContactEtabList.add(sendNewContactEtabs);
                        }
                        // Log.d("---test", "" + sendContactEtabList.size());
                        Log.d("-test", toJSON(sendContactEtabList));
                        syncContactData(toJSON(sendContactEtabList));
                    } else {
                        hideDialog();
                        Toast.makeText(getContext(), "Il n'y pas de nouveau contact", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    hideDialog();
                    Toast.makeText(getContext(), "Il n'y pas de nouveau contact", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    //toJSON
    private String toJSON(Object src) {
        GsonBuilder builder = new GsonBuilder();
        builder.serializeNulls().excludeFieldsWithoutExposeAnnotation();
        Gson gson = builder.setPrettyPrinting().create();
        return gson.toJson(src);
    }

    /**
     * SYNC  CONTACT DATA
     */
    private void syncContactData(final String requestBody) {
        // Formulate the request and handle the response.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppConfig.URL_NEW_CONTACT_REGISTER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Do something with the response
                        if (response.replace("\"", "").equals("Succès")) {
                            // update after sync "Succès"
                            newContactEtabViewModel.deleteNewContactEtabAfterSync();
                            etablissementViewModel.deleteNewEtabsAfterSync();
                            contactViewModel.deleteNewcontactAfterSyncTask();
                            hideDialog();
                            Toast.makeText(getContext(), "Les données s'enregistrent avec succès sur le serveur", Toast.LENGTH_SHORT).show();
                            Log.d("volley ok", response.replace("\"", ""));
                            Log.d("volley ok", response);
                        } else {
                            hideDialog();
                            Toast.makeText(getContext(), "L'enregistrement a échoué!", Toast.LENGTH_SHORT).show();
                            Log.d("volley fail", response);
                            Log.d("volley fail", response.replace("\"", ""));
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                        if (error instanceof AuthFailureError) {
                            //dismiss dialog
                            hideDialog();
                            Toast.makeText(getContext(), "Username or Password  or Code Mobile incorrect :", Toast.LENGTH_SHORT).show();
                        } else if (error instanceof NetworkError) {
                            //dismiss dialog
                            hideDialog();
                            Toast.makeText(getContext(), "Network Error! Can't reach https://disprophar.net", Toast.LENGTH_SHORT).show();
                        } else if (error instanceof ParseError) {
                            //dismiss dialog
                            hideDialog();
                            Toast.makeText(getContext(), "JSON Parse Error!", Toast.LENGTH_SHORT).show();
                        } else if (error instanceof ServerError) {
                            //dismiss dialog
                            hideDialog();
                            Toast.makeText(getContext(), "https://disprophar.net responded with an error response", Toast.LENGTH_SHORT).show();
                        } else if (error instanceof TimeoutError) {
                            //dismiss dialog
                            hideDialog();
                            Toast.makeText(getContext(), "Connection or the socket timed out", Toast.LENGTH_SHORT).show();
                        } else {
                            hideDialog();
                            Toast.makeText(getContext(), "Volley Error: " + error.toString(), Toast.LENGTH_SHORT).show();
                            Log.e("Volley Error", error.toString());
                        }
                    }
                }) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return requestBody == null ? null : requestBody.getBytes("utf-8");
                } catch (UnsupportedEncodingException exception) {
                    Log.e("ERROR", "exception", exception);
                    return null;
                }
            }
        };
        AppVolleySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);
    }

    //////////////////////////////////////////////////////////////////// Send GH data //////////////////////////////////////////////////////////////////////

    public void fromGHAsyncTask() {
        final ArrayList<SendGH> sendGHArrayList = new ArrayList<>();

        ghJourContactProduitViewModel.setOnGHJourContactProduitListener(new GHJourContactProduitRepository.OnGHJourContactProduitListener() {
            @Override
            public void OnGHJourContactProduit(List<GHJourContactProduit> ghJourContactProduits) {
                if (ghJourContactProduits != null) {
                    ghJourContactProduitList = new ArrayList<>();
                    ghJourContactProduitList.addAll(ghJourContactProduits);
                }
            }
        });

        ghJourContactViewModel.setOnGHJourContactListeListener(new GHJourContactRepository.OnGHJourContactListeListener() {
            @Override
            public void onGHJourContact(List<GHJourContact> ghJourContactList) {
                if (ghJourContactList != null) {
                    ghJourContacts = new ArrayList<>();
                    ghJourContacts.addAll(ghJourContactList);
                }
            }
        });


        ghJourViewModel.setOnGHJourListListener(new GHJourRepository.OnGHJourListListener() {
            @Override
            public void onGHJourListe(List<GHJour> ghJourList) {
                if (ghJourList != null) {
                    ghJours = new ArrayList<>();
                    ghJours.addAll(ghJourList);
                }
            }
        });

        ghViewModel.setOnGHListListener(new GHRepository.OnGHListListener() {
            @Override
            public void onGHListe(List<GH> ghList) {
                if (ghList != null) {
                    if (ghList.size() > 0) {
                        sendGHArrayList.clear();
                        for (GH gh : ghList) {
                            sendGH = new SendGH();
                            sendGH.setGhId(gh.getGhId());
                            sendGH.setPaId(gh.getPaId());
                            sendGH.setDebut(gh.getDebut());
                            sendGH.setFin(gh.getFin());
                            sendGH.setCreePar(gh.getCreePar());
                            sendGH.setCreePle(gh.getCreePle());
                            sendGH.setModifiePar(gh.getModifiePar());
                            sendGH.setModifieLe(gh.getModifieLe());
                            ////////////////////////////////////////////////////////////////////////
                            switch (statutTrans) {
                                case "ALL":
                                    //cas GH complete
                                    if (gh.getGhComplete()) {
                                        sendGH.setTransferePar(userSessionPreferences.getUserDetails());
                                        sendGH.setTransfereLe(transfere_le);
                                        sendGH.setCompletePar(gh.getCompletePar());
                                        sendGH.setCompleteLe(gh.getCompleteLe());
                                        sendGH.setGhComplete(true);
                                    } else {
                                        sendGH.setTransferePar(userSessionPreferences.getUserDetails());
                                        sendGH.setTransfereLe(transfere_le);
                                        sendGH.setCompletePar(userSessionPreferences.getUserDetails());
                                        sendGH.setCompleteLe(transfere_le);
                                        sendGH.setGhComplete(false);
                                    }
                                    break;
                                case "COURANT":
                                    if (gh.getStatutTemporel().equals("COURANT")) {
                                        //cas GH complete
                                        if (gh.getGhComplete()) {
                                            sendGH.setTransferePar(userSessionPreferences.getUserDetails());
                                            sendGH.setTransfereLe(transfere_le);
                                            sendGH.setCompletePar(gh.getCompletePar());
                                            sendGH.setCompleteLe(gh.getCompleteLe());
                                            sendGH.setGhComplete(true);
                                        } else {
                                            sendGH.setTransferePar(userSessionPreferences.getUserDetails());
                                            sendGH.setTransfereLe(transfere_le);
                                            sendGH.setCompletePar(userSessionPreferences.getUserDetails());
                                            sendGH.setCompleteLe(transfere_le);
                                            sendGH.setGhComplete(false);
                                        }
                                    } else {
                                        //cas GH complete
                                        if (gh.getGhComplete()) {
                                            sendGH.setTransferePar(userSessionPreferences.getUserDetails());
                                            sendGH.setTransfereLe(transfere_le);
                                            sendGH.setCompletePar(gh.getCompletePar());
                                            sendGH.setCompleteLe(gh.getCompleteLe());
                                            sendGH.setGhComplete(true);
                                        } else {
                                            sendGH.setTransferePar(userSessionPreferences.getUserDetails());
                                            sendGH.setTransfereLe(transfere_le);
                                            sendGH.setCompletePar(gh.getCompletePar());
                                            sendGH.setCompleteLe(gh.getCompleteLe());
                                            sendGH.setGhComplete(false);
                                        }
                                    }

                                    break;
                                case "PROCHAIN":
                                    if (!gh.getStatutTemporel().equals("COURANT")) {
                                        //cas GH complete
                                        if (gh.getGhComplete()) {
                                            sendGH.setTransferePar(userSessionPreferences.getUserDetails());
                                            sendGH.setTransfereLe(transfere_le);
                                            sendGH.setCompletePar(gh.getCompletePar());
                                            sendGH.setCompleteLe(gh.getCompleteLe());
                                            sendGH.setGhComplete(true);
                                        } else {
                                            sendGH.setTransferePar(userSessionPreferences.getUserDetails());
                                            sendGH.setTransfereLe(transfere_le);
                                            sendGH.setCompletePar(userSessionPreferences.getUserDetails());
                                            sendGH.setCompleteLe(transfere_le);
                                            sendGH.setGhComplete(false);
                                        }
                                    } else {
                                        //cas GH complete
                                        if (gh.getGhComplete()) {
                                            sendGH.setTransferePar(userSessionPreferences.getUserDetails());
                                            sendGH.setTransfereLe(transfere_le);
                                            sendGH.setCompletePar(gh.getCompletePar());
                                            sendGH.setCompleteLe(gh.getCompleteLe());
                                            sendGH.setGhComplete(true);
                                        } else {
                                            sendGH.setTransferePar(userSessionPreferences.getUserDetails());
                                            sendGH.setTransfereLe(transfere_le);
                                            sendGH.setCompletePar(gh.getCompletePar());
                                            sendGH.setCompleteLe(gh.getCompleteLe());
                                            sendGH.setGhComplete(false);
                                        }
                                    }
                                    break;
                                default:
                                    //cas GH complete
                                    if (gh.getGhComplete()) {
                                        sendGH.setTransferePar(userSessionPreferences.getUserDetails());
                                        sendGH.setTransfereLe(transfere_le);
                                        sendGH.setCompletePar(gh.getCompletePar());
                                        sendGH.setCompleteLe(gh.getCompleteLe());
                                        sendGH.setGhComplete(true);
                                    } else {
                                        sendGH.setTransferePar(userSessionPreferences.getUserDetails());
                                        sendGH.setTransfereLe(transfere_le);
                                        sendGH.setCompletePar(gh.getCompletePar());
                                        sendGH.setCompleteLe(gh.getCompleteLe());
                                        sendGH.setGhComplete(false);
                                    }
                                    break;
                            }

                            ///////////////////////////////////////////////////////////////////////
                            sendGH.setRowVersion(gh.getRowVersion());
                            sendGH.setIntrowVersion(gh.getIntrowVersion());
                            //logic
                            if (ghJours.size() > 0) {
                                ArrayList<GHJour> ghJourArrayList = new ArrayList<>();
                                for (GHJour ghJour : ghJours) {
                                    if (gh.getGhId().equals(ghJour.getGhId())) {
                                        ArrayList<GHJourContact> ghJourContactArrayList = new ArrayList<>();
                                        for (GHJourContact ghJourContact : ghJourContacts) {
                                            if (ghJour.getJour().equals(ghJourContact.getJour())) {
                                                ghJourContactArrayList.add(ghJourContact);
                                                ArrayList<GHJourContactProduit> ghJourContactProduitArrayList = new ArrayList<>();
                                                for (GHJourContactProduit produit : ghJourContactProduitList) {
                                                    if (ghJourContact.getGhId().equals(produit.getGhId()) && ghJourContact.getJour().equals(produit.getJour()) && ghJourContact.getConId().equals(produit.getConId())) {
                                                        ghJourContactProduitArrayList.add(produit);
                                                    }
                                                }
                                                ghJourContact.setGhJourContactProduitList(ghJourContactProduitArrayList);
                                            }
                                        }
                                        ghJour.setGhJourContactList(ghJourContactArrayList);
                                        ghJourArrayList.add(ghJour);
                                    }

                                }
                                sendGH.setGhJourList(ghJourArrayList);
                            }
                            ////
                            sendGHArrayList.add(sendGH);
                        }
                        // Log.d("-test", toJSON(sendGH));
                        toJsonInternalLog(toJSON(sendGHArrayList));
                        // hideDialog();
                        syncGHData(toJSON(sendGHArrayList));
                    }
                }

            }
        });

    }

    public void toJsonInternalLog(String text) {
        try {
            FileOutputStream fos = getActivity().openFileOutput("toJsonLog", Context.MODE_PRIVATE);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));
            writer.write(text);
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * SYNC  GH DATA
     */
    private void syncGHData(final String requestBody) {
        // Formulate the request and handle the response.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppConfig.URL_TRANSFERT_GH,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("----volley", response);
                        // Do something with the response
                        if (response.equals("Succès")) {
                            // update after sync "Succès"*/

                            //delete GH
                            communeLocaliteContactViewModel.deleteCommuneLocaliteContact();
                            ghJourContactProduitViewModel.deleteGHJourContactProduit();
                            ghJourContactViewModel.deleteGHJourContact();
                            ghJourViewModel.deleteGHJour();
                            ghViewModel.deleteGH();

                            //download GH
                            ghRequest(AppConfig.URL_GH);
                            statutJourRequest(AppConfig.URL_STATUT_JOUR);
                            statutVisiteRequest(AppConfig.URL_STATUT_VISITE);
                            //  acceptabiliteRequest(AppConfig.URL_ACCEPTABILITE);
                            communeLocaliteContactRequest(AppConfig.URL_COMMUNE_LOCALITE_CONTACT);

                            hideDialog();
                            Toast.makeText(getContext(), response, Toast.LENGTH_LONG).show();
                            Log.d("----volleyOK", response);
                        } else {
                            hideDialog();
                            // Toast.makeText(getContext(), "L'enregistrement a échoué!", Toast.LENGTH_SHORT).show();
                            Toast.makeText(getContext(), response, Toast.LENGTH_LONG).show();
                            Log.d("volley fail", response);
                            Log.d("volley fail", response.replace("\"", ""));
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                        if (error instanceof AuthFailureError) {
                            //dismiss dialog
                            hideDialog();
                            Toast.makeText(getContext(), "Username or Password  or Code Mobile incorrect :", Toast.LENGTH_SHORT).show();
                        } else if (error instanceof NetworkError) {
                            //dismiss dialog
                            hideDialog();
                            Toast.makeText(getContext(), "Network Error! Can't reach https://disprophar.net", Toast.LENGTH_SHORT).show();
                        } else if (error instanceof ParseError) {
                            //dismiss dialog
                            hideDialog();
                            Toast.makeText(getContext(), "JSON Parse Error!", Toast.LENGTH_SHORT).show();
                        } else if (error instanceof ServerError) {
                            //dismiss dialog
                            hideDialog();
                            Toast.makeText(getContext(), "https://disprophar.net responded with an error response", Toast.LENGTH_SHORT).show();
                        } else if (error instanceof TimeoutError) {
                            //dismiss dialog
                            hideDialog();
                            Toast.makeText(getContext(), "Connection or the socket timed out", Toast.LENGTH_SHORT).show();
                        } else {
                            hideDialog();
                            Toast.makeText(getContext(), "Volley Error: " + error.toString(), Toast.LENGTH_SHORT).show();
                            Log.e("Volley Error", error.toString());
                        }
                    }
                }) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return requestBody == null ? null : requestBody.getBytes("utf-8");
                } catch (UnsupportedEncodingException exception) {
                    Log.e("ERROR", "exception", exception);
                    return null;
                }
            }
        };
        AppVolleySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);
    }

    public void alertDialog() {
        final CharSequence[] sequences = {"Courant", "Prochain"};
        final boolean[] choiceInitial = {false, false};
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext())
                .setTitle("Test")
                .setNegativeButton("Cancel", null)
                .setMultiChoiceItems(sequences, choiceInitial, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        for (int i = 0; i < sequences.length; i++) {
                            if ((0 == i) && choiceInitial[i])
                                isCheckedCourant = true;
                            else if ((1 == i) && choiceInitial[i])
                                isCheckedProchain = true;
                        }
                    }
                })
                .setPositiveButton("Accept", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (isCheckedCourant && !isCheckedProchain) {
                            Toast.makeText(getContext(), "Complété GH Courant", Toast.LENGTH_SHORT).show();
                            statutTrans = "COURANT";
                            isCheckedCourant = false;
                        } else if (!isCheckedCourant && isCheckedProchain) {
                            Toast.makeText(getContext(), "Complété GH Prochain", Toast.LENGTH_SHORT).show();
                            statutTrans = "PROCHAIN";
                            isCheckedProchain = false;
                        } else if (isCheckedCourant && isCheckedProchain) {
                            Toast.makeText(getContext(), "Complété GH Courant et Prochain", Toast.LENGTH_SHORT).show();
                            statutTrans = "ALL";
                            isCheckedCourant = isCheckedProchain = false;
                        } else {
                            Toast.makeText(getContext(), "Aucun GH Complété", Toast.LENGTH_SHORT).show();
                            statutTrans = "AUCUN";
                        }

                        // Toast.makeText(getContext(), "Statut Transert:" + statutTrans, Toast.LENGTH_SHORT).show();

                        //set title of the dialog
                        pDialog.setTitle("synchronisation des données du GH ...");
                        //set message of the dialog
                        pDialog.setMessage("S'il vous plaît, attendez...");
                        //show dialog
                        showDialog();

                        // Async GHJourContactProduit list
                        ghJourContactProduitViewModel.getGHJourContactProduit();
                        //Async GH Jour Contact list
                        ghJourContactViewModel.getGHJourContactList();
                        //Async GH Jour list
                        ghJourViewModel.getGHJourList();
                        //Async GH list
                        ghViewModel.getGHList();

                    }
                });
        alertDialogBuilder.show();
    }


}
