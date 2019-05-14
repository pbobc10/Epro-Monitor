package com.example.cbpierre.epromonitor.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.cbpierre.epromonitor.models.Force;

import java.util.List;

@Dao
public interface ForceDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Force force);

    @Query("select * from force_table order by  nom_force asc")
    LiveData<List<Force>> getAllForce();

   /* @Query("select * from force_table order by  nom_force asc")
    List<Force> getNomForce();*/
}
