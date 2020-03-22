package com.example.cbpierre.epromonitor.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.cbpierre.epromonitor.models.CommuneGH;
import com.example.cbpierre.epromonitor.models.CommuneLocaliteContact;
import com.example.cbpierre.epromonitor.models.LocaliteGH;

import java.util.List;

@Dao
public interface CommuneLocaliteContactDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCommuneLocaliteContact(CommuneLocaliteContact... communeLocaliteContact);

    @Query("delete from commune_localite_contact")
    void deleteCommuneLocaliteContact();

    // Commune GH
    @Query("select distinct commune_localite_contact.commune from commune_localite_contact " +
            "order by commune_localite_contact.commune")
    LiveData<List<CommuneGH>> allCommuneGH();

    //Localite GH
    @Query("select distinct commune_localite_contact.localite from commune_localite_contact " +
            "where commune_localite_contact.commune=:commune " +
            "order by commune_localite_contact.localite")
    LiveData<List<LocaliteGH>> allLocaliteGH(String commune);

}
