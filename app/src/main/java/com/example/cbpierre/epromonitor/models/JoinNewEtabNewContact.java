package com.example.cbpierre.epromonitor.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JoinNewEtabNewContact {
    private Integer contact_id;

    @Expose
    @SerializedName(value = "nom")
    private String nom_Etablissement;

    @Expose
    private String adresse;

    @Expose
    private String localite;

    @Expose
    private String latitude;

    @Expose
    private String longitude;

    @Expose
    @SerializedName(value = "creePar")
    private String cree_par;

    @Expose
    @SerializedName(value = "creeLe")
    private String cree_le;

    @Expose
    @SerializedName(value = "transferePar")
    private String transfert_par;

    @Expose
    @SerializedName(value = "transfereLe")
    private String transfere_le;

    public Integer getContact_id() {
        return contact_id;
    }

    public void setContact_id(Integer contact_id) {
        this.contact_id = contact_id;
    }

    public String getNom_Etablissement() {
        return nom_Etablissement;
    }

    public void setNom_Etablissement(String nom_Etablissement) {
        this.nom_Etablissement = nom_Etablissement;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getLocalite() {
        return localite;
    }

    public void setLocalite(String localite) {
        this.localite = localite;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getCree_par() {
        return cree_par;
    }

    public void setCree_par(String cree_par) {
        this.cree_par = cree_par;
    }

    public String getCree_le() {
        return cree_le;
    }

    public void setCree_le(String cree_le) {
        this.cree_le = cree_le;
    }

    public String getTransfert_par() {
        return transfert_par;
    }

    public void setTransfert_par(String transfert_par) {
        this.transfert_par = transfert_par;
    }

    public String getTransfere_le() {
        return transfere_le;
    }

    public void setTransfere_le(String transfere_le) {
        this.transfere_le = transfere_le;
    }
}
