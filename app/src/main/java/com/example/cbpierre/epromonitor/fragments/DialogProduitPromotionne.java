package com.example.cbpierre.epromonitor.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cbpierre.epromonitor.R;
import com.example.cbpierre.epromonitor.adapters.AcceptabiliteSpinnerAdapter;
import com.example.cbpierre.epromonitor.adapters.ContactProduitSpinnerAdapter;
import com.example.cbpierre.epromonitor.models.AcceptabiliteRef;
import com.example.cbpierre.epromonitor.models.GHJourContactProduit;
import com.example.cbpierre.epromonitor.models.JoinContactGhSV;
import com.example.cbpierre.epromonitor.models.JoinProduitAcceptabliliteGHProduit;
import com.example.cbpierre.epromonitor.models.Produit;
import com.example.cbpierre.epromonitor.viewModels.AcceptabiliteViewModel;
import com.example.cbpierre.epromonitor.viewModels.GHJourContactProduitViewModel;
import com.example.cbpierre.epromonitor.viewModels.PaContactProduitViewModel;
import com.example.cbpierre.epromonitor.viewModels.ShareJoinContactGhSV;

import java.util.List;

public class DialogProduitPromotionne extends DialogFragment {
    private PaContactProduitViewModel paContactProduitViewModel;
    private AcceptabiliteViewModel acceptabiliteViewModel;
    private GHJourContactProduitViewModel ghJourContactProduitViewModel;
    private ShareJoinContactGhSV shareJoinContactGhSV;
    private ContactProduitSpinnerAdapter contactProduitSpinnerAdapter;
    private AcceptabiliteSpinnerAdapter acceptabiliteSpinnerAdapter;
    private Spinner spProduitPromotionne, spAcceptabilite;
    private Button submit, cancel;
    private EditText etNoteProduitPromo;
    private JoinContactGhSV contactGhSV;
    private int produit_id;
    private String acceptabiiteCode;
    private LinearLayout llAcceptabilite;

    private OnSubmitProduitPromotionneLister produitPromotionneLister;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        paContactProduitViewModel = ViewModelProviders.of(this).get(PaContactProduitViewModel.class);
        acceptabiliteViewModel = ViewModelProviders.of(this).get(AcceptabiliteViewModel.class);
        ghJourContactProduitViewModel = ViewModelProviders.of(this).get(GHJourContactProduitViewModel.class);
        shareJoinContactGhSV = ViewModelProviders.of(getActivity()).get(ShareJoinContactGhSV.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_produit_promotionne, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        spProduitPromotionne = view.findViewById(R.id.spProduitPromotionne);
        spAcceptabilite = view.findViewById(R.id.spAcceptabilite);
        submit = view.findViewById(R.id.btnSubmitProduit);
        cancel = view.findViewById(R.id.btnCancelProduit);
        etNoteProduitPromo = view.findViewById(R.id.etProduitPromotionneNote);
        llAcceptabilite = view.findViewById(R.id.llAcceptabilite);

        llAcceptabilite.setVisibility(View.GONE);
        etNoteProduitPromo.setVisibility(View.GONE);

        shareJoinContactGhSV.getShareJoinContactGhSV().observe(this, new Observer<JoinContactGhSV>() {
            @Override
            public void onChanged(@Nullable JoinContactGhSV joinContactGhSV) {
                if (joinContactGhSV != null) {
                    contactGhSV = joinContactGhSV;
                    //get gh Jour Contact Produit Promotionne
                    ghJourContactProduitViewModel.setGhJouContactProduitParamMutable(joinContactGhSV.getGh_id(), joinContactGhSV.getCon_id(), joinContactGhSV.getJour());
                }
            }
        });
        getSelectedItem();
        populateSpinner();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (llAcceptabilite.getVisibility() == View.VISIBLE) {
                    if (isValidate()) {
                        ghJourContactProduitViewModel.insertGHJourContactProduit(new GHJourContactProduit(contactGhSV.getGh_id(), contactGhSV.getJour(), contactGhSV.getCon_id(), produit_id, acceptabiiteCode, etNoteProduitPromo.getText().toString()));
                        dismiss();
                    }
                } else
                    Toast.makeText(getContext(), "aucun produit sélectionné", Toast.LENGTH_LONG).show();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    public void populateSpinner() {
        //spinner Observer
        ghJourContactProduitViewModel.getAllGhJourContactProduit().observe(this, new Observer<List<Produit>>() {
            @Override
            public void onChanged(@Nullable List<Produit> produits) {
                if (produits != null) {
                    if (!produits.contains(new Produit(null, getResources().getString(R.string.select_produit_promotionne))))
                        produits.add(0, new Produit(null, getResources().getString(R.string.select_produit_promotionne)));
                    contactProduitSpinnerAdapter = new ContactProduitSpinnerAdapter(getContext(), R.layout.spinner_rows, produits);
                }
                contactProduitSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spProduitPromotionne.setAdapter(contactProduitSpinnerAdapter);
            }
        });
        acceptabiliteViewModel.getAllAcceptabiliteRef().observe(this, new Observer<List<AcceptabiliteRef>>() {
            @Override
            public void onChanged(@Nullable List<AcceptabiliteRef> acceptabiliteRefs) {
                if (acceptabiliteRefs != null) {
                    acceptabiliteRefs.add(0, new AcceptabiliteRef("00", getResources().getString(R.string.select_acceptabilite), "999"));
                    acceptabiliteSpinnerAdapter = new AcceptabiliteSpinnerAdapter(getContext(), R.layout.spinner_rows, acceptabiliteRefs);
                }
                acceptabiliteSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spAcceptabilite.setAdapter(acceptabiliteSpinnerAdapter);
            }
        });
    }

    public void getSelectedItem() {
        spProduitPromotionne.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Produit produit = (Produit) parent.getItemAtPosition(position);
                if (produit.getNomProduit().equals("-- SELECTIONNER PRODUIT PROMOTIONNE --")) {
                    TextView textView = (TextView) view;
                    textView.setTextColor(getResources().getColor(R.color.input_login_hint));
                    llAcceptabilite.setVisibility(View.GONE);
                    etNoteProduitPromo.setVisibility(View.GONE);
                } else {
                    llAcceptabilite.setVisibility(View.VISIBLE);
                    etNoteProduitPromo.setVisibility(View.VISIBLE);
                    produit_id = produit.getProduitId();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spAcceptabilite.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                AcceptabiliteRef acceptabiliteRef = (AcceptabiliteRef) parent.getItemAtPosition(position);
                if (acceptabiliteRef.getNom().equals("-- SELECTIONNER ACCEPTABILITE --")) {
                    TextView textView = (TextView) view;
                    textView.setTextColor(getResources().getColor(R.color.input_login_hint));
                } else {
                    acceptabiiteCode = acceptabiliteRef.getCode();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public boolean isValidate() {
        boolean validate = true;
        String statutVisite = ((AcceptabiliteRef) spAcceptabilite.getSelectedItem()).getNom();
        if (statutVisite.equals("-- SELECTIONNER ACCEPTABILITE --")) {
            TextView errorTV = (TextView) spAcceptabilite.getSelectedView();
            errorTV.setError("");
            errorTV.setText("aucun Acceptabilite sélectionné");
            errorTV.setTextColor(Color.RED);
            validate = false;
        }
        return validate;
    }

    public interface OnSubmitProduitPromotionneLister {
        List<JoinProduitAcceptabliliteGHProduit> onSubmitproduit();
    }

    public void setProduitPromotionneLister(OnSubmitProduitPromotionneLister produitPromotionneLister) {
        this.produitPromotionneLister = produitPromotionneLister;
    }
}


