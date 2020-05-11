package com.example.cbpierre.epromonitor.models;

public class EtabContact {
    private String nom;
    private String prenom;
    private String nomSpecialite;
    private String tid;

    public EtabContact() {
    }

    public EtabContact(String nom, String prenom, String nomSpecialite, String tid) {
        this.nom = nom;
        this.prenom = prenom;
        this.nomSpecialite = nomSpecialite;
        this.tid = tid;
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

    public String getNomSpecialite() {
        return nomSpecialite;
    }

    public void setNomSpecialite(String nomSpecialite) {
        this.nomSpecialite = nomSpecialite;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }
}
