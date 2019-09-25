package com.example.cbpierre.epromonitor.models;

public class JoinContactGhSV {
    private Integer gh_id;
    private Integer con_id;
    private String nom_ratio;
    private String nom;
    private String cree_par;
    private String cree_le;
    private String modifie_par;
    private String modifie_le;
    private Boolean rapport_complete;
    private Boolean gh_complete;
    private String statut;
    private String note;
    private String jour;

    public JoinContactGhSV() {
    }


    public Integer getGh_id() {
        return gh_id;
    }

    public void setGh_id(Integer gh_id) {
        this.gh_id = gh_id;
    }

    public Integer getCon_id() {
        return con_id;
    }

    public void setCon_id(Integer con_id) {
        this.con_id = con_id;
    }

    public String getNom_ratio() {
        return nom_ratio;
    }

    public void setNom_ratio(String nom_ratio) {
        this.nom_ratio = nom_ratio;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
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

    public String getModifie_par() {
        return modifie_par;
    }

    public void setModifie_par(String modifie_par) {
        this.modifie_par = modifie_par;
    }

    public String getModifie_le() {
        return modifie_le;
    }

    public void setModifie_le(String modifie_le) {
        this.modifie_le = modifie_le;
    }

    public Boolean getRapport_complete() {
        return rapport_complete;
    }

    public void setRapport_complete(Boolean rapport_complete) {
        this.rapport_complete = rapport_complete;
    }

    public Boolean getGh_complete() {
        return gh_complete;
    }

    public void setGh_complete(Boolean gh_complete) {
        this.gh_complete = gh_complete;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getJour() {
        return jour;
    }

    public void setJour(String jour) {
        this.jour = jour;
    }
}