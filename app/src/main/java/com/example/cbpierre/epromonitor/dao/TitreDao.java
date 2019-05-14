package com.example.cbpierre.epromonitor.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.cbpierre.epromonitor.models.Titre;

import java.util.List;

@Dao
public interface TitreDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Titre titre);

    @Query("Select * from titre_table order by titre asc")
    LiveData<List<Titre>> getAllTitre();
}
