package com.example.cbpierre.epromonitor.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.cbpierre.epromonitor.models.StatutJourRef;

@Dao
public interface StatutJourDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertStatutJour(StatutJourRef... statutJourRef);

    @Query("delete from statut_jour_ref")
    void deleteStatutJour();
}
