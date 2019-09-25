package com.example.cbpierre.epromonitor.models;

public class JoinGHJourStatutRef {
    private Integer gh_id;
    private String jour;
    private String nom;
    private String cree_par;
    private String cree_le;
    private String modifie_par;
    private String modifie_le;
    private String transfere_par;
    private String transfere_le;
    private Boolean rapport_complete;
    private String complete_par;
    private String complete_le;

    public JoinGHJourStatutRef() {
    }

    public Integer getGh_id() {
        return gh_id;
    }

    public void setGh_id(Integer gh_id) {
        this.gh_id = gh_id;
    }

    public String getJour() {
        return jour;
    }

    public void setJour(String jour) {
        this.jour = jour;
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

    public String getTransfere_par() {
        return transfere_par;
    }

    public void setTransfere_par(String transfere_par) {
        this.transfere_par = transfere_par;
    }

    public String getTransfere_le() {
        return transfere_le;
    }

    public void setTransfere_le(String transfere_le) {
        this.transfere_le = transfere_le;
    }

    public Boolean getRapport_complete() {
        return rapport_complete;
    }

    public void setRapport_complete(Boolean rapport_complete) {
        this.rapport_complete = rapport_complete;
    }

    public String getComplete_par() {
        return complete_par;
    }

    public void setComplete_par(String complete_par) {
        this.complete_par = complete_par;
    }

    public String getComplete_le() {
        return complete_le;
    }

    public void setComplete_le(String complete_le) {
        this.complete_le = complete_le;
    }
}
