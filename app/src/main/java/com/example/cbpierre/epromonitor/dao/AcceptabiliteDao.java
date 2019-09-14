package com.example.cbpierre.epromonitor.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.cbpierre.epromonitor.models.AcceptabiliteRef;

@Dao
public interface AcceptabiliteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAcceptabilite(AcceptabiliteRef acceptabiliteRef);

    @Query("delete from acceptabilite_ref")
    void delteAcceptabilite();
}
