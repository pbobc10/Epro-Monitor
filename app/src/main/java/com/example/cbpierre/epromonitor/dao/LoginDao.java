package com.example.cbpierre.epromonitor.dao;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.cbpierre.epromonitor.models.Login;

import java.util.List;

@Dao
public interface LoginDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Login login);

    @Query("SELECT * FROM login_table  WHERE username = :user and password = :pass ")
    Login findLoginUserByUsernameAndPassword(String user, String pass);

    @Query("SELECT count(*) FROM login_table  WHERE username = :user and password = :pass ")
    int findCountUser(String user, String pass);

    @Query("Select * from login_table order by username asc")
    LiveData<List<Login>> getAllUusers();

    @Query("Select codeMob from login_table where username=:user")
    String findCodMob(String user);

}
