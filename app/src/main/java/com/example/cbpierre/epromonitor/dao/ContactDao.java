package com.example.cbpierre.epromonitor.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.cbpierre.epromonitor.models.CompleteContact;
import com.example.cbpierre.epromonitor.models.Contact;

import java.util.List;

@Dao
public interface ContactDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Contact contact);

    @Query("Select * from contact_table order by nom asc")
    LiveData<List<Contact>> getAllContacts();

    @Query("delete from contact_table  where conId is null and is_new_contact=1")
    void deleteNewcontactAfterSync();

    @Query("Select * from contact_table where conId is null and is_new_contact=1 order by nom asc")
    List<Contact> getNewContacts();

    @Query("Select * from contact_table , FORCE_TABLE, NATURE_TABLE,SECTEUR_TABLE,SPECIALITE_TABLE,TITRE_TABLE " +
            "where FORCE_TABLE.fId=CONTACT_TABLE.force " +
            "and  NATURE_TABLE.natId=CONTACT_TABLE.nature  " +
            "and SECTEUR_TABLE.secId=CONTACT_TABLE.secteur " +
            "and SPECIALITE_TABLE.spId=CONTACT_TABLE.specialite   " +
            "and TITRE_TABLE.tid=CONTACT_TABLE.titre " +
            "and ((contact_table.nom like :nom ||'%')" +
            "or (contact_table.prenom like :nom ||'%'))" +
            "order by contact_table.nom")
    LiveData<List<CompleteContact>> getContactByNom(String nom);


    @Query("Select * from contact_table , FORCE_TABLE, NATURE_TABLE,SECTEUR_TABLE,SPECIALITE_TABLE,TITRE_TABLE " +
            "where FORCE_TABLE.fId=CONTACT_TABLE.force " +
            "and  NATURE_TABLE.natId=CONTACT_TABLE.nature  " +
            "and SECTEUR_TABLE.secId=CONTACT_TABLE.secteur " +
            "and SPECIALITE_TABLE.spId=CONTACT_TABLE.specialite   " +
            "and TITRE_TABLE.tid=CONTACT_TABLE.titre " +
            "order by contact_table.nom")
    LiveData<List<CompleteContact>> getAllCompleteContacts();
}
