package com.example.cbpierre.epromonitor.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

@Entity(tableName = "etablissement_table", indices = @Index(value = "etId", name = "etablissement_index", unique = true))
public class Etablissement implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "etabId", index = true)
    private int etabId;

    @ColumnInfo(name = "etId")
    private Integer etId;

    @ColumnInfo(name = "nom_Etablissement")
    private String nomEtablissement;

    @ColumnInfo(name = "localite")
    private String localite;

    @ColumnInfo(name = "adresse")
    private String adresse;

    @ColumnInfo(name = "latitude")
    private String latitude;

    @ColumnInfo(name = "longitude")
    private String longitude;

    @ColumnInfo(name = "statut")
    private int statut;

    @ColumnInfo(name = "valide")
    private boolean valide;

    @ColumnInfo(name = "modifie_par")
    private String modifie_par;

    @ColumnInfo(name = "modifie_le")
    private String modifie_le;

    @Nullable
    @ColumnInfo(name = "cree_par")
    private String cree_par;

    @Nullable
    @ColumnInfo(name = "cree_le")
    private String cree_le;

    @Nullable
    @ColumnInfo(name = "transfere_par")
    private String transfere_par;

    @Nullable
    @ColumnInfo(name = "transfere_le")
    private String transfere_le;

    @ColumnInfo(name = "is_new_etab")
    private boolean isNewEtab;


    public Etablissement() {
    }

    public Etablissement(Integer etId, String nomEtablissement, String localite, String adresse, String latitude, String longitude, int statut, boolean valide, String modifie_par, String modifie_le, String cree_par, String cree_le, String transfere_par, String transfere_le, boolean isNewEtab) {
        this.etId = etId;
        this.nomEtablissement = nomEtablissement;
        this.localite = localite;
        this.adresse = adresse;
        this.latitude = latitude;
        this.longitude = longitude;
        this.statut = statut;
        this.valide = valide;
        this.modifie_par = modifie_par;
        this.modifie_le = modifie_le;
        this.cree_par = cree_par;
        this.cree_le = cree_le;
        this.transfere_par = transfere_par;
        this.transfere_le = transfere_le;
        this.isNewEtab = isNewEtab;
    }

    public Etablissement(JSONObject jsonObject) {
        try {
            this.etId = jsonObject.getInt("ETID");
            this.nomEtablissement = jsonObject.getString("NOM");
            this.localite = jsonObject.getString("LOCALITE");
            this.adresse = jsonObject.getString("ADRESSE");
            this.latitude = jsonObject.getString("LATITUDE");
            this.longitude = jsonObject.getString("LONGITUDE");
            this.statut = jsonObject.getInt("STATUT");
            this.valide = jsonObject.getBoolean("VALIDE");
            this.modifie_par = jsonObject.getString("MODIFIE_PAR");
            this.modifie_le = jsonObject.getString("MODIFIE_LE");
            this.isNewEtab = false;
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Etablissement> fromJson(JSONArray jsonArray) {
        ArrayList<Etablissement> etablissements = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                etablissements.add(new Etablissement(jsonArray.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return etablissements;
    }

    @NonNull
    public Integer getEtId() {
        return etId;
    }

    public String getNomEtablissement() {
        return nomEtablissement;
    }

    public String getLocalite() {
        return localite;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public int getStatut() {
        return statut;
    }

    public boolean isValide() {
        return valide;
    }

    public String getModifie_par() {
        return modifie_par;
    }

    public void setEtId(@NonNull Integer etId) {
        this.etId = etId;
    }

    public void setNomEtablissement(String nomEtablissement) {
        this.nomEtablissement = nomEtablissement;
    }

    public void setLocalite(String localite) {
        this.localite = localite;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setStatut(int statut) {
        this.statut = statut;
    }

    public void setValide(boolean valide) {
        this.valide = valide;
    }

    public void setModifie_par(String modifie_par) {
        this.modifie_par = modifie_par;
    }

    public void setModifie_le(String modifie_le) {
        this.modifie_le = modifie_le;
    }

    public String getModifie_le() {
        return modifie_le;
    }

    public int getEtabId() {
        return etabId;
    }

    public void setEtabId(int etabId) {
        this.etabId = etabId;
    }

    public boolean isNewEtab() {
        return isNewEtab;
    }

    public void setNewEtab(boolean newEtab) {
        isNewEtab = newEtab;
    }

    @Nullable
    public String getCree_par() {
        return cree_par;
    }

    public void setCree_par(@Nullable String cree_par) {
        this.cree_par = cree_par;
    }

    @Nullable
    public String getCree_le() {
        return cree_le;
    }

    public void setCree_le(@Nullable String cree_le) {
        this.cree_le = cree_le;
    }

    @Nullable
    public String getTransfere_par() {
        return transfere_par;
    }

    public void setTransfere_par(@Nullable String transfere_par) {
        this.transfere_par = transfere_par;
    }

    @Nullable
    public String getTransfere_le() {
        return transfere_le;
    }

    public void setTransfere_le(@Nullable String transfere_le) {
        this.transfere_le = transfere_le;
    }
}
