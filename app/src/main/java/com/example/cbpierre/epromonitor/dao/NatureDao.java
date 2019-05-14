package com.example.cbpierre.epromonitor.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.cbpierre.epromonitor.models.Nature;

import java.util.List;

@Dao
public interface NatureDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Nature titre);

    @Query("Select * from nature_table order by nomNature asc")
    LiveData<List<Nature>> getAllNature();
}
