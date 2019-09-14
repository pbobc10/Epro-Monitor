package com.example.cbpierre.epromonitor.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.cbpierre.epromonitor.models.GHJour;

@Dao
public interface GHJourDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertGHJour(GHJour ghJour);

    @Query("delete from gh_jour")
    void deleteGHJour();
}
