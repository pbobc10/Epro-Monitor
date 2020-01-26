package com.example.cbpierre.epromonitor.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.cbpierre.epromonitor.models.PaContact;

import java.util.List;

@Dao
public interface PaContactDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertPaContact(PaContact paContact);

    @Query("select * from pa_contact order by pa_contact.con_id")
    LiveData<List<PaContact>> allPaContact();

    @Query("select * from pa_contact where pa_contact.quota ='-1' ")
    List<PaContact> allPaContactList();

    @Query("delete from pa_contact")
    void deleteAllPaContact();
}
