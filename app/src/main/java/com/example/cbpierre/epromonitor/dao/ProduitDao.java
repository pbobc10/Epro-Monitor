package com.example.cbpierre.epromonitor.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.cbpierre.epromonitor.models.Produit;

import java.util.List;

@Dao
public interface ProduitDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Produit produit);

    @Query("select * from produit")
    LiveData<List<Produit>> allProduit();

    @Query("delete from produit")
    void deletePrduit();
}
