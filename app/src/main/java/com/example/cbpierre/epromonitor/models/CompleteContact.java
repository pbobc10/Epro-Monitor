package com.example.cbpierre.epromonitor.models;

import java.io.Serializable;

public class CompleteContact implements Serializable {
    private int conId;
    private String titre;
    private String nom;
    private String prenom;
    private String nature;
    private String secteur;
    private String specialite;
    private String force;
    private String phone1;
    private String phone2;
    private String phone3;
    private String email;
    private int statut;
    private String transfere_par;
    private String transfere_le;
    private String cree_par;
    private String cree_le;
    private String modifie_par;
    private String modifie_le;
    private boolean valide;
    private String validateur;
    private String date_maj_valide;

    //force
    private String fid;
    private String nomForce;
    //nature
    private String natId;
    private String nomNature;
    //secteur
    private String secId;
    private String nomSecteur;
    //specialite
    private String spId;
    private String nomSpecialite;
    //titre
    private String tid;
    private String nomTitre;

    public CompleteContact(int conId, String titre, String nom, String prenom, String nature, String secteur, String specialite, String force, String phone1, String phone2, String phone3, String email, int statut, String transfere_par, String transfere_le, String cree_par, String cree_le, String modifie_par, String modifie_le, boolean valide, String validateur, String date_maj_valide, String fid, String nomForce, String natId, String nomNature, String secId, String nomSecteur, String spId, String nomSpecialite, String tid, String nomTitre) {
        this.conId = conId;
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
        this.statut = statut;
        this.transfere_par = transfere_par;
        this.transfere_le = transfere_le;
        this.cree_par = cree_par;
        this.cree_le = cree_le;
        this.modifie_par = modifie_par;
        this.modifie_le = modifie_le;
        this.valide = valide;
        this.validateur = validateur;
        this.date_maj_valide = date_maj_valide;
        this.fid = fid;
        this.nomForce = nomForce;
        this.natId = natId;
        this.nomNature = nomNature;
        this.secId = secId;
        this.nomSecteur = nomSecteur;
        this.spId = spId;
        this.nomSpecialite = nomSpecialite;
        this.tid = tid;
        this.nomTitre = nomTitre;
    }

    public int getConId() {
        return conId;
    }

    public String getTitre() {
        return titre;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNature() {
        return nature;
    }

    public String getSecteur() {
        return secteur;
    }

    public String getSpecialite() {
        return specialite;
    }

    public String getForce() {
        return force;
    }

    public String getPhone1() {
        return phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public String getPhone3() {
        return phone3;
    }

    public String getEmail() {
        return email;
    }

    public int getStatut() {
        return statut;
    }

    public String getTransfere_par() {
        return transfere_par;
    }

    public String getTransfere_le() {
        return transfere_le;
    }

    public String getCree_par() {
        return cree_par;
    }

    public String getCree_le() {
        return cree_le;
    }

    public String getModifie_par() {
        return modifie_par;
    }

    public String getModifie_le() {
        return modifie_le;
    }

    public boolean isValide() {
        return valide;
    }

    public String getValidateur() {
        return validateur;
    }

    public String getDate_maj_valide() {
        return date_maj_valide;
    }

    public String getFid() {
        return fid;
    }

    public String getNomForce() {
        return nomForce;
    }

    public String getNatId() {
        return natId;
    }

    public String getNomNature() {
        return nomNature;
    }

    public String getSecId() {
        return secId;
    }

    public String getNomSecteur() {
        return nomSecteur;
    }

    public String getSpId() {
        return spId;
    }

    public String getNomSpecialite() {
        return nomSpecialite;
    }

    public String getTid() {
        return tid;
    }

    public String getNomTitre() {
        return nomTitre;
    }
}
