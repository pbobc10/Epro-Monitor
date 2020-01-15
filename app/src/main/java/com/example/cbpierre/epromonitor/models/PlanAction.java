package com.example.cbpierre.epromonitor.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

@Entity(tableName = "plan_action", indices = @Index(value = "pa_id", name = "plan_action_index", unique = false))
public class PlanAction {
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "pa_id")
    private Integer paId;

    @NonNull
    @ColumnInfo(name = "debut")
    private String debut;

    @NonNull
    @ColumnInfo(name = "fin")
    private String fin;

    @NonNull
    @ColumnInfo(name = "capital_visites")
    private Integer capVisite;

    @NonNull
    @ColumnInfo(name = "total_visites")
    private Integer totalVisite;

    @NonNull
    @ColumnInfo(name = "valide")
    private Boolean valide;

    public PlanAction() {
    }

    public PlanAction(JSONObject jsonObject) {
        try {
            paId = jsonObject.getInt("PAID");
            debut = jsonObject.getString("DEBUT");
            fin = jsonObject.getString("FIN");
            capVisite = jsonObject.getInt("CAPITAL_VISITES");
            totalVisite = jsonObject.getInt("TOTAL_VISITES");
            valide = jsonObject.getBoolean("VALIDE");
        } catch (JSONException e) {
        }
    }

    public static ArrayList<PlanAction> fromJson(JSONArray jsonArray) {
        ArrayList<PlanAction> planActions = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                planActions.add(new PlanAction(jsonArray.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return planActions;
    }

    public static ArrayList<PaContact> fromJsonPAContact(JSONArray jsonArray) {
        ArrayList<PaContact> paContacts = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                for (int j = 0; j < jsonArray.getJSONObject(i).getJSONArray("CONTACTS").length(); j++) {
                    paContacts.add(new PaContact(jsonArray.getJSONObject(i).getJSONArray("CONTACTS").getJSONObject(j)));
                }
            } catch (JSONException e) {
            }

        }
        return paContacts;
    }

    public static ArrayList<PaContactProduit> fromJsonPaContactProduit(JSONArray jsonArray) {
        ArrayList<PaContactProduit> paContactProduits = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                for (int j = 0; j < jsonArray.getJSONObject(i).getJSONArray("CONTACTS").length(); j++) {
                    for (int k = 0; k < jsonArray.getJSONObject(i).getJSONArray("CONTACTS").getJSONObject(j).getJSONArray("PRODUITS").length(); k++) {
                        Log.d("size_array4", "test2");
                        paContactProduits.add(new PaContactProduit(jsonArray.getJSONObject(i).getJSONArray("CONTACTS").getJSONObject(j).getJSONArray("PRODUITS").getJSONObject(k)));
                    }
                }
            } catch (JSONException e) {
            }
        }
        return paContactProduits;
    }

    @NonNull
    public Integer getPaId() {
        return paId;
    }

    public void setPaId(@NonNull Integer paId) {
        this.paId = paId;
    }

    @NonNull
    public String getDebut() {
        return debut;
    }

    public void setDebut(@NonNull String debut) {
        this.debut = debut;
    }

    @NonNull
    public String getFin() {
        return fin;
    }

    public void setFin(@NonNull String fin) {
        this.fin = fin;
    }

    @NonNull
    public Integer getCapVisite() {
        return capVisite;
    }

    public void setCapVisite(@NonNull Integer capVisite) {
        this.capVisite = capVisite;
    }

    @NonNull
    public Integer getTotalVisite() {
        return totalVisite;
    }

    public void setTotalVisite(@NonNull Integer totalVisite) {
        this.totalVisite = totalVisite;
    }

    @NonNull
    public Boolean getValide() {
        return valide;
    }

    public void setValide(@NonNull Boolean valide) {
        this.valide = valide;
    }
}
