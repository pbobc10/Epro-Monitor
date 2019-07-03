package com.example.cbpierre.epromonitor.models;

import com.google.gson.annotations.Expose;


import java.util.List;

public class SendNewContactEtabs {
    @Expose
    private String titre;
    @Expose
    private String nom;
    @Expose
    private String prenom;
    @Expose
    private String nature;
    @Expose
    private String secteur;
    @Expose
    private String specialite;
    @Expose
    private String force;
    @Expose
    private String phone1;
    @Expose
    private String phone2;
    @Expose
    private String phone3;
    @Expose
    private String email;
    @Expose
    private List<Integer> etabsExistants;
    @Expose
    private List<JoinNewEtabNewContact> etabsNouveaux;
    @Expose
    private String creePar;
    @Expose
    private String creeLe;
    @Expose
    private String modifiePar;
    @Expose
    private String modifieLe;
    @Expose
    private String transferePar;
    @Expose
    private String transfereLe;

    public SendNewContactEtabs() {
    }

    public SendNewContactEtabs(String titre, String nom, String prenom, String nature, String secteur, String specialite, String force, String phone1, String phone2, String phone3, String email, List<Integer> etabsExistants, List<JoinNewEtabNewContact> etabsNouveaux, String creePar, String creeLe, String modifiePar, String modifieLe, String transferePar, String transfereLe) {
        this.titre = titre;
        this.nom = nom;
        this.prenom = prenom;
        this.nature = nature;
        this.secteur = secteur;
        this.specialite = specialite;
        this.force = force;
        this.phone1 = phone1;
        this.phone2 = phone2;
        this.phone3 = phone3;
        this.email = email;
        this.etabsExistants = etabsExistants;
        this.etabsNouveaux = etabsNouveaux;
        this.creePar = creePar;
        this.creeLe = creeLe;
        this.modifiePar = modifiePar;
        this.modifieLe = modifieLe;
        this.transferePar = transferePar;
        this.transfereLe = transfereLe;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public void setSecteur(String secteur) {
        this.secteur = secteur;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public void setForce(String force) {
        this.force = force;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public void setPhone3(String phone3) {
        this.phone3 = phone3;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEtabsExistants(List<Integer> etabsExistants) {
        this.etabsExistants = etabsExistants;
    }

    public void setEtabsNouveaux(List<JoinNewEtabNewContact> etabsNouveaux) {
        this.etabsNouveaux = etabsNouveaux;
    }

    public void setCreePar(String creePar) {
        this.creePar = creePar;
    }

    public void setCreeLe(String creeLe) {
        this.creeLe = creeLe;
    }

    public void setModifiePar(String modifiePar) {
        this.modifiePar = modifiePar;
    }

    public void setModifieLe(String modifieLe) {
        this.modifieLe = modifieLe;
    }

    public void setTransferePar(String transferePar) {
        this.transferePar = transferePar;
    }

    public void setTransfereLe(String transfereLe) {
        this.transfereLe = transfereLe;
    }
}
