package com.example.cbpierre.epromonitor.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.cbpierre.epromonitor.models.Secteur;


import java.util.List;

@Dao
public interface SecteurDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Secteur secteur);

    @Query("Select * from secteur_table order by nom_secteur asc")
    LiveData<List<Secteur>> getAllSecteur();
}
