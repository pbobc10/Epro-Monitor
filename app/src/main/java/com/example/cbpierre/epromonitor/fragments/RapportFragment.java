package com.example.cbpierre.epromonitor.fragments;

import android.app.TimePickerDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.cbpierre.epromonitor.R;
import com.example.cbpierre.epromonitor.UserSessionPreferences;
import com.example.cbpierre.epromonitor.adapters.ContactEtabSpinnerAdapter;
import com.example.cbpierre.epromonitor.adapters.JoinProduitAcceptabliliteGHProduitAdapter;
import com.example.cbpierre.epromonitor.adapters.StatutVisiteSpinnerAdapter;
import com.example.cbpierre.epromonitor.models.JoinContactEtablissementData;
import com.example.cbpierre.epromonitor.models.JoinContactGhSV;
import com.example.cbpierre.epromonitor.models.JoinProduitAcceptabliliteGHProduit;
import com.example.cbpierre.epromonitor.models.StatutVisiteRef;
import com.example.cbpierre.epromonitor.viewModels.ContactEtablissementViewModel;
import com.example.cbpierre.epromonitor.viewModels.GHJourContactProduitViewModel;
import com.example.cbpierre.epromonitor.viewModels.GHJourContactViewModel;
import com.example.cbpierre.epromonitor.viewModels.ShareJoinContactGhSV;
import com.example.cbpierre.epromonitor.viewModels.StatutVisiteViewModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RapportFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class RapportFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private JoinContactGhSV contactGhSV;
    private StatutVisiteViewModel statutVisiteViewModel;
    private GHJourContactViewModel ghJourContactViewModel;
    private ContactEtablissementViewModel contactEtablissementViewModel;
    private GHJourContactProduitViewModel ghJourContactProduitViewModel;
    private ShareJoinContactGhSV shareJoinContactGhSV;
    private Spinner spStatutVisite, spEtabContact;
    private EditText etRapport;
    private Button btnSoumettre, btnAnnuler;
    private UserSessionPreferences userSessionPreferences;
    private String creePar, creeLe, modifiePar, modifieLe;
    private TextView txtTitreRapport, txtRapportJourComplete, txtVisiteADrX;
    private EditText etDebut, etFin, etAutreLieu;
    private LinearLayout llHeures, llActivities, llButtonReport, llProduitPromotionne;
    private Button btnProduitPromotionne;
    private CheckBox cbPromotion, cbLivraison, cbRecouvrement, cbAutre;

    private ContactEtabSpinnerAdapter contactEtabSpinnerAdapter;
    private JoinProduitAcceptabliliteGHProduitAdapter ghProduitAdapter;
    private TimePickerDialog pickerDialog;
    private RecyclerView rvProduitPromotionne;


    public RapportFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        statutVisiteViewModel = ViewModelProviders.of(this).get(StatutVisiteViewModel.class);
        ghJourContactViewModel = ViewModelProviders.of(this).get(GHJourContactViewModel.class);
        contactEtablissementViewModel = ViewModelProviders.of(this).get(ContactEtablissementViewModel.class);
        ghJourContactProduitViewModel = ViewModelProviders.of(this).get(GHJourContactProduitViewModel.class);
        shareJoinContactGhSV = ViewModelProviders.of(getActivity()).get(ShareJoinContactGhSV.class);
        //SharePreference
        userSessionPreferences = new UserSessionPreferences(getContext());
        creePar = modifiePar = userSessionPreferences.getUserDetails();

        //Date
        String parttern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(parttern);
        creeLe = modifieLe = simpleDateFormat.format(new Date());

        contactGhSV = new JoinContactGhSV();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rapport, container, false);
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
        spStatutVisite = view.findViewById(R.id.spStatutVisite);
        spEtabContact = view.findViewById(R.id.spEtabContact);
        etRapport = view.findViewById(R.id.etRapport);
        btnSoumettre = view.findViewById(R.id.btnSubmitRapport);
        btnAnnuler = view.findViewById(R.id.btnCancelRapport);
        txtTitreRapport = view.findViewById(R.id.txtTitreRapport);
        txtRapportJourComplete = view.findViewById(R.id.txtRapportJourComplete);
        txtVisiteADrX = view.findViewById(R.id.txtVisiteADrX);
        etDebut = view.findViewById(R.id.etDebut);
        etFin = view.findViewById(R.id.etFin);
        etAutreLieu = view.findViewById(R.id.etAutreLieu);
        llActivities = view.findViewById(R.id.llActivites);
        llHeures = view.findViewById(R.id.llHeure);
        btnProduitPromotionne = view.findViewById(R.id.btnProduitPromotionne);
        llButtonReport = view.findViewById(R.id.llButtonReport);
        llProduitPromotionne = view.findViewById(R.id.llProduitPromotionne);
        rvProduitPromotionne = view.findViewById(R.id.rvProduitPromotionne);
        cbAutre = view.findViewById(R.id.cbAutre);
        cbLivraison = view.findViewById(R.id.cbLivraison);
        cbPromotion = view.findViewById(R.id.cbPromotion);
        cbRecouvrement = view.findViewById(R.id.cbRecouvrement);

        etDebut.setInputType(InputType.TYPE_NULL);
        etFin.setInputType(InputType.TYPE_NULL);
        txtRapportJourComplete.setVisibility(View.GONE);
        spEtabContact.setVisibility(View.GONE);
        etAutreLieu.setVisibility(View.GONE);
        llHeures.setVisibility(View.GONE);
        llActivities.setVisibility(View.GONE);
        etRapport.setVisibility(View.GONE);
        llProduitPromotionne.setVisibility(View.GONE);
        llButtonReport.setVisibility(View.GONE);

        shareJoinContactGhSV.getShareJoinContactGhSV().observe(this, new Observer<JoinContactGhSV>() {
            @Override
            public void onChanged(@Nullable JoinContactGhSV joinContactGhSV) {
                contactGhSV = joinContactGhSV;
                etRapport.setText(joinContactGhSV.getNote());
                txtTitreRapport.setText("Rapport à la date du " + date(joinContactGhSV.getJour()));

                //get contact id for the etabs
                contactEtablissementViewModel.setEtabsByContactId(joinContactGhSV.getCon_id());
                //get gh Jour Contact Produit Promotionne
                ghJourContactProduitViewModel.setGhJouContactProduitParamMutable(joinContactGhSV.getGh_id(), joinContactGhSV.getCon_id(), joinContactGhSV.getJour());

                if (joinContactGhSV.getRapport_complete())
                    txtRapportJourComplete.setVisibility(View.VISIBLE);
                //button
                if (joinContactGhSV.getRapport_complete()) {
                    spStatutVisite.setEnabled(false);
                    etRapport.setEnabled(false);
                    btnSoumettre.setEnabled(false);
                    btnAnnuler.setEnabled(false);
                }

                txtVisiteADrX.setText("Visite à " + joinContactGhSV.getNom_ratio());
                //initialize autre lieu
                etAutreLieu.setText(joinContactGhSV.getAutre_lieu());
                //initialize  heure(time) frame
                etDebut.setText(joinContactGhSV.getDebut());
                etFin.setText(joinContactGhSV.getFin());
                //initialize activities(activites)
                cbAutre.setChecked(joinContactGhSV.isAutre());
                cbLivraison.setChecked(joinContactGhSV.isLivraison());
                cbPromotion.setChecked(joinContactGhSV.isPromotion());
                cbRecouvrement.setChecked(joinContactGhSV.isRecouvrement());

            }
        });

        // timePicker
        etDebut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePicker(etDebut);
            }
        });
        etFin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePicker(etFin);
            }
        });
        activitiesChecked();

        populateSpinner();
        getSpinnerItem();
        ghProduitAdapter = new JoinProduitAcceptabliliteGHProduitAdapter(getContext());
        rvProduitPromotionne.setLayoutManager(new LinearLayoutManager(getContext()));
        ghJourContactProduitViewModel.getJoinProduitAcceptabliliteGHProduitt().observe(this, new Observer<List<JoinProduitAcceptabliliteGHProduit>>() {
            @Override
            public void onChanged(@Nullable List<JoinProduitAcceptabliliteGHProduit> joinProduitAcceptabliliteGHProduits) {
                ghProduitAdapter.setJoinProduitAcceptabliliteGHProduitList(joinProduitAcceptabliliteGHProduits);
            }
        });
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        rvProduitPromotionne.setAdapter(ghProduitAdapter);
        rvProduitPromotionne.addItemDecoration(dividerItemDecoration);

        //delete gh jour contact Produit
        ghProduitAdapter.setOndeleteGHJourContactProduit(new JoinProduitAcceptabliliteGHProduitAdapter.OndeleteGHJourContactProduit() {
            @Override
            public void onDeleteProduit(int ghId, int conId, String jour, int produitId) {
                ghJourContactProduitViewModel.deleteGHJourContactProduitId(ghId, conId, jour, produitId);
            }
        });

        btnSoumettre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValidate()) {
                    //Get the selected item out a spinner using:
                    String codeStatutVisite = ((StatutVisiteRef) spStatutVisite.getSelectedItem()).getCode();
                    String etab = ((JoinContactEtablissementData) spEtabContact.getSelectedItem()).getNom_Etablissement();
                    //insert rapport data
                    ghJourContactViewModel.updateGHJourContact(codeStatutVisite, etRapport.getText().toString(), creePar, creeLe, modifiePar, modifieLe, contactGhSV.getJour(), contactGhSV.getGh_id().toString(), contactGhSV.getCon_id().toString(), Boolean.toString(cbPromotion.isChecked()), Boolean.toString(cbLivraison.isChecked()), Boolean.toString(cbRecouvrement.isChecked()), Boolean.toString(cbAutre.isChecked()), etDebut.getText().toString(), etFin.getText().toString(), etab, etAutreLieu.getText().toString());
                    getActivity().onBackPressed();
                } else {
                    Toast.makeText(getContext(), "l'enregistrement a échoué!!!", Toast.LENGTH_LONG).show();
                }
            }

        });

        btnAnnuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check time
                checkTime(etDebut.getText().toString(), etFin.getText().toString());
                getActivity().onBackPressed();
            }
        });
        btnProduitPromotionne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                DialogProduitPromotionne produitPromotionne = new DialogProduitPromotionne();
                produitPromotionne.show(fragmentManager, "test");

            }
        });
    }

    public void activitiesChecked() {
        //promotion checked
        cbPromotion.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (cbPromotion.isChecked()) {
                    llProduitPromotionne.setVisibility(View.VISIBLE);
                } else {
                    llProduitPromotionne.setVisibility(View.GONE);
                }
            }
        });
    }

    public void timePicker(final EditText editText) {
        final Calendar calendar = Calendar.getInstance();
        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        pickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                // Toast.makeText(getContext(), hourOfDay + ":" + minute, Toast.LENGTH_LONG).show();
                editText.setText(hourOfDay + ":" + minute);
            }
        }, hourOfDay, minute, true);
        pickerDialog.show();
    }

    public void checkTime(String debut, String fin) {
        //float debH = 0f, finH = 0f;
        float debH = debut.equals("") ? 0f : Float.parseFloat((debut.replace(':', '.')));
        float finH = fin.equals("") ? 0f : Float.parseFloat((fin.replace(':', '.')));
        if (debH > finH)
            Toast.makeText(getContext(), "L'Heure de debut droit etre inferieur a l'heure de fin" + ":" + debH, Toast.LENGTH_LONG).show();

    }

    public void populateSpinner() {
        //populate Spinner Adapter
        statutVisiteViewModel.getAllStatutVisite().observe(this, new Observer<List<StatutVisiteRef>>() {
            @Override
            public void onChanged(@Nullable List<StatutVisiteRef> statutVisiteRefs) {
                //add new Statut Visite object
                if (statutVisiteRefs != null) {
                    statutVisiteRefs.add(0, new StatutVisiteRef("ST", "-- SELECTIONNER STATUT VISITE --", "N"));
                }
                StatutVisiteSpinnerAdapter statutVisiteSpinnerAdapter = new StatutVisiteSpinnerAdapter(getContext(), R.layout.spinner_item, statutVisiteRefs);
                statutVisiteSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spStatutVisite.setAdapter(statutVisiteSpinnerAdapter);
                //Setting spinner item based on value (rather than item position):
                for (int i = 0; i < statutVisiteSpinnerAdapter.getCount(); i++) {
                    if (statutVisiteSpinnerAdapter.getItem(i).getCode().equals(contactGhSV.getStatut())) {
                        spStatutVisite.setSelection(i);
                        break;
                    }
                }
            }
        });

        //populate Spinner spEtabContact
        contactEtablissementViewModel.getEtabsByContactId().observe(this, new Observer<List<JoinContactEtablissementData>>() {
            @Override
            public void onChanged(@Nullable List<JoinContactEtablissementData> contactEtablissementData) {
                // add new etablissement object
                if (contactEtablissementData != null) {
                    contactEtablissementData.add(new JoinContactEtablissementData(null, null, "AUTRE", null, null, null, null));
                    contactEtablissementData.add(0, (new JoinContactEtablissementData(null, null, "-- SELECTIONNER UN ETABLISSEMENT --", null, null, null, null)));
                }
                contactEtabSpinnerAdapter = new ContactEtabSpinnerAdapter(getContext(), R.layout.spinner_rows, contactEtablissementData);
                contactEtabSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spEtabContact.setAdapter(contactEtabSpinnerAdapter);
                //Setting spinner item based on value (rather than item position):
                for (int i = 0; i < contactEtabSpinnerAdapter.getCount(); i++) {
                    if (contactEtabSpinnerAdapter.getItem(i).getNom_Etablissement().equals(contactGhSV.getLieu())) {
                        spEtabContact.setSelection(i);
                        break;
                    }
                }
            }
        });
    }

    public void getSpinnerItem() {
        spStatutVisite.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                StatutVisiteRef visiteRef = (StatutVisiteRef) parent.getItemAtPosition(position);
                if (visiteRef.getNom().equals(getResources().getString(R.string.select_statut_visite)) || visiteRef.getCode().equals("VNP") || visiteRef.getCode().equals("VP")) {
                    TextView textView = (TextView) view;
                    textView.setTextColor(getResources().getColor(R.color.input_login_hint));
                    spEtabContact.setVisibility(View.GONE);
                    etAutreLieu.setVisibility(View.GONE);
                    llHeures.setVisibility(View.GONE);
                    llActivities.setVisibility(View.GONE);
                    etRapport.setVisibility(View.GONE);
                    //  llProduitPromotionne.setVisibility(View.GONE);
                    llButtonReport.setVisibility(View.GONE);
                } else if (visiteRef.getCode().equals("VNE") || visiteRef.getCode().equals("VNR")) {
                    spEtabContact.setVisibility(View.GONE);
                    etAutreLieu.setVisibility(View.GONE);
                    llHeures.setVisibility(View.GONE);
                    llActivities.setVisibility(View.GONE);
                    etRapport.setVisibility(View.VISIBLE);
                    //  llProduitPromotionne.setVisibility(View.GONE);
                    llButtonReport.setVisibility(View.VISIBLE);
                } else if (visiteRef.getCode().equals("ABS")) {
                    spEtabContact.setVisibility(View.VISIBLE);
                    etAutreLieu.setVisibility(View.GONE);
                    llHeures.setVisibility(View.GONE);
                    llActivities.setVisibility(View.GONE);
                    etRapport.setVisibility(View.VISIBLE);
                    llButtonReport.setVisibility(View.VISIBLE);
                    //  llProduitPromotionne.setVisibility(View.GONE);
                    //  resetEtabContactSpinner();
                } else if (visiteRef.getCode().equals("CE")) {
                    spEtabContact.setVisibility(View.VISIBLE);
                    etAutreLieu.setVisibility(View.GONE);
                    llHeures.setVisibility(View.VISIBLE);
                    llActivities.setVisibility(View.VISIBLE);
                    etRapport.setVisibility(View.VISIBLE);
                    llButtonReport.setVisibility(View.VISIBLE);
                    // llProduitPromotionne.setVisibility(View.VISIBLE);
                    //  resetEtabContactSpinner();
                } else {
                    spEtabContact.setVisibility(View.GONE);
                    etAutreLieu.setVisibility(View.GONE);
                    llHeures.setVisibility(View.GONE);
                    llActivities.setVisibility(View.GONE);
                    etRapport.setVisibility(View.VISIBLE);
                    llButtonReport.setVisibility(View.VISIBLE);
                    // llProduitPromotionne.setVisibility(View.GONE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spEtabContact.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (view == null)
                    return;

                JoinContactEtablissementData contactEtablissementData = (JoinContactEtablissementData) parent.getItemAtPosition(position);

                if (contactEtablissementData.getNom_Etablissement().equals(getResources().getString(R.string.select_etablissement))) {
                    //change default item color
                    TextView textView = (TextView) view;
                    textView.setTextColor(getResources().getColor(R.color.input_login_hint));
                    etAutreLieu.setVisibility(View.GONE);
                } else if (contactEtablissementData.getNom_Etablissement().equals("AUTRE")) {
                    //Toast.makeText(getContext(), "ok", Toast.LENGTH_LONG).show();
                    etAutreLieu.setVisibility(View.VISIBLE);
                } else {
                    etAutreLieu.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    // item validation
    public boolean isValidate() {
        boolean valid = true;
        String etabli = ((JoinContactEtablissementData) spEtabContact.getSelectedItem()).getNom_Etablissement();
        if (etabli.equals("-- SELECTIONNER UN ETABLISSEMENT --")) {
            TextView errorText = (TextView) spEtabContact.getSelectedView();
            errorText.setError("");
            errorText.setText("Le fileur de l'Etablissement est vide!");
            errorText.setTextColor(Color.RED);//just to highlight that this is an error
            valid = false;
        } else if (etAutreLieu.getVisibility() == View.VISIBLE && etAutreLieu.getText().toString().equals("")) {
            etAutreLieu.setError("Lieu Obligatoire!");
            etAutreLieu.setTextColor(Color.RED);
            valid = false;
        } else if (TextUtils.isEmpty(etRapport.getText().toString()) || (etRapport.getText().toString().equals(""))) {
            etRapport.setError("Note Obligatoire!");
            etRapport.setTextColor(Color.RED);
            valid = false;
        }
        return valid;
    }

    /*   public void resetEtabContactSpinner() {
           //set spinner to selectionner etablissement or reset spEtabContact spinner
           for (int i = 0; i < contactEtabSpinnerAdapter.getCount(); i++) {
               if (contactEtabSpinnerAdapter.getItem(i).getNom_Etablissement().equals("-- SELECTIONNER UN ETABLISSEMENT --")) {
                   spEtabContact.setSelection(i);
                   break;
               }
           }
       }
   */
    public String date(String x) {
        //2019-09-27T00:00:00
        Date dat = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd", Locale.FRENCH);
        try {
            dat = simpleDateFormat.parse(x);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return simpleDateFormat.format(dat);
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
