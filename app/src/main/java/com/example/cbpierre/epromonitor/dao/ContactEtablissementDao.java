package com.example.cbpierre.epromonitor.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.cbpierre.epromonitor.models.ContactEtablissement;
import com.example.cbpierre.epromonitor.models.JoinContactEtablissementData;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface ContactEtablissementDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ContactEtablissement... contactEtablissement);

    @Query("select etablissement_table.nom_Etablissement,etablissement_table.adresse,zone_table.description_zone_ht from contact_etablissement,etablissement_table,zone_table " +
            "where contact_etablissement.conIds = :conIds and contact_etablissement.etIds=etablissement_table.etId " +
            "and etablissement_table.localite=zone_table.zone_ht_id")
    LiveData<List<JoinContactEtablissementData>> getAllEtablissementByContactId(Integer conIds);

    @Query("select etablissement_table.nom_Etablissement,etablissement_table.adresse,zone_table.description_zone_ht" +
            " from new_contact_etab,etablissement_table,zone_table " +
            "  where (new_contact_etab.new_etab_id = etablissement_table.etabId or new_contact_etab.etab_id=etablissement_table.etId)" +
            " and etablissement_table.localite = zone_table.zone_ht_id " +
            //"and etablissement_table.is_new_etab = 1" +
            " and new_contact_etab.contact_id = :conIds")
    LiveData<List<JoinContactEtablissementData>> getAllNewEtablissementByContactId(Integer conIds);
}
