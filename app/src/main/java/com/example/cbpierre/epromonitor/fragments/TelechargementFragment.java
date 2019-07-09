package com.example.cbpierre.epromonitor.fragments;


import android.app.ProgressDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
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
import com.example.cbpierre.epromonitor.models.JoinNewEtabNewContact;
import com.example.cbpierre.epromonitor.models.Nature;
import com.example.cbpierre.epromonitor.models.OldEtablissement;
import com.example.cbpierre.epromonitor.models.Secteur;
import com.example.cbpierre.epromonitor.models.SendNewContactEtabs;
import com.example.cbpierre.epromonitor.models.Specialite;
import com.example.cbpierre.epromonitor.models.Titre;
import com.example.cbpierre.epromonitor.models.Zone;
import com.example.cbpierre.epromonitor.repositories.ContactRepository;
import com.example.cbpierre.epromonitor.repositories.EtablissementRepository;
import com.example.cbpierre.epromonitor.repositories.NatureRepository;
import com.example.cbpierre.epromonitor.repositories.SecteurRepository;
import com.example.cbpierre.epromonitor.repositories.SpecialiteRepository;
import com.example.cbpierre.epromonitor.viewModels.ContactEtablissementViewModel;
import com.example.cbpierre.epromonitor.viewModels.ContactViewModel;
import com.example.cbpierre.epromonitor.viewModels.EtablissementViewModel;
import com.example.cbpierre.epromonitor.viewModels.ForceViewModel;
import com.example.cbpierre.epromonitor.viewModels.NatureViewModel;
import com.example.cbpierre.epromonitor.viewModels.NewContactEtabViewModel;
import com.example.cbpierre.epromonitor.viewModels.SecteurViewModel;
import com.example.cbpierre.epromonitor.viewModels.SpecialiteViewModel;
import com.example.cbpierre.epromonitor.viewModels.TitreViewModel;
import com.example.cbpierre.epromonitor.viewModels.ZoneViewModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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

    private ArrayList<OldEtablissement> oldEtabs;
    private ArrayList<JoinNewEtabNewContact> newEtab;

    private Button btnDownloadContact, btnSyncContact, btnDownloadEtablissement, btnSyncEtablissement;
    private List<SendNewContactEtabs> sendContactEtabList;
    private SendNewContactEtabs sendNewContactEtabs;
    private ProgressDialog pDialog;

    private ArrayList<Integer> etabExistant;
    private ArrayList<JoinNewEtabNewContact> newEtabList;


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
        newContactEtabViewModel = ViewModelProviders.of(this).get(NewContactEtabViewModel.class);
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
        btnSyncContact = view.findViewById(R.id.btnSyncContactData);

        /**
         * Contact button
         */
        btnDownloadContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //set title of the dialog
                pDialog.setTitle("Obtenir les données de contact du serveur  ...");
                //set message of the dialog
                pDialog.setMessage("S'il vous plaît, attendez...");
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
         * Async Response
         */
        fromAsyncReposeCreateJsonString();
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
                Toast.makeText(getContext(), "Téléchargement annulé en raison d'une erreur", Toast.LENGTH_LONG).show();
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
                Toast.makeText(getContext(), "Téléchargement annulé en raison d'une erreur", Toast.LENGTH_LONG).show();
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
                Toast.makeText(getContext(), "Téléchargement annulé en raison d'une erreur", Toast.LENGTH_LONG).show();
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
                Toast.makeText(getContext(), "Téléchargement annulé en raison d'une erreur", Toast.LENGTH_LONG).show();
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
                Toast.makeText(getContext(), "Téléchargement annulé en raison d'une erreur", Toast.LENGTH_LONG).show();
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
                Toast.makeText(getContext(), "Téléchargement annulé en raison d'une erreur", Toast.LENGTH_LONG).show();
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
                Toast.makeText(getContext(), "Téléchargement terminé", Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //dismiss dialog
                hideDialog();
                Toast.makeText(getContext(), "Téléchargement annulé en raison d'une erreur", Toast.LENGTH_LONG).show();
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
                Toast.makeText(getContext(), "Téléchargement terminé", Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //dismiss dialog
                hideDialog();
                Toast.makeText(getContext(), "Téléchargement annulé en raison d'une erreur", Toast.LENGTH_LONG).show();
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
                if (contacts != null) {
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

                            sendNewContactEtabs.setTransfereLe(contact.getTransfere_le());

                            sendContactEtabList.add(sendNewContactEtabs);
                        }
                        // Log.d("---test", "" + sendContactEtabList.size());
                        Log.d("-test", toJSON(sendContactEtabList));
                        syncContactData(toJSON(sendContactEtabList));
                    }
                } else
                    hideDialog();
                Toast.makeText(getContext(), "Il n'y pas de nouveau contact", Toast.LENGTH_SHORT).show();
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
                        if (response.equals("\"Succès\"")) {
                            // update after sync "Succès"
                            newContactEtabViewModel.deleteNewContactEtabAfterSync();
                            etablissementViewModel.deleteNewEtabsAfterSync();
                            contactViewModel.deleteNewcontactAfterSyncTask();
                            hideDialog();
                            Toast.makeText(getContext(), "Les données s'enregistrent avec succès sur le serveur", Toast.LENGTH_LONG).show();
                            Log.d("volley", response);
                        } else {
                            hideDialog();
                            Toast.makeText(getContext(), "L'enregistrement a échoué!", Toast.LENGTH_LONG).show();
                            Log.d("volley", response);
                            Log.d("volley", "\"Succès\"");
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
                            Toast.makeText(getContext(), "Connection or the socket timed out", Toast.LENGTH_LONG).show();
                        } else {
                            hideDialog();
                            Toast.makeText(getContext(), "Volley Error: " + error.toString(), Toast.LENGTH_LONG).show();
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
}
