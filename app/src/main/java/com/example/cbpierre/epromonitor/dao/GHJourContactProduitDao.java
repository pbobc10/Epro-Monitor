package com.example.cbpierre.epromonitor.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.cbpierre.epromonitor.models.GHJourContactProduit;
import com.example.cbpierre.epromonitor.models.JoinProduitAcceptabliliteGHProduit;
import com.example.cbpierre.epromonitor.models.Produit;

import java.util.List;

@Dao
public interface GHJourContactProduitDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertGHJourContactProduit(GHJourContactProduit... ghJourContactProduit);

    @Query("delete from gh_jour_contact_produit")
    void deleteGHJourContactProduit();

    @Query("select * from gh_jour_contact_produit order by gh_id,jour,con_id")
    List<GHJourContactProduit> ghJourContactProduitList();

    @Query(" select ghp.gh_id,ghp.con_id,ghp.jour,ghp.note,ghp.accept code_acceptabilite,ghp.produit_id,p.nom_produit,a.nom nom_acceptabilite from gh_jour_contact_produit ghp,produit p,acceptabilite_ref a \n" +
            "            where   ghp.produit_id=p.produit_id \n" +
            "            and a.code=ghp.accept \n" +
            "            and ghp.gh_id=:ghId and ghp.con_id=:conId and ghp.jour=:jour   ")
    LiveData<List<JoinProduitAcceptabliliteGHProduit>> getProduitAcceptabliliteGHProduit(int ghId, int conId, String jour);

    @Query("select produit.produit_id ,produit.nom_produit from  pa_contact_produit,produit,gh_jour_contact" +
            " where pa_contact_produit.produit_id=produit.produit_id " +
            " and pa_contact_produit.con_id=gh_jour_contact.con_id" +
            " and pa_contact_produit.con_id=:contactId " +
            " and gh_jour_contact.jour=:jour " +
            " and gh_jour_contact.gh_id=:ghId " +
            " and produit.produit_id not in (" +
            " select gh_jour_contact_produit.produit_id from gh_jour_contact_produit where gh_id=:ghId and con_id=:contactId and jour=:jour" +
            ")" +
            "order by produit.nom_produit")
    LiveData<List<Produit>> allGhJourContactProduit(Integer ghId, String jour, Integer contactId);


    @Query("delete from gh_jour_contact_produit where gh_id=:ghId and jour=:jour and con_id=:conId and produit_id=:produit_id")
    void deleteGHJourContactProduitId(int ghId, int conId, String jour, int produit_id);

    @Query("delete from gh_jour_contact_produit where gh_id=:ghId and jour=:jour and con_id=:conId")
    void deleteGHJourContactProduit(int ghId, int conId, String jour);
}
