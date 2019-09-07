package com.example.cbpierre.epromonitor.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.cbpierre.epromonitor.models.PaContactProduit;
import com.example.cbpierre.epromonitor.models.Produit;

import java.util.List;

@Dao
public interface PaContactProduitDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertPaContactProduit(PaContactProduit paContactProduit);

    @Query("select produit.produit_id ,produit.nom_produit from  pa_contact_produit,produit where pa_contact_produit.produit_id=produit.produit_id " +
            "and pa_contact_produit.con_id=:contactId order by pa_contact_produit.produit_id")
    LiveData<List<Produit>> allPaContactProduit(Integer contactId);

    @Query("delete from pa_contact_produit")
    void deleteAllPaContactProduit();
}
