package com.example.cbpierre.epromonitor.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.cbpierre.epromonitor.models.PostLogin;

import java.util.List;

@Dao
public interface PostLoginDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(PostLogin postLogin);

    /*@Query("SELECT * FROM login_table  WHERE username = :user and password = :pass ")
    PostLogin findPostLoginUserByUsernameAndPassword(String user, String pass);*/

    /*@Query("SELECT count(*) FROM login_table  WHERE username = :user and password = :pass ")
    int findCountUser(String user, String pass);*/

    @Query("Select * from postLogin_table order by cieId asc")
    LiveData<List<PostLogin>> getAllPostLogin();
}
