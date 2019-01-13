package com.example.cbpierre.epromonitor.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Date;

@Entity(tableName = "contact_table")
public class Contact {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "conId")
    private int conId;

    @ColumnInfo(name = "titre")
    private String titre;

    @ColumnInfo(name = "nom")
    private String nom;

    @ColumnInfo(name = "prenom")
    private String prenom;

    @ColumnInfo(name = "nature")
    private String nature;

    @ColumnInfo(name = "secteur")
    private String secteur;

    @ColumnInfo(name = "specialite")
    private String specialite;

    @ColumnInfo(name = "force")
    private String force;

    @ColumnInfo(name = "phone1")
    private String phone1;

    @ColumnInfo(name = "phone2")
    private String phone2;

    @ColumnInfo(name = "phone3")
    private String phone3;

    @ColumnInfo(name = "email")
    private String email;

    @ColumnInfo(name = "statut")
    private int statut;

    @ColumnInfo(name = "transfere_par")
    private String transfere_par;

    @ColumnInfo(name = "transfere_le")
    private Date transfere_le;

    @ColumnInfo(name = "cree_par")
    private String cree_par;

    @ColumnInfo(name = "cree_le")
    private Date cree_le;

    @ColumnInfo(name = "modifie_par")
    private String modifie_par;
    @ColumnInfo(name = "modifie_le")
    private Date modifie_le;

    @ColumnInfo(name = "valide")
    private int valide;

    @ColumnInfo(name = "validateur")
    private String validateur;

    @ColumnInfo(name = "date_maj_valide")
    private Date date_maj_valide;

    // constructor
    public Contact( /*int conId,*/ String titre, String nom, String prenom, String nature, String secteur, String specialite, String force, String phone1, String phone2, String phone3, String email, int statut, String transfere_par, Date transfere_le, String cree_par, Date cree_le, String modifie_par, Date modifie_le, int valide, String validateur, Date date_maj_valide) {
        //this.conId = conId;
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
    }

    @NonNull
    public int getConId() {
        return conId;
    }

    public void setConId(@NonNull int conId) {
        this.conId = conId;
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

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getSecteur() {
        return secteur;
    }

    public void setSecteur(String secteur) {
        this.secteur = secteur;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public String getForce() {
        return force;
    }

    public void setForce(String force) {
        this.force = force;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getPhone3() {
        return phone3;
    }

    public void setPhone3(String phone3) {
        this.phone3 = phone3;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getStatut() {
        return statut;
    }

    public void setStatut(int statut) {
        this.statut = statut;
    }

    public String getTransfere_par() {
        return transfere_par;
    }

    public void setTransfere_par(String transfere_par) {
        this.transfere_par = transfere_par;
    }

    public Date getTransfere_le() {
        return transfere_le;
    }

    public void setTransfere_le(Date transfere_le) {
        this.transfere_le = transfere_le;
    }

    public String getCree_par() {
        return cree_par;
    }

    public void setCree_par(String cree_par) {
        this.cree_par = cree_par;
    }

    public Date getCree_le() {
        return cree_le;
    }

    public void setCree_le(Date cree_le) {
        this.cree_le = cree_le;
    }

    public String getModifie_par() {
        return modifie_par;
    }

    public void setModifie_par(String modifie_par) {
        this.modifie_par = modifie_par;
    }

    public Date getModifie_le() {
        return modifie_le;
    }

    public void setModifie_le(Date modifie_le) {
        this.modifie_le = modifie_le;
    }

    public int getValide() {
        return valide;
    }

    public void setValide(int valide) {
        this.valide = valide;
    }

    public String getValidateur() {
        return validateur;
    }

    public void setValidateur(String validateur) {
        this.validateur = validateur;
    }

    public Date getDate_maj_valide() {
        return date_maj_valide;
    }

    public void setDate_maj_valide(Date date_maj_valide) {
        this.date_maj_valide = date_maj_valide;
    }
}
