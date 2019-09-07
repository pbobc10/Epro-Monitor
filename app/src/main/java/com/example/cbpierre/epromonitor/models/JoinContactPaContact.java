package com.example.cbpierre.epromonitor.models;

public class JoinContactPaContact {
    private String titre;
    private String nom;
    private String prenom;
    private String nomNature;
    private String nomSpecialite;
    private String force;
    private String quota;
    private int con_id;

    public JoinContactPaContact() {
    }


    public int getCon_id() {
        return con_id;
    }

    public void setCon_id(int con_id) {
        this.con_id = con_id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
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
