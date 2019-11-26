package com.example.cbpierre.epromonitor.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.cbpierre.epromonitor.models.Specialite;
import com.example.cbpierre.epromonitor.models.SpecialiteGH;

import java.util.List;

@Dao
public interface SpecialiteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Specialite specialite);

    @Query("select * from specialite_table order by nomSpecialite asc")
    LiveData<List<Specialite>> getAllSpecialite();

    @Query("select distinct specialite_table.nomSpecialite,specialite_table.spId from specialite_table,contact_visite\n" +
            "where specialite_table.spId=contact_visite.specialite\n" +
            "order by specialite_table.nomSpecialite")
    LiveData<List<SpecialiteGH>> getAllSpecialiteGH();
}
