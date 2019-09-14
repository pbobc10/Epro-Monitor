package com.example.cbpierre.epromonitor.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.cbpierre.epromonitor.models.GHJourContactProduit;

@Dao
public interface GHJourContactProduitDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertGHJourContactProduit(GHJourContactProduit ghJourContactProduit);

    @Query("delete from gh_jour_contact_produit")
    void deleteGHJourContactProduit();
}
