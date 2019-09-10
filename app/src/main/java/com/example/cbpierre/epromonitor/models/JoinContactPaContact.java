package com.example.cbpierre.epromonitor.models;

public class JoinContactPaContact {
    private String nom_ratio;
    private String nomNature;
    private String nomSpecialite;
    private String force;
    private String quota;
    private int con_id;

    public JoinContactPaContact() {
    }

    public String getNom_ratio() {
        return nom_ratio;
    }

    public void setNom_ratio(String nom_ratio) {
        this.nom_ratio = nom_ratio;
    }

    public int getCon_id() {
        return con_id;
    }

    public void setCon_id(int con_id) {
        this.con_id = con_id;
    }

    public String getForce() {
        return force;
    }

    public void setForce(String force) {
        this.force = force;
    }

    public String getNomNature() {
        return nomNature;
    }

    public void setNomNature(String nomNature) {
        this.nomNature = nomNature;
    }

    public String getNomSpecialite() {
        return nomSpecialite;
    }

    public void setNomSpecialite(String nomSpecialite) {
        this.nomSpecialite = nomSpecialite;
    }

    public String getQuota() {
        return quota;
    }

    public void setQuota(String quota) {
        this.quota = quota;
    }
}
