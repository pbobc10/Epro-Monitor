package com.example.cbpierre.epromonitor.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.cbpierre.epromonitor.models.GHJourContact;
import com.example.cbpierre.epromonitor.models.JoinContactGhSV;

import java.util.List;

@Dao
public interface GHJourContactDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertGHJOurContact(GHJourContact ghJourContact);

    @Query("delete from gh_jour_contact")
    void deleteGHJourContact();

    @Query("update gh_jour_contact " +
            " set statut=:statut,note=:note,cree_par=:creePar,cree_le=:creeLe,modifie_par=:modifiePar,modifie_le=:modifieLe," +
            " promotion=:promotion,livraison=:livraison,recouvrement=:recouvrement,autre=:autre,debut=:debut,fin=:fin,lieu=:lieu,autre_lieu=:autreLieu " +
            " where jour=:jour and gh_id=:ghId and con_id=:conId ")
    void updateGHJourContact(String statut, String note, String creePar, String creeLe, String modifiePar, String modifieLe, String jour, Integer ghId, Integer conId,
                             boolean promotion, boolean livraison, boolean recouvrement, boolean autre, String debut, String fin, String lieu, String autreLieu);

    @Query("select gh_jour_contact.gh_id,gh_jour_contact.con_id,contact_visite.nom_ratio,statut_visite_ref.nom,gh_jour_contact.statut,gh_jour_contact.cree_par ,gh_jour_contact.cree_le,gh_jour_contact.modifie_par,gh_jour_contact.modifie_le,gh_jour.rapport_complete,gh.gh_complete ,gh_jour_contact.note,gh_jour_contact.jour,gh_jour_contact.promotion,gh_jour_contact.livraison,gh_jour_contact.recouvrement,gh_jour_contact.autre,gh_jour_contact.debut,gh_jour_contact.fin,gh_jour_contact.lieu,gh_jour_contact.autre_lieu from gh_jour_contact,statut_visite_ref,contact_visite,gh_jour,gh" +
            " where gh_jour_contact.con_id=contact_visite.con_id " +
            "and gh_jour_contact.statut=statut_visite_ref.code " +
            "and gh_jour_contact.gh_id=gh_jour.gh_id " +
            "and gh_jour_contact.gh_id=gh.gh_id " +
            "and gh_jour_contact.jour=gh_jour.jour " +
            "and gh_jour_contact.jour=:jour " +
            "order by contact_visite.nom_ratio ")
    LiveData<List<JoinContactGhSV>> allGhJourContact(String jour);

    @Query("delete from gh_jour_contact where gh_jour_contact.con_id=:conId and gh_jour_contact.gh_id=:ghId")
    void deleteJourContact(Integer ghId, Integer conId);

    @Query("select * from gh_jour_contact order by gh_id,jour,con_id")
    List<GHJourContact> ghJourContactList();
}
