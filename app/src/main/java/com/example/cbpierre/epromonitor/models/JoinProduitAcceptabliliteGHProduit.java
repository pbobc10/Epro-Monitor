package com.example.cbpierre.epromonitor.models;

public class JoinProduitAcceptabliliteGHProduit {
    private int gh_id;
    private int con_id;
    private String jour;
    private String note;
    private String code_acceptabilite;
    private int produit_id;
    private String nom_produit;
    private String nom_acceptabilite;

    public JoinProduitAcceptabliliteGHProduit() {
    }

    public JoinProduitAcceptabliliteGHProduit(int gh_id, int con_id, String jour, String note, String code_acceptabilite, int produit_id, String nom_produit, String nom_acceptabilite) {
        this.gh_id = gh_id;
        this.con_id = con_id;
        this.jour = jour;
        this.note = note;
        this.code_acceptabilite = code_acceptabilite;
        this.produit_id = produit_id;
        this.nom_produit = nom_produit;
        this.nom_acceptabilite = nom_acceptabilite;
    }

    public int getGh_id() {
        return gh_id;
    }

    public void setGh_id(int gh_id) {
        this.gh_id = gh_id;
    }

    public int getCon_id() {
        return con_id;
    }

    public void setCon_id(int con_id) {
        this.con_id = con_id;
    }

    public String getJour() {
        return jour;
    }

    public void setJour(String jour) {
        this.jour = jour;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCode_acceptabilite() {
        return code_acceptabilite;
    }

    public void setCode_acceptabilite(String code_acceptabilite) {
        this.code_acceptabilite = code_acceptabilite;
    }

    public int getProduit_id() {
        return produit_id;
    }

    public void setProduit_id(int produit_id) {
        this.produit_id = produit_id;
    }

    public String getNom_produit() {
        return nom_produit;
    }

    public void setNom_produit(String nom_produit) {
        this.nom_produit = nom_produit;
    }

    public String getNom_acceptabilite() {
        return nom_acceptabilite;
    }

    public void setNom_acceptabilite(String nom_acceptabilite) {
        this.nom_acceptabilite = nom_acceptabilite;
    }
}
