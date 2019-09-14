package com.example.cbpierre.epromonitor.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.cbpierre.epromonitor.models.StatutVisiteRef;

@Dao
public interface StatutVisiteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertStatutVisite(StatutVisiteRef statutVisiteRef);

    @Query("delete from statut_visite_ref")
    void deleteStatutVisite();
}
