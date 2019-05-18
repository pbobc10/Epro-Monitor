package com.example.cbpierre.epromonitor.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.cbpierre.epromonitor.models.Specialite;

import java.util.List;

@Dao
public interface SpecialiteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Specialite specialite);

    @Query("select * from specialite_table order by nomSpecialite asc")
    LiveData<List<Specialite>> getAllSpecialite();
 }
